package com.crystallightghot.frscommunityclient.view.value;

/**
 * @Date 2022/2/5
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public enum FRSCString {

    SERVICE_UN_ONLINE ("(ಥ﹏ಥ)服务器跑路了");

    private String string;
    private FRSCString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
