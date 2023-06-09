package com.crystallightghot.frscommunityclient.presenter;


import com.crystallightghot.frscommunityclient.contract.LoginContract;
import com.crystallightghot.frscommunityclient.contract.RequestCallBack;
import com.crystallightghot.frscommunityclient.model.LoginModel;
import com.crystallightghot.frscommunityclient.model.UserModel;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.blog.DaoSession;
import com.crystallightghot.frscommunityclient.view.pojo.system.*;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCDataBaseUtil;
import com.crystallightghot.frscommunityclient.view.value.MessageCode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.greenrobot.eventbus.EventBus;

/**
 * @Date 2022/1/21
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class LoginPresenter implements LoginContract.Presenter, RequestCallBack {

    LoginModel model;
    LoginContract.View view;
    UserModel userModel;

    public static LoginPresenter getInstance(LoginContract.View view) {
        return new LoginPresenter(view);
    }

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        model = new LoginModel();
        userModel = new UserModel();
    }

    @Override
    public void login(User user) {
        view.showLoadingDialog();
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        model.login(userJson, this);
    }

    @Override
    public void callBack(RequestResult requestResult) {
        if (requestResult.isSuccess()) {
            view.hideLoadingDialog();
            // 将获取的数据转化为User实体类
            Gson gson = (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            User user = gson.fromJson(gson.toJson(requestResult.getData()), User.class);

            // 创建消息
            RequestMessage message = new RequestMessage();
            message.setSuccess(true);
            message.setMessage(requestResult.getMessage());
            message.setData(requestResult.getData());
            message.setMessageKey(MessageCode.LOGIN_RESULT);

            // 存储User信息到全局中
            FRSCApplicationContext.setUser(user);
            // 存储到本地数据库
            DaoSession daoSession = FRSCDataBaseUtil.getWriteDaoSession();
            UserDao userDao = daoSession.getUserDao();
            userDao.deleteAll();
            userDao.insert(user);

            RoleDao roleDao = daoSession.getRoleDao();
            roleDao.deleteAll();

            Role role = user.getRole();
            role.setUserId(user.getUserId());
            roleDao.insert(role);

            // 记录当前用户登陆状态
            LoginInformationDao loginInformationDao = daoSession.getLoginInformationDao();
            loginInformationDao.deleteAll();
            LoginInformation loginInformation = new LoginInformation(null, user.getUserId(), 1);
            loginInformationDao.insert(loginInformation);

            EventBus.getDefault().post(message);
        } else {
            view.hideLoadingDialog();
            RequestMessage message = new RequestMessage();
            message.setMessage(requestResult.getMessage());
            message.setMessageKey(MessageCode.LOGIN_RESULT);
            message.setSuccess(false);
            EventBus.getDefault().post(message);
        }

    }
}
