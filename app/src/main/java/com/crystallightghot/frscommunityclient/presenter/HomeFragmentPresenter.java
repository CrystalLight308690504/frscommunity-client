package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.model.SkatingTypeModel;
import com.crystallightghot.frscommunityclient.view.fragment.HomeFragment;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.skatingtype.SkatingType;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
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
public class HomeFragmentPresenter {
    SkatingTypeModel skatingTypeModel;
    HomeFragment homeFragment;
    String[] skatingTypesName ;
    ArrayList<SkatingType> skatingTypes = new ArrayList();

    public HomeFragmentPresenter(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
        skatingTypeModel = new SkatingTypeModel();
        FRSCEventBusUtil.register(this);
    }

    public void loadingSkatingType() {
        homeFragment.showLoadingDialog();
        skatingTypeModel.loadingSkatingType(RespondMessageKey.LOADING_SKATING_TYPE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        if (!(message.getMessageKey() instanceof RespondMessageKey)) {
            return;
        }
        switch ((RespondMessageKey) message.getMessageKey()) {
            case LOADING_SKATING_TYPE:
                homeFragment.hideLoadingDialog();
                if (message.isSuccess()) {
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                    ArrayList<LinkedTreeMap> list = (ArrayList) message.getData();
                    skatingTypesName = new String[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        LinkedTreeMap linkedTreeMap = list.get(i);
                        String toJson = gson.toJson(linkedTreeMap);
                        SkatingType skatingType = gson.fromJson(toJson, SkatingType.class);
                        skatingTypes.add(skatingType);
                        skatingTypesName[i] = skatingType.getName();
                    }
                    homeFragment.init(skatingTypesName, skatingTypes);
                    FRSCApplicationContext.setSkatingTypes(skatingTypes);
                }
                break;
        }
    }

    public enum RespondMessageKey {
        LOADING_SKATING_TYPE
    }
}
