package com.crystallightghot.frscommunityclient.activity.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.crystallightghot.frscommunityclient.activity.HomeActivity;
import com.crystallightghot.frscommunityclient.activity.fragment.HomeFragment;

public class HomeViewPagerItemScrollChangedReceiver extends BroadcastReceiver {

    HomeFragment homeFragment;

    public HomeViewPagerItemScrollChangedReceiver() {
    }

    ;

    public HomeViewPagerItemScrollChangedReceiver(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        HomeActivity activity = (HomeActivity) homeFragment.getActivity();
        if (null == extras) { // 表示未滑动 不改变状态
            return;
        }

        boolean isScrollUpward = (boolean) extras.get("isScrollUpward");
//        Log.d("测试", "onReceive: 接受广播 是否下滑" + isScrollUpward);

        if (isScrollUpward) {
            homeFragment.addIconIsShowed(false);
            activity.bottomItemIsVisible(false);
        } else {
            homeFragment.addIconIsShowed(true);
            activity.bottomItemIsVisible(true);
        }
    }
}