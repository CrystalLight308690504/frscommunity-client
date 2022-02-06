package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.contract.RequestCallBack;
import com.crystallightghot.frscommunityclient.contract.SettingContract;
import com.crystallightghot.frscommunityclient.view.util.FRSCOKHttp3RequestUtil;
import com.crystallightghot.frscommunityclient.view.value.RequstIO;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Date 2022/1/25
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class SettingModel implements SettingContract.Model {


    public void unLoginUser(User user, final RequestCallBack callback) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String userJson = gson.toJson(user);
        String url = RequstIO.USER_UNLOGIN.getRequestIO();
        FRSCOKHttp3RequestUtil.deleteRequestWithBodyJson(url,userJson,callback);
    }

}
