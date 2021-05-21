// IFirewallManager.aidl
package com.gxatek.firewall;

interface IFirewallManager
{
    /*iptables-save > /tmp/iptable-rules*/
    boolean saveCurrentRules(in @utf8InCpp String path);
    /*iptables-restore < /tmp/iptable-rules*/
    boolean restorePreviousRules(in @utf8InCpp String path);

    boolean switchToAppWhiteList(boolean white);
    boolean switchToIpWhiteList(boolean white);

    /*IP White List*/
    boolean addIpToWhiteList(in @utf8InCpp String ip);
    boolean removeIpFromWhiteList(in @utf8InCpp String ip);
    boolean clearIpInWhiteList();

    /*IP Black List*/
    boolean addIpToBlackList(in @utf8InCpp String ip);
    boolean removeIpFromBlackList(in @utf8InCpp String ip);
    boolean clearIpInBlackList();

    /*App White List*/
    boolean addAppToWhiteList(in @utf8InCpp String appName);
    boolean removeAppFromWhiteList(in @utf8InCpp String appName);
    boolean clearAppInWhiteList();

    /*App Black List*/
    boolean addAppToBlackList(in @utf8InCpp String appName);
    boolean removeAppFromBlackList(in @utf8InCpp String appName);
    boolean clearAppInBlackList();

    /*Port enable/disable*/
    boolean enableInterfacePort(in @utf8InCpp String iface, int portNum);
    boolean disableInterfacePort(in @utf8InCpp String iface, int portNum);

    /*Data enable/disable*/
    boolean setDataEnabled(boolean enable);
    boolean getDataStatus();

    boolean executeRawCommand(in @utf8InCpp String[] cmds);
}

