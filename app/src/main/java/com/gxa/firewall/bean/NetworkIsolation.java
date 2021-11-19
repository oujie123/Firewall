package com.gxatek.firewall.bean;

import java.util.List;

/**
 * @ProjectName: Firewall
 * @Package: com.gxatek.firewall.bean
 * @ClassName: NetworkIsolation
 * @Description: java类作用描述
 * @Author: JackOu
 * @CreateDate: 2021/6/16 20:13
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/16 20:13
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class NetworkIsolation {

    private List<String> source_iface;
    private List<String> destination_iface;

    public List<String> getSource_iface() {
        return source_iface;
    }

    public void setSource_iface(List<String> source_iface) {
        this.source_iface = source_iface;
    }

    public List<String> getDestination_iface() {
        return destination_iface;
    }

    public void setDestination_iface(List<String> destination_iface) {
        this.destination_iface = destination_iface;
    }
}
