package com.crystallightghot.frscommunityclient.view.util;

import org.greenrobot.eventbus.EventBus;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class FRSCEventBusUtil {
    public static final void sendMessage(Object o) {
        EventBus.getDefault().post(o);
    }

    public static final void sendStickMessage(Object o) {
        EventBus.getDefault().postSticky(o);
    }

    public static final void register(Object o) {
        EventBus.getDefault().register(o);
    }

    public static final void unregister(Object o) {
        EventBus.getDefault().unregister(o);
    }
}
