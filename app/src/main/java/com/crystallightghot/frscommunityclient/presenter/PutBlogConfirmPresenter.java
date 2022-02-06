package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.contract.PutBlogConfirmContract;
import com.crystallightghot.frscommunityclient.model.PutBlogConfirmModel;
import com.crystallightghot.frscommunityclient.view.fragment.PutBlogConfirmFragment;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCategory;
import com.crystallightghot.frscommunityclient.view.pojo.skatingtype.SkatingType;
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
 * @Date 2022/2/6
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class PutBlogConfirmPresenter implements PutBlogConfirmContract.Presenter {

    PutBlogConfirmFragment view;
    PutBlogConfirmModel model;

    public PutBlogConfirmPresenter(PutBlogConfirmFragment view) {
        FRSCEventBusUtil.register(this);
        this.view = view;
        model = new PutBlogConfirmModel();
    }

    public void loadingCategory() {

        User user = FRSCApplicationContext.getUser();
        view.showLoadingDialog();
        model.loadingCategory(user.getUserId());
    }

    public void loadingSkatingType() {

        view.showLoadingDialog();
        model.loadingSkatingType();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {

        switch ((MessageRespondKey) message.getMessageKey()) {
            case BLOG_CATEGORIES:
                view.hideLoadingDialog();
                if (message.isSuccess()) {
                    ArrayList<LinkedTreeMap> list = (ArrayList) message.getData();
                    ArrayList<String> blogCategoriesName = new ArrayList();
                    ArrayList<BlogCategory> blogCategories = new ArrayList();
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                    for (int i = 0; i < list.size(); i++) {
                        LinkedTreeMap linkedTreeMap = list.get(i);
                        String toJson = gson.toJson(linkedTreeMap);
                        BlogCategory category = gson.fromJson(toJson, BlogCategory.class);
                        blogCategories.add(category);
                        blogCategoriesName.add(category.getCategoryName());
                    }
                    view.loadingBlogCategories(blogCategoriesName);
                    view.getCategoryList(blogCategories);
                } else {
                    XToastUtils.error(message.getMessage());
                }
                break;
            case SKATING_TYPES :
                view.hideLoadingDialog();
                if (message.isSuccess()) {
                    ArrayList<LinkedTreeMap> list = (ArrayList) message.getData();
                    ArrayList<SkatingType> skatingTypes = new ArrayList();
                    ArrayList<String> skatingTypesName = new ArrayList();
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                    for (int i = 0; i < list.size(); i++) {
                        LinkedTreeMap linkedTreeMap = list.get(i);
                        String toJson = gson.toJson(linkedTreeMap);
                        SkatingType skatingType = gson.fromJson(toJson, SkatingType.class);
                        skatingTypes.add(skatingType);
                        skatingTypesName.add(skatingType.getName());
                    }
                    view.loadingSkatingType(skatingTypesName);
                    view.getSkatingTypeList(skatingTypes);
                } else {
                    XToastUtils.error(message.getMessage());
                }

                break;
        }
    }

    public enum MessageRespondKey {
        BLOG_CATEGORIES,
        SKATING_TYPES
    }

}
