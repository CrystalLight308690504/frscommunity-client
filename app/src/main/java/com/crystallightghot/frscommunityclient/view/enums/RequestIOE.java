package com.crystallightghot.frscommunityclient.view.enums;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public enum RequestIOE {

    USER_Login("/user/login","9002"),
    USER_Register("/user/register","9002"),
    USER_UNLOGIN("/user/logout","9002"),
    MODIFY_USERNAME("/user/modifyUserName","9002"),
    MODIFY_PASSWORD_BY_PHONE_NUMBER("/user/modifyUserPasswordByPhoneNumber","9002"),
    MODIFY_PASSWORD_BY_OLD_PASSWORD("/user/modifyUserPasswordByOldPassword","9002"),
    MODIFY_USER_GENDER("/user/modifyUserGender","9002"),
    MODIFY_USER_DESCRIPTION("/user/modifyUserDescription","9002"),
    MODIFY_USER_PROFILE("/user/modifyUserProfile","9002"),
    MODIFY_USER_EMAIL("/user/modifyUserEmail","9002");

    private String host = "http://192.168.2.101:";
//    private String host = "http://42.194.211.199:";
    private String port;
    private String requestInterface;

    private RequestIOE(String requestInterface, String port) {
        this.requestInterface = requestInterface;
        this.port = port;
    }

    public String getRequestIO() {
        return host + port  + requestInterface;
    }


}
