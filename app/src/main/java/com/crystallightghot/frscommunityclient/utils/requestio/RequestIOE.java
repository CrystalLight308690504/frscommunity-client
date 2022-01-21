package com.crystallightghot.frscommunityclient.utils.requestio;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public enum RequestIOE {

    USER_Login("user/login","9002"),
    USER_Register("user/register","9002");

    private String host = "http://42.194.211.199:";
    private String port;
    private String requestInterface;

    private RequestIOE(String requestInterface, String port) {
        this.requestInterface = requestInterface;
        this.port = port;
    }

    public String getRequestIO() {
        return host + port + "/" + requestInterface;
    }


}
