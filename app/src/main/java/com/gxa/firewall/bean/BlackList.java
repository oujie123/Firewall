package com.gxatek.firewall.bean;

import java.util.List;

/**
 * @ProjectName: Firewall
 * @Package: com.gxatek.firewall.bean
 * @ClassName: BlackList
 * @Description: java类作用描述
 * @Author: JackOu
 * @CreateDate: 2021/6/16 21:25
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/16 21:25
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BlackList {

    private List<Integer> port;
    private List<String> ip;
    private List<String> url;
    private List<Integer> uid;
    private List<String> mac;

    public List<Integer> getPort() {
        return port;
    }

    public void setPort(List<Integer> port) {
        this.port = port;
    }

    public List<String> getIp() {
        return ip;
    }

    public void setIp(List<String> ip) {
        this.ip = ip;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public List<Integer> getUid() {
        return uid;
    }

    public void setUid(List<Integer> uid) {
        this.uid = uid;
    }

    public List<String> getMac() {
        return mac;
    }

    public void setMac(List<String> mac) {
        this.mac = mac;
    }

    @Override
    public String toString() {
        return "BlackList{" +
                "port=" + port +
                ", ip=" + ip +
                ", url=" + url +
                ", uid=" + uid +
                ", mac=" + mac +
                '}';
    }
}
