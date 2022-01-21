package com.crystallightghot.frscommunityclient.contract;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public interface BaseContract {

    interface Model {
    }

    interface View {
        void showLoadingDialog();

        void hideLoadingDialog();
    }

    interface Presenter {
    }
}

