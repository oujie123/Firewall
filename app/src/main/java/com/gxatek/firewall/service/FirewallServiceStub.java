package com.gxatek.firewall.service;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.gxatek.firewall.IFirewallManager;
import com.gxatek.firewall.R;

/**
 * @ProjectName: Firewall
 * @Package: com.gxatek.firewall.service
 * @ClassName: FirewallServiceStub
 * @Description: java类作用描述
 * @Author: JackOu
 * @CreateDate: 2021/5/19 14:28
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/19 14:28
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FirewallServiceStub extends IFirewallManager.Stub{
    private static final String TAG = FirewallServiceStub.class.getSimpleName();
    private FirewallService mService;
    private String defaultConfigPath = null;
    private Context mContext;
    private static String VENDOR_INPUT_CHAIN = "vendor_fw_INPUT";
    private static String VENDOR_OUTPUT_CHAIN = "vendor_fw_OUTPUT";
    //port management
    private static String PORT_INPUT_CHAIN = "fw_port_INPUT";
    private static String PORT_OUTPUT_CHAIN = "fw_port_OUTPUT";
    //white lists
    private static String IP_WHITE_INPUT_CHAIN = "fw_ip_whitelist_INPUT";
    private static String IP_WHITE_OUTPUT_CHAIN = "fw_ip_whitelist_OUTPUT";
    private static String APP_WHITE_INPUT_CHAIN = "fw_app_whitelist_INPUT";
    private static String APP_WHITE_OUTPUT_CHAIN = "fw_app_whitelist_OUTPUT";
    //black lists
    private static String IP_BLACK_INPUT_CHAIN = "fw_ip_blacklist_INPUT";
    private static String IP_BLACK_OUTPUT_CHAIN = "fw_ip_blacklist_OUTPUT";
    private static String APP_BLACK_INPUT_CHAIN = "fw_app_blacklist_INPUT";
    private static String APP_BLACK_OUTPUT_CHAIN = "fw_app_blacklist_OUTPUT";

    private final FirewallDaemonClient mFirewallDaemon = new FirewallDaemonClient();

    public FirewallServiceStub(FirewallService service, Context context) {
        mService = service;
        mContext = context.getApplicationContext();
        if (mContext!= null) {
            defaultConfigPath = mContext.getResources().getString(R.string.defaultPath);
        }
        Log.d(TAG, "FirewallServiceBinder: mService is " + mService);
    }

    @Override
    public boolean saveCurrentRules(String path) {
        Log.d(TAG, "saveCurrentRules: \"" + path + "\"");
        String[] cmds = new String[] {
                "iptables-save > " + (path != null ? path : defaultConfigPath)
        };
        return mFirewallDaemon.execCommands(cmds);
    }

    @Override
    public boolean restorePreviousRules(String path) {
        Log.d(TAG, "restorePreviousRules: \"" + path + "\"");
        String[] cmds = new String[] {
                "iptables-restore < " + (path != null ? path : defaultConfigPath)
        };
        return mFirewallDaemon.execCommands(cmds);
    }

    @Override
    public boolean switchToIpWhiteList(boolean white) {
        Log.d(TAG, "switchToIpWhiteList: " + white);
        String[] cmds = new String[] {
                "iptables -R " + VENDOR_INPUT_CHAIN + " 2 -j "
                        + (white ? IP_WHITE_INPUT_CHAIN : IP_BLACK_INPUT_CHAIN),
                "iptables -R " + VENDOR_OUTPUT_CHAIN + " 2 -j "
                        + (white ? IP_WHITE_OUTPUT_CHAIN : IP_BLACK_OUTPUT_CHAIN)
        };
        return mFirewallDaemon.execCommands(cmds);
    }

    @Override
    public boolean switchToAppWhiteList(boolean white) {
        Log.d(TAG, "switchToAppWhiteList: " + white);
        String[] cmds = new String[] {
                "iptables -R " + VENDOR_INPUT_CHAIN + " 3 -j "
                        + (white ? APP_WHITE_INPUT_CHAIN : APP_BLACK_INPUT_CHAIN),
                "iptables -R " + VENDOR_OUTPUT_CHAIN + " 3 -j "
                        + (white ? APP_WHITE_OUTPUT_CHAIN : APP_BLACK_OUTPUT_CHAIN)
        };
        return mFirewallDaemon.execCommands(cmds);
    }

    @Override
    public boolean addIpToWhiteList(String ipAddr) {
        Log.d(TAG, "addIpToWhiteList: " + ipAddr);
        String[] cmds = new String[] {
                "iptables -A " + IP_WHITE_INPUT_CHAIN + " -s " + ipAddr + " -j ACCEPT",
                "iptables -A " + IP_WHITE_OUTPUT_CHAIN + " -d " + ipAddr + " -j ACCEPT"
        };
        return mFirewallDaemon.execCommands(cmds);
    }

    @Override
    public boolean removeIpFromWhiteList(String ipAddr) {
        Log.d(TAG, "removeIpFromWhiteList: " + ipAddr);
        String[] cmds = new String[] {
                "iptables -D " + IP_WHITE_INPUT_CHAIN + " -s " + ipAddr + " -j ACCEPT",
                "iptables -D " + IP_WHITE_OUTPUT_CHAIN + " -d " + ipAddr + " -j ACCEPT"
        };
        return mFirewallDaemon.execCommands(cmds);
    }

    @Override
    public boolean clearIpInWhiteList() {
        Log.d(TAG, "clearIpInWhiteList");
        String[] cmds = new String[] {
                "iptables -F " + IP_WHITE_INPUT_CHAIN,
                "iptables -F " + IP_WHITE_OUTPUT_CHAIN
        };
        return mFirewallDaemon.execCommands(cmds);
    }

    @Override
    public boolean addIpToBlackList(String ipAddr) {
        Log.d(TAG, "addIpToBlackList: " + ipAddr);
        String[] cmds = new String[] {
                "iptables -A " + IP_BLACK_INPUT_CHAIN + " -s " + ipAddr + " -j DROP",
                "iptables -A " + IP_BLACK_OUTPUT_CHAIN + " -d " + ipAddr + " -j DROP"
        };
        return mFirewallDaemon.execCommands(cmds);
    }

    @Override
    public boolean removeIpFromBlackList(String ipAddr) {
        Log.d(TAG, "removeIpFromBlackList: " + ipAddr);
        String[] cmds = new String[] {
                "iptables -D " + IP_BLACK_INPUT_CHAIN + " -s " + ipAddr + " -j DROP",
                "iptables -D " + IP_BLACK_OUTPUT_CHAIN + " -d " + ipAddr + " -j DROP"
        };
        return mFirewallDaemon.execCommands(cmds);
    }

    @Override
    public boolean clearIpInBlackList() {
        Log.d(TAG, "clearIpInBlackList");
        String[] cmds = new String[] {
                "iptables -F " + IP_BLACK_INPUT_CHAIN,
                "iptables -F " + IP_BLACK_OUTPUT_CHAIN
        };
        return mFirewallDaemon.execCommands(cmds);
    }

    private int getUid(String appName) {
        PackageManager pm;
        ApplicationInfo ai;
        try {
            pm = mContext.getPackageManager();
            ai = pm.getApplicationInfo(appName, 0);
            Log.d(TAG, "app:" + appName + ", uid:" + ai.uid);
        } catch (PackageManager.NameNotFoundException exception) {
            Log.e(TAG, "addAppToBlackList: Can't find app: " + appName);
            return 0;
        }
        return ai.uid;
    }

    @Override
    public boolean addAppToWhiteList(String appName) {
        Log.d(TAG, "addAppToWhiteList: " + appName);
        int uid = getUid(appName);
        if (uid == 0) {
            return false;
        }

        String[] cmds = new String[] {
                "iptables -A " + APP_WHITE_INPUT_CHAIN + " -m owner --uid-owner " + uid + " -j ACCEPT",
                "iptables -A " + APP_WHITE_OUTPUT_CHAIN + " -m owner --uid-owner " + uid + " -j ACCEPT"
        };
        return mFirewallDaemon.execCommands(cmds);
    }

    @Override
    public boolean removeAppFromWhiteList(String appName) {
        Log.d(TAG, "removeAppFromWhiteList: " + appName);
        int uid = getUid(appName);
        if (uid == 0) {
            return false;
        }

        String[] cmds = new String[] {
                "iptables -D " + APP_WHITE_INPUT_CHAIN + " -m owner --uid-owner " + uid + " -j ACCEPT",
                "iptables -D " + APP_WHITE_OUTPUT_CHAIN + " -m owner --uid-owner " + uid + " -j ACCEPT"
        };
        return mFirewallDaemon.execCommands(cmds);
    }

    @Override
    public boolean clearAppInWhiteList() {
        Log.d(TAG, "clearAppInWhiteList");
        String[] cmds = new String[] {
                "iptables -F " + APP_WHITE_INPUT_CHAIN,
                "iptables -F " + APP_WHITE_OUTPUT_CHAIN
        };
        return mFirewallDaemon.execCommands(cmds);
    }

    @Override
    public boolean addAppToBlackList(String appName) {
        Log.d(TAG, "addAppToBlackList: " + appName);
        int uid = getUid(appName);
        if (uid == 0) {
            return false;
        }

        String[] cmds = new String[] {
                "iptables -A " + APP_BLACK_INPUT_CHAIN + " -m owner --uid-owner " + uid + " -j DROP",
                "iptables -A " + APP_BLACK_OUTPUT_CHAIN + " -m owner --uid-owner " + uid + " -j DROP"
        };
        return mFirewallDaemon.execCommands(cmds);
    }

    @Override
    public boolean removeAppFromBlackList(String appName) {
        Log.d(TAG, "removeAppFromBlackList: " + appName);
        int uid = getUid(appName);
        if (uid == 0) {
            return false;
        }

        String[] cmds = new String[] {
                "iptables -D " + APP_BLACK_INPUT_CHAIN + " -m owner --uid-owner " + uid + " -j DROP",
                "iptables -D " + APP_BLACK_OUTPUT_CHAIN + " -m owner --uid-owner " + uid + " -j DROP"
        };
        return mFirewallDaemon.execCommands(cmds);
    }

    @Override
    public boolean clearAppInBlackList() {
        Log.d(TAG, "clearAppInBlackList");
        String[] cmds = new String[] {
                "iptables -F " + APP_BLACK_INPUT_CHAIN,
                "iptables -F " + APP_BLACK_OUTPUT_CHAIN
        };
        return mFirewallDaemon.execCommands(cmds);
    }

    @Override
    public boolean enableInterfacePort(String iface, int portNum) {
        Log.d(TAG, "enableInterfacePort: " + iface + ", " + portNum);
        String[] cmds = new String[] {
                "iptables -A " + PORT_INPUT_CHAIN + " -i " + iface + " -p tcp --dport " + portNum + " -j ACCEPT",
                "iptables -A " + PORT_INPUT_CHAIN + " -i " + iface + " -p udp --dport " + portNum + " -j ACCEPT",
                "iptables -A " + PORT_OUTPUT_CHAIN + " -i " + iface + " -p tcp --sport " + portNum + " -j ACCEPT",
                "iptables -A " + PORT_OUTPUT_CHAIN + " -i " + iface + " -p udp --sport " + portNum + " -j ACCEPT"
        };
        return mFirewallDaemon.execCommands(cmds);
    }

    @Override
    public boolean disableInterfacePort(String iface, int portNum) {
        Log.d(TAG, "disableInterfacePort: " + iface + ", " + portNum);
        String[] cmds = new String[] {
                "iptables -D " + PORT_INPUT_CHAIN + " -i " + iface + " -p tcp --dport " + portNum + " -j ACCEPT",
                "iptables -D " + PORT_INPUT_CHAIN + " -i " + iface + " -p udp --dport " + portNum + " -j ACCEPT",
                "iptables -D " + PORT_OUTPUT_CHAIN + " -i " + iface + " -p tcp --sport " + portNum + " -j ACCEPT",
                "iptables -D " + PORT_OUTPUT_CHAIN + " -i " + iface + " -p udp --sport " + portNum + " -j ACCEPT"
        };
        return mFirewallDaemon.execCommands(cmds);
    }

    @Override
    public boolean setDataEnabled(boolean enable) {
        Log.d(TAG, "setDataEnabled " + enable);
        String[] cmds = new String[] {
                "iptables " + (enable ? "-A " : "-D ")
                        + VENDOR_INPUT_CHAIN + " -i eth1 ! -s 2.2.2.2 -j ACCEPT",
                "iptables " + (enable ? "-A " : "-D ")
                        + VENDOR_OUTPUT_CHAIN + " -o eth1 ! -d 2.2.2.2 -j ACCEPT"
        };
        return mFirewallDaemon.execCommands(cmds);
    }

    @Override
    public boolean getDataStatus() {
        Log.d(TAG, "getDataStatus");
        String[] cmds = new String[] {
                "iptables -C " + VENDOR_INPUT_CHAIN + " -i eth1 ! -s 2.2.2.2 -j ACCEPT",
                "iptables -C " + VENDOR_OUTPUT_CHAIN + " -o eth1 ! -d 2.2.2.2 -j ACCEPT"
        };
        return mFirewallDaemon.execCommands(cmds);
    }

    public boolean executeRawCommand(String[] cmds) {
        Log.d(TAG, "executeRawCommand: ");
        for (String cmd : cmds) {
            Log.d(TAG, "        " + cmd.toString());
        }
        return mFirewallDaemon.execCommands(cmds);
    }
}

