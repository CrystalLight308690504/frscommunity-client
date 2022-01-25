package com.crystallightghot.frscommunityclient.view.util;

import android.app.Activity;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import com.crystallightghot.frscommunityclient.utils.EventBusUtil;
import com.crystallightghot.frscommunityclient.utils.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.activity.SingleFragmentActivity;
import com.crystallightghot.frscommunityclient.view.message.FragmentChangeMessage;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 *  跳转到 SingleFragmentActivity 的 工具类  SingleFragmentActivity要注册接收DefaultFragment 的Event事件 ${SingleFragmentActivity.getMessage()}
 */
public class FRSCIntentUtil {

    public static final void intentToSingleFragmentActivity(Fragment defaultFragment) {

        Activity activity = FRSCApplicationContext.getActivity();
        Intent intent = new Intent(activity,SingleFragmentActivity.class);
        activity.startActivity(intent);
        FragmentChangeMessage fragmentChangeMessage = new FragmentChangeMessage();
        fragmentChangeMessage.setCode(SingleFragmentActivity.MESSAGE_COD);
        fragmentChangeMessage.setDefaultFragment(defaultFragment);

        // 发送默认activity消息
        EventBusUtil.sendStickMessage(fragmentChangeMessage);
    }
}
