package com.crystallightghot.frscommunityclient.utils;

import java.util.Random;

/**
 * @Date 2022/1/19
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class VerifyCodeUtil {
    public  static String getVerifyCode(){
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int code = random.nextInt(9);
            stringBuffer.append(code);
        }
        return stringBuffer.toString();
    }
}
