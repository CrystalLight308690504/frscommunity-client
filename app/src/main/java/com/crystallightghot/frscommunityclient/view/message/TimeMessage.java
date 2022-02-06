package com.crystallightghot.frscommunityclient.view.message;

import com.crystallightghot.frscommunityclient.view.value.MessageCode;
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
    MessageCode code;

    public static TimeMessage getInstance(int time, MessageCode code) {
        return new TimeMessage(time,code);
    }
}
