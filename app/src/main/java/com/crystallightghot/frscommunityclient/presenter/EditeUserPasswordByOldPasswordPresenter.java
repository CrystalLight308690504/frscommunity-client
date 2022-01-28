package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.contract.EditeUserPasswordByOldPasswordContract;
import com.crystallightghot.frscommunityclient.view.fragment.EditeUserPasswordByOldPasswordFragment;

/**
 * @Date 2022/1/28
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class EditeUserPasswordByOldPasswordPresenter implements EditeUserPasswordByOldPasswordContract.Presenter {

    EditeUserPasswordByOldPasswordFragment editeUserPasswordByOldPasswordFragment;
    public EditeUserPasswordByOldPasswordPresenter(EditeUserPasswordByOldPasswordFragment editeUserPasswordByOldPasswordFragment) {
        this.editeUserPasswordByOldPasswordFragment = editeUserPasswordByOldPasswordFragment;

    }
}
