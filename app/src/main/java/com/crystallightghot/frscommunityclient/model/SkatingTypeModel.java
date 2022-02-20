package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.view.util.FRSCOKHttp3RequestUtil;
import com.crystallightghot.frscommunityclient.view.value.FRSCRequstIO;

/**
 * @Date 2022/2/11
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class SkatingTypeModel {
    public void loadingSkatingType(Object respondMessageKey) {
        String requestIO = FRSCRequstIO.GET_SKATING_TYPE.getRequestIO();
        FRSCOKHttp3RequestUtil.callGetRequest(requestIO,respondMessageKey);
    }

}
