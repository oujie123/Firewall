package com.gxa.firewall.service;

import android.app.Service;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;

/**
 * @ProjectName: Firewall
 * @Package: com.gxatek.firewall.service
 * @ClassName: FirewallService
 * @Description: 防火墙服务端Service
 * @Author: JackOu
 * @CreateDate: 2021/5/19 14:12
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/19 14:12
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FirewallService extends Service {
    private static final String TAG = FirewallService.class.getSimpleName();

    private static final String THREAD_NAME = "gxa_firewall_config_thread";
    private FirewallServiceStub mBinder;
    private HandlerThread mHandlerThread;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: this is " + this);
        mHandlerThread = new HandlerThread(THREAD_NAME);
        mHandlerThread.start();
        mBinder = new FirewallServiceStub(this, this, mHandlerThread.getLooper());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        Log.d(TAG, "onBind: mBinder is " + mBinder);
        return mBinder;
    }
}
