package com.crystallightghot.frscommunityclient.view.message;

import com.crystallightghot.frscommunityclient.view.pojo.system.RequestResult;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Data
@NoArgsConstructor
public class RequestMessage<K> {
    String message;
    Object data;
    boolean success;
    K messageKey;

    public RequestMessage(RequestResult requestResult, K messageKey) {
        this.message = requestResult.getMessage();
        this.success = requestResult.isSuccess();
        this.data =  requestResult.getData();
        this.messageKey = messageKey;
    }

}
