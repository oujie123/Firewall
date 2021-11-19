package com.gxatek.firewall.bean;

import java.util.List;

/**
 * @ProjectName: Firewall
 * @Package: com.gxatek.firewall.bean
 * @ClassName: MacRules
 * @Description: java类作用描述
 * @Author: JackOu
 * @CreateDate: 2021/6/16 21:24
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/16 21:24
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MacRules {

    private List<String> allowed_mac;
    private List<String> prohibited_mac;
    private String iface;

    public List<String> getAllowed_mac() {
        return allowed_mac;
    }

    public void setAllowed_mac(List<String> allowed_mac) {
        this.allowed_mac = allowed_mac;
    }

    public List<String> getProhibited_mac() {
        return prohibited_mac;
    }

    public void setProhibited_mac(List<String> prohibited_mac) {
        this.prohibited_mac = prohibited_mac;
    }

    public String getIface() {
        return iface;
    }

    public void setIface(String iface) {
        this.iface = iface;
    }
}
