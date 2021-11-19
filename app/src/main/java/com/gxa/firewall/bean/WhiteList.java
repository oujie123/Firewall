package com.gxatek.firewall.bean;

import java.util.List;

/**
 * @ProjectName: Firewall
 * @Package: com.gxatek.firewall.bean
 * @ClassName: WhiteList
 * @Description: java类作用描述
 * @Author: JackOu
 * @CreateDate: 2021/6/16 20:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/16 20:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class WhiteList {

    private List<IfacesRules> ifaces_rules;
    private List<AppsRules> apps_rules;
    private List<DestinationIp> destination_ip;
    private List<IpPortCombined> ip_port_combined;
    private List<MacRules> mac_rules;
    private List<String> url;

    public void setIfaces_rules(List<IfacesRules> ifaces_rules) {
        this.ifaces_rules = ifaces_rules;
    }
    public List<IfacesRules> getIfaces_rules() {
        return ifaces_rules;
    }

    public void setApps_rules(List<AppsRules> apps_rules) {
        this.apps_rules = apps_rules;
    }
    public List<AppsRules> getApps_rules() {
        return apps_rules;
    }

    public void setDestination_ip(List<DestinationIp> destination_ip) {
        this.destination_ip = destination_ip;
    }
    public List<DestinationIp> getDestination_ip() {
        return destination_ip;
    }

    public void setIp_port_combined(List<IpPortCombined> ip_port_combined) {
        this.ip_port_combined = ip_port_combined;
    }
    public List<IpPortCombined> getIp_port_combined() {
        return ip_port_combined;
    }

    public void setMac_rules(List<MacRules> mac_rules) {
        this.mac_rules = mac_rules;
    }
    public List<MacRules> getMac_rules() {
        return mac_rules;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }
    public List<String> getUrl() {
        return url;
    }
}
