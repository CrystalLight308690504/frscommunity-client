package com.crystallightghot.frscommunityclient.view.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.crystallightghot.frscommunityclient.view.activity.MainActivity;

public class HomeViewPagerItemScrollChangedReceiver extends BroadcastReceiver {

    MainActivity activity;

    public HomeViewPagerItemScrollChangedReceiver() {
    }

    public HomeViewPagerItemScrollChangedReceiver( MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (null == extras || null ==activity) { // 表示未滑动 不改变状态
            return;
        }

        boolean isScrollUpward = (boolean) extras.get("isScrollUpward");

        if (isScrollUpward) {
            activity.bottomNavigationAndAddIconState(false);
            activity.bottomItemIsVisible(false);
        } else {
            activity.bottomNavigationAndAddIconState(true);
            activity.bottomItemIsVisible(true);
        }
    }
}