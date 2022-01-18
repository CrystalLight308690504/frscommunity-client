package com.crystallightghot.frscommunityclient.view.messageEvent;

import lombok.Getter;

/**
 * @Date 2022/1/18
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Getter
public class VerifyCodeMessageEvent {
    int time;

    public VerifyCodeMessageEvent(int time) {
        this.time = time;
    }

}
