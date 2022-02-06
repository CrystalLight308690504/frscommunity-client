package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.contract.RegisterContract;
import com.crystallightghot.frscommunityclient.contract.RequestCallBack;
import com.crystallightghot.frscommunityclient.view.value.RequstIO;
import com.crystallightghot.frscommunityclient.view.util.FRSCOKHttp3RequestUtil;
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
    public void registerUser(User user, final RequestCallBack callback) {
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        String url = RequstIO.USER_REGISTER.getRequestIO();
        FRSCOKHttp3RequestUtil.postRequestWithBodyJson(url,userJson,callback);
    }
}
