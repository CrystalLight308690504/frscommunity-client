package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.BlogModel;
import com.crystallightghot.frscommunityclient.view.fragment.BlogSearchResultDefaultViewPagerItemFragment;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Map;

/**
 * @Date 2022/2/20
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class BlogSearchResultDefaultViewPagerItemFragmentPresenter {
    BlogSearchResultDefaultViewPagerItemFragment view;
    BlogModel model;
    boolean hasNext = true;

    public BlogSearchResultDefaultViewPagerItemFragmentPresenter(BlogSearchResultDefaultViewPagerItemFragment view) {
        model = new BlogModel();
        this.view = view;
        FRSCEventBusUtil.register(this);
    }

    public void loadSearchBlogResult(String searchText, int pagerIndex) {
        view.showLoadingDialog();
        model.loadSearchBlogResult(searchText, pagerIndex, RespondMessageKey.LOAD_BLOG_SEARCH_RESULT);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        if (!(message.getMessageKey() instanceof RespondMessageKey)) {
            return;
        }

        switch ((RespondMessageKey) message.getMessageKey()) {
            case LOAD_BLOG_SEARCH_RESULT:
                view.hideLoadingDialog();
                if (message.isSuccess()) {
                    Map data = (Map) message.getData();
                    if (null != data) {
                        hasNext = (boolean) data.get("hasNext");
                        Map resultMap = (Map) message.getData();

                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                        ArrayList dataList = (ArrayList) resultMap.get("data");
                        ArrayList<Blog> blogs = new ArrayList<>();
                        for (int i = 0; i < dataList.size(); i++) {
                            LinkedTreeMap linkedTreeMap = (LinkedTreeMap) dataList.get(i);
                            String toJson = gson.toJson(linkedTreeMap);
                            Blog blog = gson.fromJson(toJson, Blog.class);
                            blogs.add(blog);
                        }
                        view.addDataToRV(blogs, hasNext);
                    }
                } else {
                    view.showError(message.getMessage());
                }
                break;
        }
    }

    public enum RespondMessageKey {
        LOAD_BLOG_SEARCH_RESULT
    }
}
