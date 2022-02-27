package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.BlogModel;
import com.crystallightghot.frscommunityclient.model.UserModel;
import com.crystallightghot.frscommunityclient.view.fragment.ArticleContentSpecifiedFragment;
import com.crystallightghot.frscommunityclient.view.message.BlogCriticiseChangeMessage;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCollection;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCriticism;
import com.crystallightghot.frscommunityclient.view.pojo.system.UserFollower;
import com.crystallightghot.frscommunityclient.view.util.*;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Date 2022/2/23
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class ArticleContentSpecifiedFragmentPresenter {

    UserModel userModel;
    BlogModel blogModel;
    BlogCriticism blogCriticismAdded;
    private ArticleContentSpecifiedFragment view;
    RespondMessageKey checkIfFollowedK = new RespondMessageKey();
    RespondMessageKey followUserK = new RespondMessageKey();
    RespondMessageKey cancelUserK = new RespondMessageKey();
    RespondMessageKey collectionBlogK = new RespondMessageKey();
    RespondMessageKey cancelCollectionBlogK = new RespondMessageKey();
    RespondMessageKey IS_COLLECTION_BLOG = new RespondMessageKey();
    RespondMessageKey isApplauseBlogK = new RespondMessageKey();
    RespondMessageKey applauseBlogK = new RespondMessageKey();
    RespondMessageKey cancelApplauseBlogK = new RespondMessageKey();
    RespondMessageKey criticiseBlogK = new RespondMessageKey();
    RespondMessageKey loadCriticismK = new RespondMessageKey();


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

    public void collectionBlog(Blog blog) {
        BlogCollection blogCollection = new BlogCollection();
        blogCollection.setBlog(blog);
        blogCollection.setUserId(FRSCApplicationContext.getUser().getUserId());
        blogModel.collectionBlog(blogCollection, collectionBlogK);
    }

    public void cancelCollectionBlog(Blog blog) {
        BlogCollection blogCollection = new BlogCollection();
        blogCollection.setBlog(blog);
        blogCollection.setUserId(FRSCApplicationContext.getUser().getUserId());
        blogModel.cancelCollectionBlog(blogCollection, cancelCollectionBlogK);

    }

    public void checkIfCollection(Long blogId) {
        Long userId = FRSCApplicationContext.getUser().getUserId();
        blogModel.checkIfCollection(userId, blogId, IS_COLLECTION_BLOG);
    }

    public void checkIsApplauseBlog(long blogId) {
        Long userId = FRSCApplicationContext.getUser().getUserId();
        blogModel.checkIsApplauseBlog(userId, blogId,isApplauseBlogK);
    }

    public void applauseBlog(Blog blog) {
        blogModel.applauseBlog(FRSCApplicationContext.getUser().getUserId(), blog, applauseBlogK);
    }

    public void cancelApplauseBlog(Blog blog) {
        blogModel.cancelApplauseBlog(FRSCApplicationContext.getUser().getUserId(), blog, applauseBlogK);
    }

    public void criticiseBlog(String toString, long blogId) {
        blogCriticismAdded = new BlogCriticism();
        blogCriticismAdded.setContent(toString);
        blogCriticismAdded.setBlogId(blogId);
        blogCriticismAdded.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        blogCriticismAdded.setUser(FRSCApplicationContext.getUser());
        blogModel.criticiseBlog(blogCriticismAdded,criticiseBlogK);
    }

    public void loadCriticisms(long blogId, int pagerIndex) {
        blogModel.loadCriticisms(blogId,pagerIndex, loadCriticismK);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        if (!message.getMessageKey().getClass().equals(RespondMessageKey.class)){
            return;
        }

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
        }else if (message.getMessageKey() == cancelUserK) {
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
        }else if (message.getMessageKey() == IS_COLLECTION_BLOG) {
            if (message.isSuccess()) {
                boolean b = (boolean) message.getData();
                view.showIsCollectionBlog(b);
            } else {
                XToastUtils.error(message.getMessage());
            }
        }else if (message.getMessageKey() == isApplauseBlogK) {
            if (message.isSuccess()) {
                boolean b = (boolean) message.getData();
                view.showIsApplauseBlog(b);
            } else {
                XToastUtils.error(message.getMessage());
            }
        }else if (message.getMessageKey() == applauseBlogK) {
            if (message.isSuccess()) {
                XToastUtils.success(message.getMessage());

            } else {
                XToastUtils.error(message.getMessage());
            }
        }else if (message.getMessageKey() == cancelApplauseBlogK) {
            if (message.isSuccess()) {
                XToastUtils.success(message.getMessage());

            } else {
                XToastUtils.error(message.getMessage());
            }
        }else if (message.getMessageKey() == criticiseBlogK) {
            if (message.isSuccess()) {
                XToastUtils.success(message.getMessage());
                view.criticiseBlogAfter(true,blogCriticismAdded);
            } else {
                view.criticiseBlogAfter(false,null);
                XToastUtils.error(message.getMessage());
            }
        }else if (message.getMessageKey() == loadCriticismK) {
                if (message.isSuccess()) {
                    Map resultMap = (Map) message.getData();
                    boolean hasNext = (boolean) resultMap.get("hasNext");
                    ArrayList dataList = (ArrayList) resultMap.get("data");
                    if (null != dataList){
                        List<BlogCriticism> blogCriticisms = FRSCGsonUtil.listMapToListObject(dataList, BlogCriticism.class);
                        view.showMoreBlogCriticism(blogCriticisms,hasNext);
                    }
                }else  {
                    view.showMoreBlogCriticismError(message.getMessage());
                }
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(BlogCriticiseChangeMessage message) {
        view.blogCriticiseChange();

    }

    private class RespondMessageKey {
    }
}
