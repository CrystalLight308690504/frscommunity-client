package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.contract.MyBlogContract;
import com.crystallightghot.frscommunityclient.model.MyBlogModel;
import com.crystallightghot.frscommunityclient.view.fragment.ArticlesFragment;
import com.crystallightghot.frscommunityclient.view.fragment.MyBlogFragment;
import com.crystallightghot.frscommunityclient.view.message.BlogChangMessage;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.message.TransportDataMessage;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCategory;
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
import java.util.Map;

/**
 * @Date 2022/2/4
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class MyBlogPresenter implements MyBlogContract.Presenter{

    MyBlogModel blogModel;
    MyBlogFragment view;
    ArticlesFragment articlesFragment;

    ArrayList<Blog> blogs = new ArrayList();
    ArrayList<BlogCategory> blogCategories = new ArrayList();

    public MyBlogPresenter(ArticlesFragment articlesFragment) {
        this.articlesFragment = articlesFragment;
        blogModel = new MyBlogModel();
        FRSCEventBusUtil.register(this);
    }

    public MyBlogPresenter(MyBlogFragment view) {
        blogModel = new MyBlogModel();
        this.view = view;
        FRSCEventBusUtil.register(this);
    }


    public void loadingCategory() {
        view.showLoadingDialog();
        User user = FRSCApplicationContext.getUser();
        blogModel.loadingCategory(user.getUserId());
    }

    /**
     * 注册账号消息
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        if (!(message.getMessageKey() instanceof RespondMessageKey)) {
            return;
        }
        switch ((RespondMessageKey)message.getMessageKey()) {
            case LOADING_BLOG_CATEGORIES:
                if (null == view){
                    return;
                }
                view.hideLoadingDialog();
                blogCategories.clear();
                if (message.isSuccess()) {
                    ArrayList<LinkedTreeMap> list = (ArrayList) message.getData();
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
                break;
            case LOADING_BLOGS:
                if (null == articlesFragment) {
                    return;
                }
                articlesFragment.hideLoadingDialog();
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                ArrayList<LinkedTreeMap> list = (ArrayList) message.getData();
                for (int i = 0; i < list.size(); i++) {
                    LinkedTreeMap linkedTreeMap = list.get(i);
                    String toJson = gson.toJson(linkedTreeMap);
                    Blog blog = gson.fromJson(toJson, Blog.class);
                    blogs.add(blog);
                }
                articlesFragment.addDataToList(blogs);
                break;
            case  ADD_BLOG_CATEGORY :
                if (message.isSuccess()) {
                    view.hideLoadingDialog();
                    XToastUtils.success("添加成功");
                    loadingCategory();
                }else {
                    XToastUtils.error(message.getMessage());
                }

                break;
            case MODIFY_CATEGORY:
            case DELETE_CATEGORY :
               if (message.isSuccess()) {
                   loadingCategory();
                   XToastUtils.success(message.getMessage());

               }else {
                   XToastUtils.error(message.getMessage());
               }
                break;
        }

    }


    public void loadingBlogs(Long categoryId) {

        articlesFragment.showLoadingDialog();
        User user = FRSCApplicationContext.getUser();
        blogModel.loadingBlogs(user.getUserId(), categoryId);
    }

    public void addBlogCategory(String categoryName, String description) {
        view.showLoadingDialog();
        User user = FRSCApplicationContext.getUser();
        BlogCategory category = new BlogCategory();
        category.setCategoryName(categoryName);
        category.setDescription(description);
        category.setUser(user);
        blogModel.addBlogCategory(category);
    }

    public void modifyBlogCategory(BlogCategory blogCategory) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String blogCategoryJson = gson.toJson(blogCategory);
        blogModel.modifyCategory(blogCategoryJson, RespondMessageKey.MODIFY_CATEGORY);
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void getMessage(TransportDataMessage message) {
        if (message.getMessageKey() != RespondMessageKey.CATEGORY_DIALOG) {
            return;
        }
        Object data = message.getData();
        if (data instanceof Map) {
          Map map = (Map) data;
            String categoryName = (String) map.get("categoryName");
            String description = (String) map.get("description");
            addBlogCategory(categoryName, description);
        } else if (data instanceof BlogCategory) {
            BlogCategory blogCategory = (BlogCategory) data;
            modifyBlogCategory(blogCategory);
        }

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(BlogChangMessage message ) {
        if (articlesFragment == null) {
            return;
        }
        articlesFragment.initView();
    }

    public enum RespondMessageKey {
        LOADING_BLOGS,
        LOADING_BLOG_CATEGORIES,
        ADD_BLOG_CATEGORY,
        MODIFY_CATEGORY,
        DELETE_CATEGORY,
        CATEGORY_DIALOG
    }

}
