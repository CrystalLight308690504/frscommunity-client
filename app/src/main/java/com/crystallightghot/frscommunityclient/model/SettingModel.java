package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.contract.RequestCallBack;
import com.crystallightghot.frscommunityclient.contract.SettingContract;
import com.crystallightghot.frscommunityclient.view.util.FRSCOKHttpRequestUtil;
import com.crystallightghot.frscommunityclient.view.enums.FRSCRequestIOE;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.google.gson.Gson;

/**
 * @Date 2022/1/25
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class SettingModel implements SettingContract.Model {


    public void unLoginUser(User user, final RequestCallBack callback) {
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        String url = FRSCRequestIOE.USER_UNLOGIN.getRequestIO();
        FRSCOKHttpRequestUtil.deleteRequestWithBodyJson(url,userJson,callback);
    }

}
