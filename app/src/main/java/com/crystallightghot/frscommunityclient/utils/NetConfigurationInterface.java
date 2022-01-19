package com.crystallightghot.frscommunityclient.utils;

import lombok.Getter;

/**
 * @Date 2022/1/19
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Getter
public class NetConfigurationInterface {
    public   static String host = "http://42.194.211.199:";
    public  static String systemPost = "9002";
    public  static String registerUserI = "/user/register";

    public  static String getRegisterUserUrl(){
        return host+systemPost+registerUserI;
    }
}
