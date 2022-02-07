package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.contract.MyBlogContract;
import com.crystallightghot.frscommunityclient.presenter.MyBlogPresenter;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCategory;
import com.crystallightghot.frscommunityclient.view.util.FRSCOKHttp3RequestUtil;
import com.crystallightghot.frscommunityclient.view.value.RequstIO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Date 2022/2/4
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class MyBlogModel implements MyBlogContract.Model {


    public void loadingCategory(Long userId) {
        String requestIO = RequstIO.FIND_CATEGORIES_BY_USERID.getRequestIO() + userId;
        FRSCOKHttp3RequestUtil.callGetRequest(requestIO, MyBlogPresenter.RespondMessageKey.LOADING_BLOG_CATEGORIES);
    }

    public void loadingBlogs(Long userId, Long blogCategoryId) {
        String url = RequstIO.FIND_BLOGS_BY_USERID_AND_CATEGORY.getRequestIO() + userId + "/" + blogCategoryId;
        FRSCOKHttp3RequestUtil.callGetRequest(url, MyBlogPresenter.RespondMessageKey.LOADING_BLOGS);

    }

    public void addBlogCategory(BlogCategory category) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        String categoryJson = gson.toJson(category);
        String url = RequstIO.ADD_BLOG_CATEGORY.getRequestIO();
        FRSCOKHttp3RequestUtil.callPostRequest(url, categoryJson, MyBlogPresenter.RespondMessageKey.ADD_BLOG_CATEGORY);
    }

    public void modifyCategory(String jsonBody, Object respondMessageKey) {
        String url = RequstIO.MODIFY_BLOG_CATEGORY.getRequestIO();
        FRSCOKHttp3RequestUtil.callPutRequest(url,jsonBody, respondMessageKey);
    }
}
