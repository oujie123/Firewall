package com.gxatek.firewall;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.gxatek.firewall.service.FirewallService;

/**
 * @ProjectName: Firewall
 * @Package: com.gxatek.firewall
 * @ClassName: FirewallApplication
 * @Description: java类作用描述
 * @Author: JackOu
 * @CreateDate: 2021/5/19 18:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/19 18:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FirewallApplication extends Application {

    private static final String TAG = FirewallApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.d(TAG, TAG + " onCreate");
        super.onCreate();

        //start FirewallService
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), FirewallService.class);
        startService(intent);
    }
}
