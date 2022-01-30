package com.crystallightghot.frscommunityclient.view.message;

import com.crystallightghot.frscommunityclient.view.enums.FRSCRequestIOE;
import com.crystallightghot.frscommunityclient.view.enums.MessageCode;
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
    MessageCode messageCode;
    FRSCRequestIOE requestIO;

    public RequestMessage(RequestResult requestResult, MessageCode messageCode) {
        this.message = requestResult.getMessage();
        this.success = requestResult.isSuccess();
        this.messageCode = messageCode;
        this.data = (T) requestResult.getData();
    }

    public RequestMessage(RequestResult requestResult, FRSCRequestIOE requestIO) {
        this.message = requestResult.getMessage();
        this.success = requestResult.isSuccess();
        this.requestIO = requestIO;
        this.data = (T) requestResult.getData();
    }

}
