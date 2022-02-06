package com.crystallightghot.frscommunityclient.view.message;

import com.crystallightghot.frscommunityclient.view.value.MessageCode;
import lombok.Data;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Data
public class BaseMessage {
    // 唯一标识符
    MessageCode messageCode;
    String message;
}
