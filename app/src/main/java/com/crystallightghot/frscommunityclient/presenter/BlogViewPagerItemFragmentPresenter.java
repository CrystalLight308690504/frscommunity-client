package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.BlogViewPagerItemFragmentModel;
import com.crystallightghot.frscommunityclient.view.fragment.BlogViewPagerItemFragment;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.crystallightghot.frscommunityclient.view.pojo.skatingtype.SkatingType;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * @Date 2022/2/11
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class BlogViewPagerItemFragmentPresenter {
    BlogViewPagerItemFragment view;
    BlogViewPagerItemFragmentModel model;

    public BlogViewPagerItemFragmentPresenter(BlogViewPagerItemFragment view) {
        this.view = view;
        model = new BlogViewPagerItemFragmentModel();
        FRSCEventBusUtil.register(this);
    }

    public void loadingBlogs(SkatingType skatingType) {
        view.showLoadingDialog();
        model.loadingBlogs(skatingType, this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        if ((message.getMessageKey() != this)) {
            return;
        }
        if (message.isSuccess()) {
            view.hideLoadingDialog();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            ArrayList<LinkedTreeMap> list = (ArrayList) message.getData();
            ArrayList<Blog> blogs = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                LinkedTreeMap linkedTreeMap = list.get(i);
                String toJson = gson.toJson(linkedTreeMap);
                Blog blog = gson.fromJson(toJson, Blog.class);
                blogs.add(blog);
            }
            view.loadData(blogs);
        }
    }
}
