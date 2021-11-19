package com.gxatek.firewall.bean;

import java.util.List;

/**
 * @ProjectName: Firewall
 * @Package: com.gxatek.firewall.bean
 * @ClassName: IfacesRules
 * @Description: java类作用描述
 * @Author: JackOu
 * @CreateDate: 2021/6/16 20:19
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/16 20:19
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class IfacesRules {
    private String iface;
    private String package_name;
    private List<Integer> uid;

    public String getIface() {
        return iface;
    }

    public void setIface(String iface) {
        this.iface = iface;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public List<Integer> getUid() {
        return uid;
    }

    public void setUid(List<Integer> uid) {
        this.uid = uid;
    }
}
