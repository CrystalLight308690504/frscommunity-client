package com.crystallightghot.frscommunityclient.view.util;

import android.app.Activity;
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;
import com.crystallightghot.frscommunityclient.view.activity.MainActivity;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */

public class FRSCApplicationContext {

    private static Activity activity;
    private static BaseFragmentActivity baseFragmentActivity;

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    public static void setMainActivity(MainActivity mainActivity) {
        FRSCApplicationContext.mainActivity = mainActivity;
    }

    private static MainActivity mainActivity;
    private  static User user;

    public static Activity getActivity() {
        return activity;
    }

    public static void setActivity(Activity activity) {
        FRSCApplicationContext.activity = activity;
    }

    public static BaseFragmentActivity getBaseFragmentActivity() {
        return baseFragmentActivity;
    }

    public static void setBaseFragmentActivity(BaseFragmentActivity baseFragmentActivity) {
        FRSCApplicationContext.baseFragmentActivity = baseFragmentActivity;
    }

    public static User getUser() {
        return user;
    }

    public static void  setUser(User user) {
        FRSCApplicationContext.user = user;
    }



}
