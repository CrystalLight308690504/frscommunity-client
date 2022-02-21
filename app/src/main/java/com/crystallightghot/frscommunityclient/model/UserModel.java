package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.view.util.FRSCOKHttp3RequestUtil;
import com.crystallightghot.frscommunityclient.view.value.FRSCRequstIO;

/**
 * @Date 2022/2/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class UserModel {
    public <K> void  findUserByNameKey(String searchText, int pagerIndex, K respondMessageKey) {
        String url = FRSCRequstIO.FIND_USERS_BY_NAME_KEY.getRequestIO() + searchText + "/" + pagerIndex;
        FRSCOKHttp3RequestUtil.callGetRequest(url,respondMessageKey);
    }

}
