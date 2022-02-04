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
public class RequestMessage<T> {
    String message;
    T data;
    boolean success;
    Object messageCode;

    public RequestMessage(RequestResult<T> requestResult,Object messageCode) {
        this.message = requestResult.getMessage();
        this.success = requestResult.isSuccess();
        this.data =  requestResult.getData();
        this.messageCode = messageCode;
    }

}
