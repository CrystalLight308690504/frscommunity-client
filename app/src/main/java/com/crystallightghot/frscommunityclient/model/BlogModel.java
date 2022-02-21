package com.crystallightghot.frscommunityclient.model;

import android.view.View;
import com.crystallightghot.frscommunityclient.presenter.MyBlogPresenter;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCategory;
import com.crystallightghot.frscommunityclient.view.util.FRSCOKHttp3RequestUtil;
import com.crystallightghot.frscommunityclient.view.value.FRSCRequstIO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Date 2022/2/7
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class BlogModel {

    public void deleteBlog(String requestBody, Object respondMessageKey) {
        String url = FRSCRequstIO.DELETE_BLOG.getRequestIO();
        FRSCOKHttp3RequestUtil.callDeleteRequest(url, requestBody, respondMessageKey);
    }

    public void loadSearchBlogResult(String searchText, int pagerIndex , Object respondMessageKey) {
        String url = FRSCRequstIO.FIND_BLOGS_BY_SEARCH_KEY.getRequestIO() + searchText + "/" + pagerIndex;
        FRSCOKHttp3RequestUtil.callGetRequest(url,respondMessageKey);
    }

    public void deleteBlogCategory(String blogCategoryJson, Object respondMessageKey) {
        String url = FRSCRequstIO.DELETE_BLOG_CATEGORY.getRequestIO();
        FRSCOKHttp3RequestUtil.callPutRequest(url,blogCategoryJson,respondMessageKey);
    }
    public void loadingCategory(Long userId) {
        String requestIO = FRSCRequstIO.FIND_CATEGORIES_BY_USERID.getRequestIO() + userId;
        FRSCOKHttp3RequestUtil.callGetRequest(requestIO, MyBlogPresenter.RespondMessageKey.LOADING_BLOG_CATEGORIES);
    }

    public void loadingBlogs(Long userId, Long blogCategoryId) {
        String url = FRSCRequstIO.FIND_BLOGS_BY_USERID_AND_CATEGORY.getRequestIO() + userId + "/" + blogCategoryId;
        FRSCOKHttp3RequestUtil.callGetRequest(url, MyBlogPresenter.RespondMessageKey.LOADING_BLOGS);

    }

    public void addBlogCategory(BlogCategory category) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        String categoryJson = gson.toJson(category);
        String url = FRSCRequstIO.ADD_BLOG_CATEGORY.getRequestIO();
        FRSCOKHttp3RequestUtil.callPostRequest(url, categoryJson, MyBlogPresenter.RespondMessageKey.ADD_BLOG_CATEGORY);
    }

    public void modifyCategory(String jsonBody, Object respondMessageKey) {
        String url = FRSCRequstIO.MODIFY_BLOG_CATEGORY.getRequestIO();
        FRSCOKHttp3RequestUtil.callPutRequest(url,jsonBody, respondMessageKey);
    }


    public void loadBlogCount(Long userId, Object respondMessageKey) {
        String url = FRSCRequstIO.COUNT_BLOGS.getRequestIO() + userId;
        FRSCOKHttp3RequestUtil.callGetRequest(url, respondMessageKey);
    }
}
