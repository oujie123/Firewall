package com.gxatek.firewall.bean;

import java.util.List;

/**
 * @ProjectName: Firewall
 * @Package: com.gxatek.firewall.bean
 * @ClassName: AppsRules
 * @Description: java类作用描述
 * @Author: JackOu
 * @CreateDate: 2021/6/16 21:15
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/16 21:15
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AppsRules {
    private List<Integer> uid;
    private String package_name;
    private List<String> url;
    private List<String> ip;
    private List<Integer> port;
    private String iface;

    public List<Integer> getUid() {
        return uid;
    }

    public void setUid(List<Integer> uid) {
        this.uid = uid;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public List<String> getIp() {
        return ip;
    }

    public void setIp(List<String> ip) {
        this.ip = ip;
    }

    public List<Integer> getPort() {
        return port;
    }

    public void setPort(List<Integer> port) {
        this.port = port;
    }

    public String getIface() {
        return iface;
    }

    public void setIface(String iface) {
        this.iface = iface;
    }
}
