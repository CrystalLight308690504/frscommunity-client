package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.contract.LoginContract;
import com.crystallightghot.frscommunityclient.contract.RespondCallBck;
import com.crystallightghot.frscommunityclient.utils.requestio.RequestIOE;
import com.crystallightghot.frscommunityclient.utils.requestio.RequestUtil;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class LoginModel implements LoginContract.Model {


    public LoginModel() {
    }

    public void login(String userJson , final RespondCallBck callback) {
        String url = RequestIOE.USER_Login.getRequestIO();
        RequestUtil.postRequestWithBodyJson(url,userJson,callback);
    }
}
