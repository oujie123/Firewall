package com.gxatek.firewall.bean;

import java.util.List;

/**
 * @ProjectName: Firewall
 * @Package: com.gxatek.firewall.bean
 * @ClassName: IpPortCombined
 * @Description: java类作用描述
 * @Author: JackOu
 * @CreateDate: 2021/6/16 21:22
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/16 21:22
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class IpPortCombined {
    private List<Integer> port;
    private String source_ip;
    private String iface;

    public List<Integer> getPort() {
        return port;
    }

    public void setPort(List<Integer> port) {
        this.port = port;
    }

    public String getSource_ip() {
        return source_ip;
    }

    public void setSource_ip(String source_ip) {
        this.source_ip = source_ip;
    }

    public String getIface() {
        return iface;
    }

    public void setIface(String iface) {
        this.iface = iface;
    }
}
