package com.gxatek.firewall.bean;

import java.util.List;

/**
 * @ProjectName: Firewall
 * @Package: com.gxatek.firewall.bean
 * @ClassName: FirewallConfig
 * @Description: java类作用描述
 * @Author: JackOu
 * @CreateDate: 2021/6/16 20:02
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/16 20:02
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FirewallConfig {

    private String version;
    private List<String> interfaces;
    private int rule_mode;
    private int log_switch;
    private int stateful_firewall;
    private NetworkManagement network_management;
    private List<NetworkIsolation> network_isolation;
    private List<String> extended_iptables_command;

    public void setVersion(String version) {
        this.version = version;
    }
    public String getVersion() {
        return version;
    }

    public void setInterfaces(List<String> interfaces) {
        this.interfaces = interfaces;
    }
    public List<String> getInterfaces() {
        return interfaces;
    }

    public void setRule_mode(int rule_mode) {
        this.rule_mode = rule_mode;
    }
    public int getRule_mode() {
        return rule_mode;
    }

    public void setLog_switch(int log_switch) {
        this.log_switch = log_switch;
    }
    public int getLog_switch() {
        return log_switch;
    }

    public void setStateful_firewall(int stateful_firewall) {
        this.stateful_firewall = stateful_firewall;
    }
    public int getStateful_firewall() {
        return stateful_firewall;
    }

    public void setNetwork_management(NetworkManagement network_management) {
        this.network_management = network_management;
    }
    public NetworkManagement getNetwork_management() {
        return network_management;
    }

    public void setNetwork_isolation(List<NetworkIsolation> network_isolation) {
        this.network_isolation = network_isolation;
    }

    public List<NetworkIsolation> getNetwork_isolation() {
        return network_isolation;
    }

    public void setExtended_iptables_command(List<String> extended_iptables_command) {
        this.extended_iptables_command = extended_iptables_command;
    }
    public List<String> getExtended_iptables_command() {
        return extended_iptables_command;
    }

    @Override
    public String toString() {
        return "FirewallConfig{" +
                "version='" + version + '\'' +
                ", interfaces=" + interfaces +
                ", rule_mode=" + rule_mode +
                ", log_switch=" + log_switch +
                ", stateful_firewall=" + stateful_firewall +
                ", network_management=" + network_management +
                ", network_isolation=" + network_isolation +
                ", extended_iptables_command=" + extended_iptables_command +
                '}';
    }
}