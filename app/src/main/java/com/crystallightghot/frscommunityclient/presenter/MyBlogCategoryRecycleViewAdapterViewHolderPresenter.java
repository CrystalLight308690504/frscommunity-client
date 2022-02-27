package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.BlogModel;
import com.crystallightghot.frscommunityclient.view.adapter.MyBlogCategoryRecycleViewAdapter;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCategory;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Date 2022/2/26
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class MyBlogCategoryRecycleViewAdapterViewHolderPresenter {
    BlogModel blogModel;
    private MyBlogCategoryRecycleViewAdapter.ViewHolder view;
    RespondMessageKey DELETE_CATEGORY = new RespondMessageKey();
    RespondMessageKey BLOG_COUNT = new RespondMessageKey();

    public MyBlogCategoryRecycleViewAdapterViewHolderPresenter(MyBlogCategoryRecycleViewAdapter.ViewHolder view) {
        this.view = view;
        blogModel = new BlogModel();
        FRSCEventBusUtil.register(this);
    }

    public void deleteBlogCategory(BlogCategory blogCategory) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String blogCategoryJson = gson.toJson(blogCategory);
        blogModel.deleteBlogCategory(blogCategoryJson, DELETE_CATEGORY);
    }

    public void loadBlogCount(BlogCategory blogCategory) {
        blogModel.countBlogCount(blogCategory.getCategoryId(), BLOG_COUNT);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage<RespondMessageKey> message) {
        if (message.getMessageKey() == DELETE_CATEGORY) {
            if (message.isSuccess()){
                XToastUtils.success(message.getMessage());
            }else {
                XToastUtils.error(message.getMessage());
            }
        }else if (message.getMessageKey() == BLOG_COUNT) {
            if (message.isSuccess()){
                double data = (double) message.getData();
                view.showBlogCount((long) data);
            }else {
                XToastUtils.error(message.getMessage());
            }
        }
     }

    private class RespondMessageKey {

    }
}
