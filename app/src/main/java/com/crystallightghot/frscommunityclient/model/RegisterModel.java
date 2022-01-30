package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.contract.RegisterContract;
import com.crystallightghot.frscommunityclient.contract.RespondCallBck;
import com.crystallightghot.frscommunityclient.view.enums.RequestIOE;
import com.crystallightghot.frscommunityclient.view.util.FRSCOKHttpRequestUtil;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.google.gson.Gson;

/**
 * @Date 2022/1/19
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class RegisterModel implements RegisterContract.Model {


    @Override
    public void registerUser(User user, final RespondCallBck callback) {

        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        String url = RequestIOE.USER_Register.getRequestIO();
        FRSCOKHttpRequestUtil.postRequestWithBodyJson(url,userJson,callback);
    }
}
