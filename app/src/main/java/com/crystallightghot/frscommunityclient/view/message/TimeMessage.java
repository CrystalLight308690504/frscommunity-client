package com.crystallightghot.frscommunityclient.view.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Date 2022/1/18
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Getter
@AllArgsConstructor
public class TimeMessage {
    int time;
    int code;

    public static TimeMessage getInstance(int time, int code) {
        return new TimeMessage(time,code);
    }
}
