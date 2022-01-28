package com.crystallightghot.frscommunityclient.contract;

import com.crystallightghot.frscommunityclient.view.pojo.system.RequestResult;

/**
 * @Date 2022/1/27
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public interface EditUserPasswordContract {
    interface Model {
    }

    interface View {
    }

    interface Presenter {
    }

    interface EditUserPasswordCallBack {

        void modifyUserPasswordByPhoneResult(RequestResult requestResult);

        void modifyUserPasswordByOldPasswordResult(RequestResult requestResult);
    }
}
