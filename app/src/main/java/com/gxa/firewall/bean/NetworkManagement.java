package com.gxatek.firewall.bean;

/**
 * @ProjectName: Firewall
 * @Package: com.gxatek.firewall.bean
 * @ClassName: NetworkManagement
 * @Description: java类作用描述
 * @Author: JackOu
 * @CreateDate: 2021/6/16 20:15
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/6/16 20:15
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class NetworkManagement {

    private WhiteList white_list;
    private BlackList black_list;

    public void setWhite_list(WhiteList white_list) {
        this.white_list = white_list;
    }
    public WhiteList getWhite_list() {
        return white_list;
    }

    public void setBlack_list(BlackList black_list) {
        this.black_list = black_list;
    }
    public BlackList getBlack_list() {
        return black_list;
    }
}
