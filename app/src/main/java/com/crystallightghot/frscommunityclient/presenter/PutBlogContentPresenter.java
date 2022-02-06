package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.contract.PutBlogContentContract;
import com.crystallightghot.frscommunityclient.model.PutBlogContentModel;
import com.crystallightghot.frscommunityclient.view.fragment.PutBlogContentFragment;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.message.TransportDataMessage;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCategory;
import com.crystallightghot.frscommunityclient.view.pojo.skatingtype.SkatingType;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

/**
 * @Date 2022/2/6
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class PutBlogContentPresenter implements PutBlogContentContract.Presenter {
    PutBlogContentFragment view;
    PutBlogContentModel model;

    public PutBlogContentPresenter(PutBlogContentFragment putBlogContentFragment) {
        this.view = putBlogContentFragment;
        model = new PutBlogContentModel();
        FRSCEventBusUtil.register(this);

    }

    public void addBlog(Blog blog) {
        model.addBlog(blog);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        switch ((RespondMessageKey) message.getMessageKey()) {
            case ADD_BLOG:
                view.hideLoadingDialog();
                if (message.isSuccess()) {
                    XToastUtils.success("添加成功");
                }else {
                    XToastUtils.error(message.getMessage());
                }
                break;

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(TransportDataMessage message) {
        switch ((RespondMessageKey) message.getMessageKey()) {
            case PUT_BLOG_CONFIRM_REQUEST:
                Map<String, Object> data = (Map<String, Object>) message.getData();
                BlogCategory blogCategory = (BlogCategory) data.get("blogCategory");
                SkatingType skatingType = (SkatingType) data.get("skatingType");

                Blog blog = new Blog();
                blog.setBlogTitle(view.getBlogTitle());
                blog.setContent(view.getBlogContent());
                User user = FRSCApplicationContext.getUser();
                blog.setUser(user);
                blog.setBlogCategory(blogCategory);
                blog.setSkatingType(skatingType);
                view.showLoadingDialog();
                addBlog(blog);
                break;
        }
    }

    public enum RespondMessageKey {
        ADD_BLOG,
        PUT_BLOG_CONFIRM_REQUEST
    }
}