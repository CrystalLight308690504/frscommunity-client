package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.view.util.FRSCOKHttp3RequestUtil;
import com.crystallightghot.frscommunityclient.view.value.RequstIO;

/**
 * @Date 2022/2/7
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class BlogModel {

    public void deleteBlog (String requestBody,Object respondMessageKey) {
        String url = RequstIO.DELETE_BLOG_CATEGORY.getRequestIO();
        FRSCOKHttp3RequestUtil.callDeleteRequest(url, requestBody, respondMessageKey);
    }

}
