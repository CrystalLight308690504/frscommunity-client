package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.contract.PutBlogConfirmContract;
import com.crystallightghot.frscommunityclient.presenter.PutBlogConfirmPresenter;
import com.crystallightghot.frscommunityclient.view.util.FRSCOKHttp3RequestUtil;
import com.crystallightghot.frscommunityclient.view.value.RequstIO;

/**
 * @Date 2022/2/6
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class PutBlogConfirmModel implements PutBlogConfirmContract.Model {
    public void loadingCategory(Long userId) {
        String requestIO = RequstIO.FIND_CATEGORIES_BY_USERID.getRequestIO() + userId;
        FRSCOKHttp3RequestUtil.callGetRequest(requestIO, PutBlogConfirmPresenter.MessageRespondKey.BLOG_CATEGORIES);
    }

    public void loadingSkatingType() {
        String requestIO = RequstIO.GET_SKATING_TYPE.getRequestIO();
        FRSCOKHttp3RequestUtil.callGetRequest(requestIO,PutBlogConfirmPresenter.MessageRespondKey.SKATING_TYPES);
    }
}
