package com.crystallightghot.frscommunityclient.view.messageEvent;

import lombok.Data;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Data
public class BaseMessage<T> {
    String message;
    T data;
    boolean success = true;
}
