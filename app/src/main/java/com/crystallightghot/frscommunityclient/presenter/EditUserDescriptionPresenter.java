package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.contract.EditUserDescriptionContract;
import com.crystallightghot.frscommunityclient.model.EditUserDescriptionModel;
import com.crystallightghot.frscommunityclient.view.fragment.EditMyDescriptionFragment;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;

/**
 * @Date 2022/1/30
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class EditUserDescriptionPresenter implements EditUserDescriptionContract.Presenter {
    EditMyDescriptionFragment view;
    EditUserDescriptionModel model;

    public EditUserDescriptionPresenter(EditMyDescriptionFragment view) {
        model = new EditUserDescriptionModel(this);
        this.view = view;
        FRSCEventBusUtil.register(this);
    }
    public void  modifyUserDescription(String description) {
        view.showLoadingDialog();
        User user = FRSCApplicationContext.getUser();
        model.modifyUserDescription(user,this);
    }
}
