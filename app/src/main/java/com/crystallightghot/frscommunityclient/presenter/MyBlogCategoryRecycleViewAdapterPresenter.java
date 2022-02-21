package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.contract.MyBlogCategoryRecycleViewAdapterContract;
import com.crystallightghot.frscommunityclient.model.BlogModel;
import com.crystallightghot.frscommunityclient.view.adapter.MyBlogCategoryRecycleViewAdapter;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCategory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Date 2022/2/7
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class MyBlogCategoryRecycleViewAdapterPresenter implements MyBlogCategoryRecycleViewAdapterContract.Presenter {
    MyBlogCategoryRecycleViewAdapter view;
    BlogModel blogModel;

    public MyBlogCategoryRecycleViewAdapterPresenter(MyBlogCategoryRecycleViewAdapter view) {
        this.view = view;
        blogModel = new BlogModel();
    }

    public void deleteBlogCategory(BlogCategory blogCategory) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String blogCategoryJson = gson.toJson(blogCategory);
        blogModel.deleteBlogCategory(blogCategoryJson, MyBlogPresenter.RespondMessageKey.DELETE_CATEGORY);
    }
}
