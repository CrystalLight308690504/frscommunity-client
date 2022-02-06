package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.contract.EditUserGenderContract;
import com.crystallightghot.frscommunityclient.contract.RequestCallBack;
import com.crystallightghot.frscommunityclient.view.value.RequstIO;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCOKHttp3RequestUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Date 2022/1/30
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class EditUserGenderModel implements EditUserGenderContract.Model {
    public void modifyUserGender(User user, RequestCallBack callBack) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String jsonUser = gson.toJson(user);
        String requestIO = RequstIO.MODIFY_USER_GENDER.getRequestIO();
        FRSCOKHttp3RequestUtil.putWithAuthorizationHeader(requestIO,jsonUser,callBack);
    }
}
