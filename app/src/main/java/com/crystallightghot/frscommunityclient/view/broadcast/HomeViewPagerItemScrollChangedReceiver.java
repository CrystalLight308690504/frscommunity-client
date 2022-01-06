package com.crystallightghot.frscommunityclient.view.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.crystallightghot.frscommunityclient.view.activity.HomeActivity;

public class HomeViewPagerItemScrollChangedReceiver extends BroadcastReceiver {

    HomeActivity activity;

    public HomeViewPagerItemScrollChangedReceiver() {
    }

    ;

    public HomeViewPagerItemScrollChangedReceiver( HomeActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (null == extras) { // 表示未滑动 不改变状态
            return;
        }

        boolean isScrollUpward = (boolean) extras.get("isScrollUpward");
//        Log.d("测试", "onReceive: 接受广播 是否下滑" + isScrollUpward);

        if (null == null){
            return;
        }
        if (isScrollUpward) {
            activity.addIconIsShowed(false);
            activity.bottomItemIsVisible(false);
        } else {
            activity.addIconIsShowed(true);
            activity.bottomItemIsVisible(true);
        }
    }
}