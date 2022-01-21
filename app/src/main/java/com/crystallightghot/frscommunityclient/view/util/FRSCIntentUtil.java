package com.crystallightghot.frscommunityclient.view.util;

import android.app.Activity;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import com.crystallightghot.frscommunityclient.utils.EventBusUtil;
import com.crystallightghot.frscommunityclient.view.activity.SingleFragmentActivity;
import com.crystallightghot.frscommunityclient.view.message.FragmentChangeMessage;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class FRSCIntentUtil {

    public static final void IntentToSingleFragmentActivity(Activity activity, Fragment defaultFragment) {
        Intent intent = new Intent(activity, SingleFragmentActivity.class);
        activity.startActivity(intent);
        FragmentChangeMessage fragmentChangeMessage = new FragmentChangeMessage();
        fragmentChangeMessage.setCode(SingleFragmentActivity.MESSAGE_COD);
        fragmentChangeMessage.setDefaultFragment(defaultFragment);
        EventBusUtil.sendStickMessage(fragmentChangeMessage);
    }
}
