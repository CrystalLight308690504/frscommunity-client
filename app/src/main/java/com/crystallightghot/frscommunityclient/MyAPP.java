package com.crystallightghot.frscommunityclient;

import android.app.Application;
import com.xuexiang.xui.XUI;

/**
 * @Date 2022/1/23
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class MyAPP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XUI.init(this);
        XUI.debug(true);
    }
}
