package com.gxatek.firewall.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
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

    private FirewallServiceStub mBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: this is " + this);
        mBinder = new FirewallServiceStub(this, this);
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
