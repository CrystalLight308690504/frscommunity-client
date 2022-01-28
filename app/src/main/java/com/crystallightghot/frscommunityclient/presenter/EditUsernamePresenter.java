package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.contract.EditUserNameContract;
import com.crystallightghot.frscommunityclient.model.EditUsernameModel;
import com.crystallightghot.frscommunityclient.utils.EventBusUtil;
import com.crystallightghot.frscommunityclient.utils.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.utils.XToastUtils;
import com.crystallightghot.frscommunityclient.view.enums.MessageCode;
import com.crystallightghot.frscommunityclient.view.fragment.EditUsernameFragment;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.message.UserChangedMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.RequestResult;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.pojo.system.UserDao;
import com.crystallightghot.frscommunityclient.view.util.FRSCDataBaseUtil;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Date 2022/1/27
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class EditUsernamePresenter implements EditUserNameContract.Presenter , EditUserNameContract.UserRespondCallBack {

    EditUsernameFragment view;
    EditUserNameContract.Model model;

    public EditUsernamePresenter(EditUsernameFragment view) {
        this.view = view;
        this.model = new EditUsernameModel();
        EventBusUtil.register(this);
    }




    @Override
    public void modifyUsername( User user) {
        view.showLoadingDialog();
        model.modifyUsername(user,this);
    }


    @Override
    public void modifyUsernameResult(RequestResult requestResult) {
        view.hideLoadingDialog();
        RequestMessage message = new RequestMessage(requestResult, MessageCode.EDITE_USERNAME_RESULT);
        EventBusUtil.sendMessage(message);
    }

    /**
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {

        if (message.getMessageCode() != MessageCode.EDITE_USERNAME_RESULT){
            return;
        }
        if (message.isSuccess()){
            XToastUtils.success("修改成功");
            // 修改保存在本地数据库的user
            UserDao userDao = FRSCDataBaseUtil.getWriteDaoSession().getUserDao();
            userDao.save(FRSCApplicationContext.getUser());
            // 发送修改用户事件
            UserChangedMessage userChangedMessage = new UserChangedMessage();
            EventBusUtil.sendMessage(userChangedMessage);
        }else {
            XToastUtils.error(message.getMessage());
        }
    }

}
