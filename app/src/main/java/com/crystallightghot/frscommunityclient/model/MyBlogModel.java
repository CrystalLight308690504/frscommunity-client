package com.crystallightghot.frscommunityclient.model;

import com.crystallightghot.frscommunityclient.contract.MyBlogContract;
import com.crystallightghot.frscommunityclient.contract.RequestCallBack;
import com.crystallightghot.frscommunityclient.presenter.MyBlogPresenter;
import com.crystallightghot.frscommunityclient.view.value.RequstIO;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCategory;
import com.crystallightghot.frscommunityclient.view.pojo.system.RequestResult;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCOKHttp3RequestUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCThreadPoolUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @Date 2022/2/4
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class MyBlogModel implements MyBlogContract.Model {


    public void loadingCategory(Long userId, RequestCallBack callBack) {
        String requestIO = RequstIO.FIND_CATEGORIES_BY_USERID.getRequestIO() + userId;
        FRSCOKHttp3RequestUtil.getWithAuthorizationHeader(requestIO, callBack);
    }

    public void loadingBlogs(Long userId, Long blogCategoryId, MyBlogPresenter myBlogPresenter) {
        String url = RequstIO.FIND_BLOGS_BY_USERID_AND_CATEGORY.getRequestIO() + userId + "/" + blogCategoryId;

        User user = FRSCApplicationContext.getUser();
        String head = "";
        if (null != user) {
            head = user.getSessionId();
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .addHeader("Authorization", "FRSC" + head)
                .url(url)
                .build();
        Runnable runnable = () -> {
            try {
                Response response = client.newCall(request).execute();
                String string = response.body().string();
                Gson gson = new Gson();
                // 获取返回结果信息
                RequestResult requestResult = gson.fromJson(string, RequestResult.class);
                myBlogPresenter.loadingBlogsCallBack(requestResult);
            } catch (IOException e) {
                e.printStackTrace();
                RequestResult requestResult = new RequestResult(false, 404, "(ಥ﹏ಥ)服务器跑路了", null);
                myBlogPresenter.loadingBlogsCallBack(requestResult);
            }
        };
        FRSCThreadPoolUtil.executeThread(runnable);

    }

    public void addBlogCategory(BlogCategory category) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        String categoryJson = gson.toJson(category);
        String url = RequstIO.ADD_BLOG_CATEGORY.getRequestIO();
        FRSCOKHttp3RequestUtil.callPostRequest(url, categoryJson, MyBlogPresenter.MessageCode.ADD_BLOG_CATEGORY);
    }
}
