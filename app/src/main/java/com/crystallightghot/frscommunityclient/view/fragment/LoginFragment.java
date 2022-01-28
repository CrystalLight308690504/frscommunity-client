package com.crystallightghot.frscommunityclient.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.contract.LoginContract;
import com.crystallightghot.frscommunityclient.presenter.LoginPresenter;
import com.crystallightghot.frscommunityclient.utils.XToastUtils;
import com.crystallightghot.frscommunityclient.view.activity.BaseActivity;
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;
import com.crystallightghot.frscommunityclient.view.activity.MainActivity;
import com.crystallightghot.frscommunityclient.view.enums.MessageCode;
import com.crystallightghot.frscommunityclient.view.message.RegisterMessage;
import com.crystallightghot.frscommunityclient.view.message.RequestMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentManageUtil;
import com.google.android.material.textfield.TextInputEditText;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends BaseFragment implements LoginContract.View {
    @BindView(R.id.phoneNumber)
    TextInputEditText phoneName;
    @BindView(R.id.iePassword)
    TextInputEditText iePassword;

    @BindView(R.id.login)
    AppCompatButton login;
    @BindView(R.id.register)
    AppCompatButton register;

    LoginPresenter presenter;

    BaseActivity activity;
    User user = new User();
    final int MESSAGE_CODE = 1001;


    public LoginFragment() {
        presenter = LoginPresenter.getInstance(this);
    }

    public static LoginFragment newInstance(String param1) {

        return new LoginFragment();
    }

    private void init() {
        activity = (BaseActivity) getActivity();
        presenter.checkUserLoginState();
    }



    /**
     * 登入消息
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RequestMessage<User> message) {
        if (message.getMessageCode() != MessageCode.LOGIN_RESULT) {// 只收有关本fragment的消息
            return;
        }
        // 登陆失败
        if (!message.isSuccess()) {
            XToastUtils.error(message.getMessage());
            return;
        }

        Intent intent = new Intent(activity, MainActivity.class);
        startActivity(intent);
        activity.finish();
    }

    /**
     * 注册账号消息
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RegisterMessage message) {
        // 注册账号成功 将账号消息填入
        if (message.isSuccess()) {
            phoneName.setText(message.getPhoneNumber());
            iePassword.setText(message.getPassword());
        }
    }


    private void loginAction() {
        String passwordInput = iePassword.getText().toString();
        Editable phoneNameText = phoneName.getText();
        int length = phoneNameText.length();

        if (length != 11) {
            XToastUtils.error("请输入正确手机号");
            return;
        }
        if (null == passwordInput) {
            XToastUtils.error("请输入密码");
            return;
        }

        user.setPhoneNumber(phoneNameText.toString());
        user.setPassword(passwordInput);

        presenter.login(user);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    @OnClick({R.id.phoneNumber, R.id.iePassword, R.id.login, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.login:
                loginAction();
                break;
            case R.id.register:
                FRSCFragmentManageUtil.intentToFragment(RegisterFragment.newInstance("RegisterUserFragment"), (BaseFragmentActivity) getActivity(),true);
                break;
            default:
                break;
        }
    }


    @Override
    public int getMessageCode() {
        return MESSAGE_CODE;
    }

    @Override
    public void stateToLogin() {
        Intent intent = new Intent(activity, MainActivity.class);
        startActivity(intent);
        activity.finish();
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}