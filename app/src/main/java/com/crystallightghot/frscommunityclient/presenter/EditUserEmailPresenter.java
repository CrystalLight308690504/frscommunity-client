package com.crystallightghot.frscommunityclient.presenter;

import com.crystallightghot.frscommunityclient.contract.EditUserEmailContract;
import com.crystallightghot.frscommunityclient.model.EditUserEmailModel;
import com.crystallightghot.frscommunityclient.view.value.MessageCode;
import com.crystallightghot.frscommunityclient.view.fragment.EditUserEmailFragment;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.message.UserChangedMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.RequestResult;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.pojo.system.UserDao;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCDataBaseUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.regex.Pattern;

/**
 * @Date 2022/1/28
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class EditUserEmailPresenter implements EditUserEmailContract.Presenter {
    EditUserEmailFragment view;
    EditUserEmailModel model;
    User user;

    public boolean verifyEmailPattern(String emailInput) {

        String regExEmail = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        Pattern pattern = Pattern.compile(regExEmail);
        if (pattern.matcher(emailInput).matches()) {
            return true;
        } else {
            view.showWarningToast("请输入正确的邮箱");
            return false;
        }
    }


    public EditUserEmailPresenter(EditUserEmailFragment view) {
        FRSCEventBusUtil.register(this);
        model = new EditUserEmailModel();
        user = FRSCApplicationContext.getUser();
        this.view = view;
    }

    public void modifyUserEmail(String emailModified) {
        view.showLoadingDialog();
        user.setEmail(emailModified);
        model.modifyUserEmail(user, this);
    }

    public void modifyUserEmailResult(RequestResult requestResult) {
        view.hideLoadingDialog();
        RequestMessage message = new RequestMessage(requestResult, MessageCode.MODIFY_USER_EMAIL_RESULT);
        FRSCEventBusUtil.sendMessage(message);
    }

    /**
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage message) {
        view.hideLoadingDialog();
        if (message.getMessageKey() != MessageCode.MODIFY_USER_EMAIL_RESULT) {
            return;
        }
        if (message.isSuccess()) {
            XToastUtils.success("修改成功");

            view.clearDataInput();

            // 修改保存在本地数据库的user
            UserDao userDao = FRSCDataBaseUtil.getWriteDaoSession().getUserDao();
            userDao.save(user);
            // 发送修改用户事件
            UserChangedMessage userChangedMessage = new UserChangedMessage();
            FRSCEventBusUtil.sendMessage(userChangedMessage);
        } else {
            XToastUtils.error(message.getMessage());
        }
    }

}
