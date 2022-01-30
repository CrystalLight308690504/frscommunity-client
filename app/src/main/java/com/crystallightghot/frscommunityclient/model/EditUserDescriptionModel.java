package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.contract.EditUserDescriptionContract;
import com.crystallightghot.frscommunityclient.contract.RequestCallBack;
import com.crystallightghot.frscommunityclient.presenter.EditUserDescriptionPresenter;
import com.crystallightghot.frscommunityclient.view.enums.FRSCRequestIOE;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCOKHttpRequestUtil;
import com.google.gson.Gson;

/**
 * @Date 2022/1/30
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class EditUserDescriptionModel implements EditUserDescriptionContract.Model {
    public EditUserDescriptionModel(EditUserDescriptionPresenter editUserDescriptionPresenter) {

    }

    public void modifyUserDescription(User user, RequestCallBack callBack) {
        Gson gson = new Gson();
        String jsonUser = gson.toJson(user);
        String requestIO = FRSCRequestIOE.MODIFY_USER_DESCRIPTION.getRequestIO();
        FRSCOKHttpRequestUtil.putWithAuthorizationHeader(requestIO,jsonUser,callBack);
    }
}