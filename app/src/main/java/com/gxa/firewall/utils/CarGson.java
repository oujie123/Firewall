package com.gxatek.firewall.utils;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Jack_Ou  created on 2021/3/1.
 */
public class CarGson {

    private static final String TAG = "XHF_CarGson";

    private void closeQuietly(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                Log.d(TAG, "Exception ignored");
            }
        }
    }

    public boolean writeObjectToJsonFile(String path, Object content) {
        if (content != null) {
            if (TextUtils.isEmpty(path)) {
                return false;
            }
            try {
                return writeToJsonFile(path, new Gson().toJson(content));
            } catch (Exception paramString) {
                Log.e(TAG, "writeObjectToJsonFile error : ", paramString);
                return false;
            }
        }
        return false;
    }

    private boolean writeToJsonFile(String filePath, String toJson) {
        Log.d(TAG, "writeToJsonFile() filePath = " + filePath);
        if ((TextUtils.isEmpty(filePath)) || (TextUtils.isEmpty(toJson))) {
            return false;
        }
        if (TextUtils.isEmpty(toJson)) {
            Log.w(TAG, "writeToJsonFile() saveContent is empty");
            return false;
        }
        FileOutputStream fos = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            byte[] bytesArray = toJson.getBytes();
            fos = new FileOutputStream(filePath);
            fos.write(bytesArray);
            fos.flush();
            Log.i(TAG, "writeToJsonFile() Success!!!");
            return true;
        } catch (IOException exception) {
            Log.w(TAG, "writeToJsonFile() IOException: " + exception);
            return false;
        } finally {
            closeQuietly(fos);
        }
    }

    public <T> T getObjectFromJsonFile(String path) {
        String apps = readJsonFile(path);
        if (!TextUtils.isEmpty(apps)) {
            try {
                StringBuilder builder = new StringBuilder();
                builder.append("getObjectFromJsonFile json : ");
                builder.append(apps);
                Log.i(TAG, builder.toString());
                return new Gson().fromJson(apps, new TypeToken() {
                }.getType());
            } catch (Exception e) {
                StringBuilder builder = new StringBuilder();
                builder.append("readPermissionConfig fromJson error : ");
                builder.append(apps);
                Log.e(TAG, builder.toString(), e);
            }
        }
        return null;
    }

    public <T> T getObjectFromJsonFile(String path, Class<T> type) {
        String apps = readJsonFile(path);
        if (!TextUtils.isEmpty(apps)) {
            try {
                return new Gson().fromJson(apps, type);
            } catch (Exception e) {
                StringBuilder builder = new StringBuilder();
                builder.append("readPermissionConfig fromJson error : ");
                builder.append(path);
                Log.e(TAG, builder.toString(), e);
            }
        }
        return null;
    }

    private String readJsonFile(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        File file = new File(path);
        if (!file.exists()) {
            Log.w(TAG, "readJsonFile path : " + path + " does not exist config file.");
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String readLine;
            while ((readLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(readLine);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            Log.e(TAG, "readJsonFile IOException", e);
        } finally {
            closeQuietly(inputStream);
            closeQuietly(inputStreamReader);
            closeQuietly(bufferedReader);
        }
        return null;
    }
}
