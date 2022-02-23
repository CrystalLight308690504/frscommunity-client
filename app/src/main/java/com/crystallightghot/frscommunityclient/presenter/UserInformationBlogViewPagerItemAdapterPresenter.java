package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.BlogModel;
import com.crystallightghot.frscommunityclient.view.fragment.UserInformationViewPagerBlogItemFragment;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCategory;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * @Date 2022/2/23
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class UserInformationBlogViewPagerItemAdapterPresenter {

    RespondMessageKey loadingCategoryK = new RespondMessageKey();
    BlogModel blogModel;
    private UserInformationViewPagerBlogItemFragment view;
    ArrayList<BlogCategory> blogCategories = new ArrayList();

    public UserInformationBlogViewPagerItemAdapterPresenter(UserInformationViewPagerBlogItemFragment view) {
        this.view = view;
        blogModel = new BlogModel();
        FRSCEventBusUtil.register(this);
    }

    public void loadingCategory(Long userId) {
        blogModel.loadingCategory(userId, loadingCategoryK);
    }


    private class RespondMessageKey {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage<RespondMessageKey> message) {
        if (message.getMessageKey() == loadingCategoryK) {
            if (message.isSuccess()) {
                ArrayList<LinkedTreeMap> list = (ArrayList) message.getData();
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                blogCategories.clear();
                for (int i = 0; i < list.size(); i++) {
                    LinkedTreeMap linkedTreeMap = list.get(i);
                    String toJson = gson.toJson(linkedTreeMap);
                    BlogCategory category = gson.fromJson(toJson, BlogCategory.class);
                    blogCategories.add(category);
                }
                view.loadingData(blogCategories);
            } else {
                XToastUtils.error(message.getMessage());
                view.showError();
            }
        }
    }
}
