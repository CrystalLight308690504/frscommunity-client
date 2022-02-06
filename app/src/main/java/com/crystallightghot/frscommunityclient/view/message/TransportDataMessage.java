package com.crystallightghot.frscommunityclient.view.message;

import lombok.Data;

/**
 * @Date 2022/2/5
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Data
public class TransportDataMessage {

    // 消息标识符
    Object messageKey;
    Object data;

    public TransportDataMessage( Object data, Object messageKey) {
        this.messageKey = messageKey;
        this.data = data;
    }
}
