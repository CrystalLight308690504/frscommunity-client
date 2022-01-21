package com.crystallightghot.frscommunityclient.contract;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public interface RespondCallBck {
        void success(String respondMessage,Object respondData);
        void failure(String failureMessage);
}
