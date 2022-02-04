package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.contract.MyBlogContract;
import com.crystallightghot.frscommunityclient.contract.RequestCallBack;
import com.crystallightghot.frscommunityclient.model.MyBlogModel;
import com.crystallightghot.frscommunityclient.view.enums.MessageCode;
import com.crystallightghot.frscommunityclient.view.fragment.ArticlesFragment;
import com.crystallightghot.frscommunityclient.view.fragment.MyBlogFragment;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCategory;
import com.crystallightghot.frscommunityclient.view.pojo.system.RequestResult;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * @Date 2022/2/4
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class MyBlogPresenter implements MyBlogContract.Presenter, RequestCallBack {

    MyBlogModel model;
    MyBlogFragment view;
    ArticlesFragment articlesFragment;

    public MyBlogPresenter(ArticlesFragment articlesFragment) {
        this.articlesFragment = articlesFragment;
        model = new MyBlogModel();
        FRSCEventBusUtil.register(this);
    }

    public MyBlogPresenter(MyBlogFragment view) {
        model = new MyBlogModel();
        this.view = view;
        FRSCEventBusUtil.register(this);
    }


    public void loadingCategory() {
        User user = FRSCApplicationContext.getUser();

        view.showLoadingDialog();
        model.loadingCategory(user.getUserId(), this);
    }

    /**
     * 注册账号消息
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {

        if (message.getMessageCode() == MessageCode.BLOG_CATEGORIES) {
            view.hideLoadingDialog();
            if (message.isSuccess()) {
                ArrayList<LinkedTreeMap> list = (ArrayList) message.getData();
                ArrayList<BlogCategory> blogCategories = new ArrayList();
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

                for (int i = 0; i < list.size(); i++) {
                    LinkedTreeMap linkedTreeMap = list.get(i);
                    String toJson = gson.toJson(linkedTreeMap);
                    BlogCategory category = gson.fromJson(toJson, BlogCategory.class);
                    blogCategories.add(category);
                }
                view.loadingData(blogCategories);
            } else {
                XToastUtils.error(message.getMessage());
            }
        } else if (message.getMessageCode() == MyMessageCode.LOADING_BLOGS_CALLBACK) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            ArrayList<LinkedTreeMap> list = (ArrayList) message.getData();
            ArrayList<Blog> blogs = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                LinkedTreeMap linkedTreeMap = list.get(i);
                String toJson = gson.toJson(linkedTreeMap);
                Blog blog = gson.fromJson(toJson, Blog.class);
                blogs.add(blog);
            }
            articlesFragment.addDataToList(blogs);
        }


    }

    @Override
    public void callBack(RequestResult requestResult) {
        RequestMessage message = new RequestMessage(requestResult, MessageCode.BLOG_CATEGORIES);
        FRSCEventBusUtil.sendMessage(message);
    }


    public void loadingBlogs(Long categoryId) {
        User user = FRSCApplicationContext.getUser();
        model.loadingBlogs(user.getUserId(), categoryId, this);
    }

    public void loadingBlogsCallBack(RequestResult requestResult) {
        RequestMessage message = new RequestMessage(requestResult, MyMessageCode.LOADING_BLOGS_CALLBACK);
        FRSCEventBusUtil.sendMessage(message);
    }

    public enum MyMessageCode {
        LOADING_BLOGS_CALLBACK
    }

}
