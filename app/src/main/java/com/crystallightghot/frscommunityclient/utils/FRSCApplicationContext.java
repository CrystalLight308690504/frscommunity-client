package com.crystallightghot.frscommunityclient.utils;

import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import lombok.Data;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */

@Data
public class FRSCApplicationContext {
    static FRSCApplicationContext applicationContext;
    User user;

    public static FRSCApplicationContext getInstance() {
        if (null == applicationContext) {
            applicationContext = new FRSCApplicationContext();
        }
        return applicationContext;
    }
}
