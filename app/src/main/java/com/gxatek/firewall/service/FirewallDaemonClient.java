/**
 * Copyright (c) 2019
 * All Rights Reserved by Thunder Software Technology Co., Ltd and its affiliates.
 * You may not use, copy, distribute, modify, transmit in any form this file
 * except in compliance with THUNDERSOFT in writing by applicable law.
 */

package com.gxatek.firewall.service;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @ProjectName: Firewall
 * @Package: com.gxatek.firewall.service
 * @ClassName: FirewallDaemonClient
 * @Description: 防火墙守护进程的客户端
 * @Author: JackOu
 * @CreateDate: 2021/5/19 14:12
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/19 14:12
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
final class FirewallDaemonClient {
    private static String TAG = "FirewallDaemonClient";
    private static final String FIREWALL_SOCKET = "tsfirewall";
    private static final String EXEC_OK = "OK";
    private static final int BUF_SIZE = 4096;
    private LocalSocket mSocket;
    private OutputStream mOutStream;
    private InputStream mInStream;
    private boolean mConnected = false;

    /**
     * Execute commands from array.
     *
     * @param commands - List of commands
     * @return true if commands succeeded / false otherwise
     */
    synchronized boolean execCommands(@NonNull String[] commands) {
        boolean result = false;

        if (commands.length == 0) {
            Log.e(TAG, "execCommands: commands can't be null");
            return false;
        }

        if (!createSocket()) {
            closeSocket();
            Log.e(TAG, "execCommands: createSocket() failed.");
            return false;
        }

        for (String command : commands) {
            byte[] buf = new byte[BUF_SIZE];
            int length = 0;
            try {
                mOutStream.write(command.getBytes());
                mOutStream.flush();
                length = mInStream.read(buf, 0, BUF_SIZE);
            } catch (IOException ex) {
                Log.e(TAG, "execCommands: " + ex.toString());
                break;
            }
            if (length > 0) {
                final String resultmsg = new String(buf, 0, length, StandardCharsets.UTF_8);
                if (!resultmsg.equals(EXEC_OK)) {
                    Log.e(TAG, "execCommands: " + command + " result is " + resultmsg);
                    result = false;
                    break;
                }
                result = true;
            } else {
                Log.e(TAG, "execCommands: input stream read error");
                break;
            }
        }
        closeSocket();
        return result;
    }

    private boolean createSocket() {
        if (mConnected) {
            return true;
        }

        try {
            mSocket = new LocalSocket();
            LocalSocketAddress address = new LocalSocketAddress(FIREWALL_SOCKET,
                    LocalSocketAddress.Namespace.RESERVED);
            mSocket.connect(address);
            mInStream = mSocket.getInputStream();
            mOutStream = mSocket.getOutputStream();
            if (mInStream != null && mOutStream != null) {
                mConnected = true;
            }
        } catch (IOException ex) {
            Log.e(TAG, "createSocket error: " + ex.toString());
        }
        return mConnected;
    }

    private void closeSocket() {
        if (mOutStream != null) {
            try {
                mOutStream.close();
            } catch (IOException ex) {
                Log.e(TAG, "mOutStream.close() error: " + ex.toString());
            }
            mOutStream = null;
        }
        if (mInStream != null) {
            try {
                mInStream.close();
            } catch (IOException ex) {
                Log.e(TAG, "mInStream.close() error: " + ex.toString());
            }
            mInStream = null;
        }
        if (mSocket != null) {
            try {
                mSocket.close();
            } catch (IOException ex) {
                Log.e(TAG, "mSocket.close() error: " + ex.toString());
            }
            mSocket = null;
        }
        mConnected = false;
    }
}
