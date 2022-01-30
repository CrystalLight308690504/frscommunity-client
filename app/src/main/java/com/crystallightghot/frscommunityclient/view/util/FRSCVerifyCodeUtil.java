package com.crystallightghot.frscommunityclient.view.util;

import java.util.Random;

/**
 * @Date 2022/1/19
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class FRSCVerifyCodeUtil {
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
