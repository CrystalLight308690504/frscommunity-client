package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.contract.PutBlogContentContract;
import com.crystallightghot.frscommunityclient.presenter.PutBlogContentPresenter;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.crystallightghot.frscommunityclient.view.util.FRSCOKHttp3RequestUtil;
import com.crystallightghot.frscommunityclient.view.value.FRSCRequstIO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Date 2022/2/6
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class PutBlogContentModel implements PutBlogContentContract.Model {
    public void addBlog(Blog blog) {
        String requestIO = FRSCRequstIO.ADD_BLOG.getRequestIO();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String blogJson = gson.toJson(blog);
        FRSCOKHttp3RequestUtil.callPostRequest(requestIO,blogJson, PutBlogContentPresenter.RespondMessageKey.ADD_BLOG);
    }
}
