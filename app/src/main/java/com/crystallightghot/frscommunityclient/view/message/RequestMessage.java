package com.crystallightghot.frscommunityclient.view.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class RequestMessage<T>{
    // 唯一标识符
    int code;
    String message;
    T data;
    boolean success = true;
}
