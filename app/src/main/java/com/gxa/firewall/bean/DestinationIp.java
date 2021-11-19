package com.gxatek.firewall.bean;

/**
 * @ProjectName: Firewall
 * @Package: com.gxatek.firewall.bean
 * @ClassName: DestinationIp
 * @Description: java类作用描述
 * @Author: JackOu
 * @CreateDate: 2021/6/16 21:20
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/16 21:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DestinationIp {

    private String ip;
    private String iface;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIface() {
        return iface;
    }

    public void setIface(String iface) {
        this.iface = iface;
    }
}
