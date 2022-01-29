package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.contract.RespondCallBck;
import com.crystallightghot.frscommunityclient.contract.SettingContract;
import com.crystallightghot.frscommunityclient.view.util.OKHttpRequestUtil;
import com.crystallightghot.frscommunityclient.view.enums.RequestIOE;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.google.gson.Gson;

/**
 * @Date 2022/1/25
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class SettingModel implements SettingContract.Model {


    public void unLoginUser(User user, final RespondCallBck callback) {
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        String url = RequestIOE.USER_UNLOGIN.getRequestIO();
        OKHttpRequestUtil.deleteRequestWithBodyJson(url,userJson,callback);
    }

}
