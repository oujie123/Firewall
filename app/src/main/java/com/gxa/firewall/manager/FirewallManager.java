package com.gxa.firewall.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;

import com.gxa.firewall.IFirewallManager;

/**
 * @ProjectName: Firewall
 * @Package: com.gxatek.firewall.manager
 * @ClassName: FirewallManager
 * @Description: 防火墙对外客户端
 * @Author: JackOu
 * @CreateDate: 2021/5/19 14:12
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/19 14:12
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FirewallManager {
    private static String TAG = "FirewallManager";
    private static final String SERVICE_PACKAGE_NAME = "com.gxa.firewall";
    private static final String SERVICE_CLASS_NAME = "com.gxa.firewall.service.FirewallService";
    private IFirewallManager mService = null;
    private static FirewallManager sInstance = null;
    private Context mContext = null;

    private final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.d(TAG, "onServiceConnected: className=" + className + ", service=" + service);
            mService = IFirewallManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            Log.d(TAG, "onServiceDisconnected: className=" + className);
            mService = null;
        }
    };

    private FirewallManager(Context context) {
        Log.d(TAG, "FirewallManager enter");
        mContext = context;

        Intent intent = new Intent(IFirewallManager.class.getName());
        intent.setComponent(new ComponentName(SERVICE_PACKAGE_NAME, SERVICE_CLASS_NAME));
        boolean state = mContext.bindServiceAsUser(intent, mConnection, Context.BIND_AUTO_CREATE, UserHandle.CURRENT);
        Log.d(TAG, "FirewallManager exit: " + state);
    }

    /**
     * Get the instance of FirewallManager.
     *
     * @param context The context
     * @return the instance of FirewallManager
     */
    public static synchronized FirewallManager getInstance(Context context) {
        if (sInstance == null) {
            if (context == null) {
                return null;
            }
            sInstance = new FirewallManager(context);
        }

        return sInstance;
    }

    /**
     * Save current iptables rules to the given path.
     * If given path is empty, then use path "/data/iptable-rules" as default.
     *
     * @param path The path to save iptables rules to
     * @return true on success, else false
     */
    public boolean saveCurrentRules(String path) {
        boolean ret = false;
        Log.d(TAG, "saveCurrentRules to " + path);
        try {
            if (mService != null) {
                ret = mService.saveCurrentRules(path);
            } else {
                Log.e(TAG, "IFirewallManager is not available");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "RemoteException " + ex.getMessage());
        }
        return ret;
    }

    /**
     * Restore previous iptables rules from the given path.
     * If given path is empty, then use path "/data/iptable-rules" as default.
     *
     * @param path The path to restore iptables rules from
     * @return true on success, else false
     */
    public boolean restorePreviousRules(String path) {
        boolean ret = false;
        Log.d(TAG, "restorePreviousRules to " + path);
        try {
            if (mService != null) {
                ret = mService.restorePreviousRules(path);
            } else {
                Log.e(TAG, "IFirewallManager is not available");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "RemoteException " + ex.getMessage());
        }
        return ret;
    }

    /**
     * Switch to white/black App list.
     *
     * @param white If true, switch to white list, othewise black list.
     * @return true on success, else false
     */
    public boolean switchToAppWhiteList(boolean white) {
        boolean ret = false;
        Log.d(TAG, "switchToAppWhiteList: " + white);
        try {
            if (mService != null) {
                ret = mService.switchToAppWhiteList(white);
            } else {
                Log.e(TAG, "IFirewallManager is not available");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "RemoteException " + ex.getMessage());
        }
        return ret;
    }

    /**
     * Switch to white/black Ip list.
     *
     * @param white If true, switch to white list, othewise black list.
     * @return true on success, else false
     */
    public boolean switchToIpWhiteList(boolean white) {
        boolean ret = false;
        Log.d(TAG, "switchToIpWhiteList: " + white);
        try {
            if (mService != null) {
                ret = mService.switchToIpWhiteList(white);
            } else {
                Log.e(TAG, "IFirewallManager is not available");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "RemoteException " + ex.getMessage());
        }
        return ret;
    }

    /**
     * Add an IP address to white list.
     *
     * @param ipAddr The ip address
     * @return true on success, else false
     */
    public boolean addIpToWhiteList(String ipAddr) {
        boolean ret = false;
        Log.d(TAG, "addIpToWhiteList " + ipAddr);
        try {
            if (mService != null) {
                ret = mService.addIpToWhiteList(ipAddr);
            } else {
                Log.e(TAG, "IFirewallManager is not available");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "RemoteException " + ex.getMessage());
        }
        return ret;
    }

    /**
     * Remove an IP address from white list.
     *
     * @param ipAddr The ip address
     * @return true on success, else false
     */
    public boolean removeIpFromWhiteList(String ipAddr) {
        boolean ret = false;
        Log.d(TAG, "removeIpFromWhiteList " + ipAddr);
        try {
            if (mService != null) {
                ret = mService.removeIpFromWhiteList(ipAddr);
            } else {
                Log.e(TAG, "IFirewallManager is not available");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "RemoteException " + ex.getMessage());
        }
        return ret;
    }

    /**
     * Clear all IP addresses in white list.
     *
     * @return true on success, else false
     */
    public boolean clearIpInWhiteList() {
        boolean ret = false;
        Log.d(TAG, "clearIpInWhiteList");
        try {
            if (mService != null) {
                ret = mService.clearIpInWhiteList();
            } else {
                Log.e(TAG, "IFirewallManager is not available");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "RemoteException " + ex.getMessage());
        }
        return ret;
    }

    /**
     * Add an IP address to black list.
     *
     * @param ipAddr The ip address
     * @return true on success, else false
     */
    public boolean addIpToBlackList(String ipAddr) {
        boolean ret = false;
        Log.d(TAG, "addIpToBlackList " + ipAddr);
        try {
            if (mService != null) {
                ret = mService.addIpToBlackList(ipAddr);
            } else {
                Log.e(TAG, "IFirewallManager is not available");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "RemoteException " + ex.getMessage());
        }
        return ret;
    }

    /**
     * Remove an IP address to black list.
     *
     * @param ipAddr The ip address
     * @return true on success, else false
     */
    public boolean removeIpFromBlackList(String ipAddr) {
        boolean ret = false;
        Log.d(TAG, "removeIpFromBlackList " + ipAddr);
        try {
            if (mService != null) {
                ret = mService.removeIpFromBlackList(ipAddr);
            } else {
                Log.e(TAG, "IFirewallManager is not available");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "RemoteException " + ex.getMessage());
        }
        return ret;
    }

    /**
     * Clear all IP addresses in black list.
     *
     * @return true on success, else false
     */
    public boolean clearIpInBlackList() {
        boolean ret = false;
        Log.d(TAG, "clearIpInBlackList");
        try {
            if (mService != null) {
                ret = mService.clearIpInBlackList();
            } else {
                Log.e(TAG, "IFirewallManager is not available");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "RemoteException " + ex.getMessage());
        }
        return ret;
    }

    /**
     * Add an app to black list.
     *
     * @param appName The app name
     * @return true on success, else false
     */
    public boolean addAppToWhiteList(String appName) {
        boolean ret = false;
        Log.d(TAG, "addAppToWhiteList " + appName);
        try {
            if (mService != null) {
                ret = mService.addAppToWhiteList(appName);
            } else {
                Log.e(TAG, "IFirewallManager is not available");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "RemoteException " + ex.getMessage());
        }
        return ret;
    }

    /**
     * Remove an app from white list.
     *
     * @param appName The app name
     * @return true on success, else false
     */
    public boolean removeAppFromWhiteList(String appName) {
        boolean ret = false;
        Log.d(TAG, "removeAppFromWhiteList " + appName);
        try {
            if (mService != null) {
                ret = mService.removeAppFromWhiteList(appName);
            } else {
                Log.e(TAG, "IFirewallManager is not available");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "RemoteException " + ex.getMessage());
        }
        return ret;
    }

    /**
     * Clear apps in white list.
     *
     * @return true on success, else false
     */
    public boolean clearAppInWhiteList() {
        boolean ret = false;
        Log.d(TAG, "clearAppInWhiteList");
        try {
            if (mService != null) {
                ret = mService.clearAppInWhiteList();
            } else {
                Log.e(TAG, "IFirewallManager is not available");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "RemoteException " + ex.getMessage());
        }
        return ret;
    }

    /**
     * Add an app to black list.
     *
     * @param appName The app name
     * @return true on success, else false
     */
    public boolean addAppToBlackList(String appName) {
        boolean ret = false;
        Log.d(TAG, "addAppToBlackList " + appName);
        try {
            if (mService != null) {
                ret = mService.addAppToBlackList(appName);
            } else {
                Log.e(TAG, "IFirewallManager is not available");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "RemoteException " + ex.getMessage());
        }
        return ret;
    }

    /**
     * Remove an app from black list.
     *
     * @param appName The app name
     * @return true on success, else false
     */
    public boolean removeAppFromBlackList(String appName) {
        boolean ret = false;
        Log.d(TAG, "removeAppFromBlackList " + appName);
        try {
            if (mService != null) {
                ret = mService.removeAppFromBlackList(appName);
            } else {
                Log.e(TAG, "IFirewallManager is not available");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "RemoteException " + ex.getMessage());
        }
        return ret;
    }

    /**
     * Clear all apps in black list.
     *
     * @return true on success, else false
     */
    public boolean clearAppInBlackList() {
        boolean ret = false;
        Log.d(TAG, "clearAppInBlackList");
        try {
            if (mService != null) {
                ret = mService.clearAppInBlackList();
            } else {
                Log.e(TAG, "IFirewallManager is not available");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "RemoteException " + ex.getMessage());
        }
        return ret;
    }

    /**
     * Enable the port on the given interface.
     *
     * @param iface   The target interface
     * @param portNum The port number to be enabled
     * @return true on success, else false
     */
    public boolean enableInterfacePort(String iface, int portNum) {
        boolean ret = false;
        Log.d(TAG, "enableInterfacePort " + iface + " " + portNum);
        try {
            if (mService != null) {
                ret = mService.enableInterfacePort(iface, portNum);
            } else {
                Log.e(TAG, "IFirewallManager is not available");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "RemoteException " + ex.getMessage());
        }
        return ret;
    }

    /**
     * Disable the port on the given interface.
     *
     * @param iface   The target interface
     * @param portNum The port number to be disabled
     * @return true on success, else false
     */
    public boolean disableInterfacePort(String iface, int portNum) {
        boolean ret = false;
        Log.d(TAG, "disableInterfacePort " + iface + " " + portNum);
        try {
            if (mService != null) {
                ret = mService.disableInterfacePort(iface, portNum);
            } else {
                Log.e(TAG, "IFirewallManager is not available");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "RemoteException " + ex.getMessage());
        }
        return ret;
    }

    /**
     * Enable/Disable mobile data for TBox.
     *
     * @param enable If true, enable data, otherwise, disable data.
     * @return true on success, else false
     */
    public boolean setDataEnabled(boolean enable) {
        boolean ret = false;
        Log.d(TAG, "setDataEnabled " + enable);
        try {
            if (mService != null) {
                ret = mService.setDataEnabled(enable);
            } else {
                Log.e(TAG, "IFirewallManager is not available");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "RemoteException " + ex.getMessage());
        }
        return ret;
    }

    /**
     * Get TBox data status.
     *
     * @return true if enabled, false if disabled
     */
    public boolean getDataStatus() {
        boolean ret = false;
        Log.d(TAG, "getDataStatus");
        try {
            if (mService != null) {
                ret = mService.getDataStatus();
            } else {
                Log.e(TAG, "IFirewallManager is not available");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "RemoteException " + ex.getMessage());
        }
        return ret;
    }

    /**
     * Execute raw command of iptables.
     *
     * @param cmds The commands to be excuted
     * @return true on success, else false
     * @hide
     */
    public boolean executeRawCommand(String[] cmds) {
        boolean ret = false;
        Log.d(TAG, "executeRawCommand: ");
        for (String cmd : cmds) {
            Log.d(TAG, "        " + cmd.toString());
        }
        try {
            if (mService != null) {
                ret = mService.executeRawCommand(cmds);
            } else {
                Log.e(TAG, "IFirewallManager is not available");
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "RemoteException " + ex.getMessage());
        }
        return ret;
    }
}
