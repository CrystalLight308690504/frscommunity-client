package com.crystallightghot.frscommunityclient.view.messageEvent;

import lombok.Data;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Data
public class UIChangeMessage<T> extends BaseMessage<T> {
    int code;
}
