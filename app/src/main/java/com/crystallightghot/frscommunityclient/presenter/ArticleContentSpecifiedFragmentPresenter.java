package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.BlogModel;
import com.crystallightghot.frscommunityclient.model.UserModel;
import com.crystallightghot.frscommunityclient.view.fragment.ArticleContentSpecifiedFragment;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCollection;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.pojo.system.UserFollower;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Date 2022/2/23
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class ArticleContentSpecifiedFragmentPresenter {

    UserModel userModel;
    BlogModel blogModel;
    private ArticleContentSpecifiedFragment view;
    RespondMessageKey checkIfFollowedK = new RespondMessageKey();
    RespondMessageKey followUserK = new RespondMessageKey();
    RespondMessageKey cancelUserK = new RespondMessageKey();
    RespondMessageKey collectionBlogK = new RespondMessageKey();
    RespondMessageKey cancelCollectionBlogK = new RespondMessageKey();


    public ArticleContentSpecifiedFragmentPresenter(ArticleContentSpecifiedFragment view) {
        this.view = view;
        userModel = new UserModel();
        blogModel = new BlogModel();
        FRSCEventBusUtil.register(this);
    }

    public void checkIfFollowed(Long userFollowedId) {
        userModel.checkIfFollowed(FRSCApplicationContext.getUser().getUserId(), userFollowedId, checkIfFollowedK);
    }

    public void followUser(Long userId) {
        UserFollower userFollower = new UserFollower();
        userFollower.setUserId(FRSCApplicationContext.getUser().getUserId());
        userFollower.setUserFollowedId(userId);
        userModel.followUser(userFollower, followUserK);
    }

    public void cancelFollower(Long userId, Long userFollowedId) {
        userModel.cancelFollower(userId, userFollowedId, cancelUserK);
    }

    public void collectionBlog(Blog blog, User user) {
        BlogCollection blogCollection = new BlogCollection();
        blogCollection.setBlogId(blog.getBlogId());
        blogCollection.setUserId(user.getUserId());
        blogModel.collectionBlog(blogCollection, collectionBlogK);
    }

    public void cancelCollectionBlog(Blog blog, User user) {
        BlogCollection blogCollection = new BlogCollection();
        blogCollection.setBlogId(blog.getBlogId());
        blogCollection.setUserId(user.getUserId());
        blogModel.cancelCollectionBlog(blogCollection, cancelCollectionBlogK);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage<RespondMessageKey> message) {
        if (message.getMessageKey() == checkIfFollowedK) {
            if (message.isSuccess()) {
                boolean isFollowed = (boolean) message.getData();
                view.isFollowed(isFollowed);
            }
        }else if (message.getMessageKey() == followUserK) {
            if (message.isSuccess()) {
                XToastUtils.success(message.getMessage());
            } else {
                XToastUtils.error(message.getMessage());

            }
        } else if (message.getMessageKey() == cancelUserK) {
            if (message.isSuccess()) {
                XToastUtils.success(message.getMessage());
            } else {
                XToastUtils.error(message.getMessage());
            }
        }else if (message.getMessageKey() == collectionBlogK) {
            if (message.isSuccess()) {
                XToastUtils.success(message.getMessage());
            } else {
                XToastUtils.error(message.getMessage());
            }
        }else if (message.getMessageKey() == cancelCollectionBlogK) {
            if (message.isSuccess()) {
                XToastUtils.success(message.getMessage());
            } else {
                XToastUtils.error(message.getMessage());
            }
        }
    }

    private class RespondMessageKey {
    }
}
