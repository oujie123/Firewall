package com.gxa.firewall;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.gxa.firewall.manager.FirewallManager;

/**
 * @ProjectName: Firewall
 * @Package: com.gxatek.firewall
 * @ClassName: MainActivity
 * @Description: 防火墙配置界面
 * @Author: JackOu
 * @CreateDate: 2021/5/19 14:12
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/19 14:12
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MainActivity extends Activity {

    private static final String TAG = "FirewallActivity";
    private FirewallManager fwManager = null;
    private Context mContext;
    private Toast mToast = null;

    private EditText portTextView;
    private Button enablePort;
    private Button disablePort;

    private Switch ipSwitch;
    private EditText ipTextView;
    private Button addIp;
    private Button removeIp;
    private Button clearIp;

    private Switch appSwitch;
    private EditText appTextView;
    private Button addApp;
    private Button removeApp;
    private Button clearApp;

    private EditText rawCmdTextView;
    private Button executeRawCommand;

    private Switch dataSwitch;
    private EditText pathTextView;
    private Button saveConfig;
    private Button restoreConfig;

    private View.OnClickListener mEnablePortListener = new View.OnClickListener() {
        public void onClick(View view) {
            Log.d(TAG, "enablePort clicked");
            String portNum = portTextView.getText().toString().trim();
            boolean ret = false;
            if (portNum.isEmpty()) {
                Log.d(TAG, "enablePort - port number is empty");
                showToast("port number is empty");
                return;
            }
            int port = Integer.parseInt(portNum);
            if (port > 0) {
                ret = fwManager.enableInterfacePort("eth0", port);
                showToast("enablePort " + (ret ? "succeed" : "failed"));
            } else {
                Log.d(TAG, "enablePort - port number is invalid");
                showToast("port number is invalid");
                //enablePort.setEnabled(false);
                //disablePort.setEnabled(false);
            }
        }
    };
    private View.OnClickListener mDisablePortListener = new View.OnClickListener() {
        public void onClick(View view) {
            Log.d(TAG, "disablePort clicked");
            String portNum = portTextView.getText().toString().trim();
            boolean ret = false;
            if (portNum.isEmpty()) {
                Log.d(TAG, "disablePort - port number is empty");
                showToast("port number is empty");
                return;
            }
            int port = Integer.parseInt(portNum);
            if (port > 0) {
                ret = fwManager.disableInterfacePort("eth0", port);
                showToast("disablePort " + (ret ? "succeed" : "failed"));
            } else {
                Log.d(TAG, "disablePort - port number is invalid");
                showToast("port number is invalid");
                //enablePort.setEnabled(false);
                //disablePort.setEnabled(false);
            }
        }
    };
    private View.OnClickListener mIpSwitchListener = new View.OnClickListener() {
        public void onClick(View view) {
            boolean ret = false;
            if (ipSwitch.isChecked()) {
                ipSwitch.setText(R.string.blackList);
                ret = fwManager.switchToIpWhiteList(false);
                showToast("Switch to IP Black List " + (ret ? "succeed" : "failed"));
            } else {
                ipSwitch.setText(R.string.whiteList);
                ret = fwManager.switchToIpWhiteList(true);
                showToast("Switch to IP White List " + (ret ? "succeed" : "failed"));
            }
        }
    };
    private View.OnClickListener mAddIpListener = new View.OnClickListener() {
        public void onClick(View view) {
            Log.d(TAG, "addIp clicked");
            String ipAddr = ipTextView.getText().toString().trim();
            boolean ret = false;
            if (!ipAddr.isEmpty()) {
                if (ipSwitch.isChecked()) {
                    Log.d(TAG, "addIp to black list");
                    ret = fwManager.addIpToBlackList(ipAddr);
                    showToast("Add IP to black list " + (ret ? "succeed" : "failed"));
                } else {
                    Log.d(TAG, "addIp to white list");
                    ret = fwManager.addIpToWhiteList(ipAddr);
                    showToast("Add IP to white list " + (ret ? "succeed" : "failed"));
                }
            } else {
                Log.d(TAG, "addIp - ip address is null");
                showToast("ip address is null");
            }
        }
    };
    private View.OnClickListener mRemoveIpListener = new View.OnClickListener() {
        public void onClick(View view) {
            Log.d(TAG, "removeIp clicked");
            String ipAddr = ipTextView.getText().toString().trim();
            boolean ret = false;
            if (!ipAddr.isEmpty()) {
                if (ipSwitch.isChecked()) {
                    Log.d(TAG, "removeIp from black list");
                    ret = fwManager.removeIpFromBlackList(ipAddr);
                    showToast("Remove IP from black list " + (ret ? "succeed" : "failed"));
                } else {
                    Log.d(TAG, "removeIp from white list");
                    ret = fwManager.removeIpFromWhiteList(ipAddr);
                    showToast("Remove IP from white list " + (ret ? "succeed" : "failed"));
                }
            } else {
                Log.d(TAG, "removeIp - ip address is null");
                showToast("ip address is null");
            }
        }
    };
    private View.OnClickListener mClearIpListener = new View.OnClickListener() {
        public void onClick(View view) {
            Log.d(TAG, "clearIp clicked");
            boolean ret = false;
            if (ipSwitch.isChecked()) {
                Log.d(TAG, "clearIp in black list");
                ret = fwManager.clearIpInBlackList();
                showToast("Clear IP in black list " + (ret ? "succeed" : "failed"));
            } else {
                Log.d(TAG, "clearIp in white list");
                ret = fwManager.clearIpInWhiteList();
                showToast("Clear IP in white list " + (ret ? "succeed" : "failed"));
            }
        }
    };
    private View.OnClickListener mDataSwitchListener = new View.OnClickListener() {
        public void onClick(View view) {
            boolean ret = false;
            ret = fwManager.setDataEnabled(dataSwitch.isChecked());
            dataSwitch.setText(dataSwitch.isChecked() ? R.string.enable : R.string.disable);
            showToast("Switch data " + (ret ? "succeed" : "failed"));
        }
    };
    private View.OnClickListener mAppSwitchListener = new View.OnClickListener() {
        public void onClick(View view) {
            boolean ret = false;
            if (appSwitch.isChecked()) {
                ret = fwManager.switchToAppWhiteList(false);
                appSwitch.setText(R.string.blackList);
                showToast("Switch to App black list " + (ret ? "succeed" : "failed"));
            } else {
                appSwitch.setText(R.string.whiteList);
                ret = fwManager.switchToAppWhiteList(true);
                showToast("Switch to App white list " + (ret ? "succeed" : "failed"));
            }
        }
    };
    private View.OnClickListener mAddAppListener = new View.OnClickListener() {
        public void onClick(View view) {
            Log.d(TAG, "addApp clicked");
            String appName = appTextView.getText().toString().trim();
            boolean ret = false;
            if (!appName.isEmpty()) {
                if (appSwitch.isChecked()) {
                    Log.d(TAG, "addApp to black list");
                    ret = fwManager.addAppToBlackList(appName);
                    showToast("Add App to black list " + (ret ? "succeed" : "failed"));
                } else {
                    Log.d(TAG, "addApp to white list");
                    ret = fwManager.addAppToWhiteList(appName);
                    showToast("Add App to white list " + (ret ? "succeed" : "failed"));
                }
            } else {
                Log.d(TAG, "addApp - app name is null");
                showToast("app name is null");
            }
        }
    };
    private View.OnClickListener mRemoveAppListener = new View.OnClickListener() {
        public void onClick(View view) {
            Log.d(TAG, "removeApp clicked");
            String appName = appTextView.getText().toString().trim();
            boolean ret = false;
            if (!appName.isEmpty()) {
                if (appSwitch.isChecked()) {
                    Log.d(TAG, "removeApp from black list");
                    ret = fwManager.removeAppFromBlackList(appName);
                    showToast("Remove App from black list " + (ret ? "succeed" : "failed"));
                } else {
                    Log.d(TAG, "removeApp from white list");
                    ret = fwManager.removeAppFromWhiteList(appName);
                    showToast("Remove App from white list " + (ret ? "succeed" : "failed"));
                }
            } else {
                Log.d(TAG, "removeApp - app name is null");
                showToast("app name is null");
            }
        }
    };
    private View.OnClickListener mClearAppListener = new View.OnClickListener() {
        public void onClick(View view) {
            Log.d(TAG, "clearApp clicked");
            boolean ret = false;
            if (appSwitch.isChecked()) {
                Log.d(TAG, "clearApp in black list");
                ret = fwManager.clearAppInBlackList();
                showToast("Clear App in black list " + (ret ? "succeed" : "failed"));
            } else {
                Log.d(TAG, "clearApp in white list");
                ret = fwManager.clearAppInWhiteList();
                showToast("Clear App in white list " + (ret ? "succeed" : "failed"));
            }
        }
    };
    private View.OnClickListener mExecuteRawCommandListener = new View.OnClickListener() {
        public void onClick(View view) {
            Log.d(TAG, "executeRawCommand clicked");
            String cmd = rawCmdTextView.getText().toString().trim();
            boolean ret = false;
            if (!cmd.isEmpty()) {
                ret = fwManager.executeRawCommand(new String[] { cmd });
                showToast("Execute Raw Command " + (ret ? "succeed" : "failed"));
            } else {
                Log.d(TAG, "executeRawCommand - cmd is null");
                showToast("cmd is null");
            }
        }
    };
    private View.OnClickListener mSaveConfigListener = new View.OnClickListener() {
        public void onClick(View view) {
            Log.d(TAG, "saveConfig clicked");
            String path = pathTextView.getText().toString().trim();
            boolean ret = false;
            if (!path.isEmpty()) {
                ret = fwManager.saveCurrentRules(path);
                showToast("Saved config to " + path);
            } else {
                Log.d(TAG, "saveConfig - path is null, use default");
                showToast("Path is null, use default path");
                ret = fwManager.saveCurrentRules(null);
            }
            showToast("saveConfig " + (ret ? "succeed" : "failed"));
        }
    };
    private View.OnClickListener mRestoreConfigListener = new View.OnClickListener() {
        public void onClick(View view) {
            Log.d(TAG, "restoreConfig clicked");
            String path = pathTextView.getText().toString().trim();
            boolean ret = false;
            if (!path.isEmpty()) {
                ret = fwManager.restorePreviousRules(path);
                showToast("Restored config from " + path);
            } else {
                Log.d(TAG, "restoreConfig - path is null, use default");
                showToast("Path is null, use default path");
                ret = fwManager.restorePreviousRules(null);
            }
            showToast("Restore config " + (ret ? "succeed" : "failed"));
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        if (fwManager == null) {
            fwManager = FirewallManager.getInstance(mContext);
        }
        initView();
    }

    private void initView() {
        portTextView = findViewById(R.id.port_num);
        enablePort = findViewById(R.id.enable_port);
        disablePort = findViewById(R.id.disable_port);

        ipSwitch = findViewById(R.id.ip_switch);
        ipTextView = findViewById(R.id.ip_address);
        addIp = findViewById(R.id.add_ip);
        removeIp = findViewById(R.id.remove_ip);
        clearIp = findViewById(R.id.clear_ip);

        appSwitch = findViewById(R.id.app_switch);
        appTextView = findViewById(R.id.app_name);
        addApp = findViewById(R.id.add_app);
        removeApp = findViewById(R.id.remove_app);
        clearApp = findViewById(R.id.clear_app);

        rawCmdTextView = findViewById(R.id.raw_cmd);
        executeRawCommand = findViewById(R.id.execute_cmd);

        dataSwitch = findViewById(R.id.data_switch);
        pathTextView = findViewById(R.id.path);
        saveConfig = findViewById(R.id.save_config);
        restoreConfig = findViewById(R.id.restore_config);

        enablePort.setOnClickListener(mEnablePortListener);
        disablePort.setOnClickListener(mDisablePortListener);
        ipSwitch.setOnClickListener(mIpSwitchListener);
        addIp.setOnClickListener(mAddIpListener);
        removeIp.setOnClickListener(mRemoveIpListener);
        clearIp.setOnClickListener(mClearIpListener);
        dataSwitch.setOnClickListener(mDataSwitchListener);
        appSwitch.setOnClickListener(mAppSwitchListener);
        addApp.setOnClickListener(mAddAppListener);
        removeApp.setOnClickListener(mRemoveAppListener);
        clearApp.setOnClickListener(mClearAppListener);
        executeRawCommand.setOnClickListener(mExecuteRawCommandListener);
        saveConfig.setOnClickListener(mSaveConfigListener);
        restoreConfig.setOnClickListener(mRestoreConfigListener);
    }

    private void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        } else {
            //mToast.cancel();
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
}
