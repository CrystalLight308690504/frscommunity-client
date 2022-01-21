package com.crystallightghot.frscommunityclient.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
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
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;
import com.crystallightghot.frscommunityclient.view.activity.BaseActivity;
import com.crystallightghot.frscommunityclient.view.activity.HomeActivityAbstract;
import com.crystallightghot.frscommunityclient.view.messageEvent.UIChangeMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.ActivityUtile;
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
    static LoginFragment loginFragment;
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
    User user  = new User();
    final  int MESSAGE_CODE = 1001;

    public static LoginFragment newInstance(String param1) {

        return new LoginFragment();
    }

    private void init() {
        activity = (BaseActivity) getActivity();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(UIChangeMessage message) {
        Log.d("TAG", "UIChangeMessage: " + message.getMessage());
        if (message.getCode() != MESSAGE_CODE){
            return;
        }
        if ( !message.isSuccess()){
            XToastUtils.error(message.getMessage());
            return;
        }

        Intent intent = new Intent(activity, HomeActivityAbstract.class);
        startActivity(intent);
        activity.finish();
    }

    private void loginAction() {
        String passwordInput = iePassword.getText().toString();
        Editable phoneNameText = phoneName.getText();
        int length = phoneNameText.length();

        if (length != 11) {
            XToastUtils.error("请输入正确手机号");
            return;
        }
        if (null == passwordInput ) {
            XToastUtils.error("请输入密码");
            return;
        }

        user.setPhoneNumber(phoneNameText.toString());
        user.setPassword(passwordInput);
        presenter = LoginPresenter.getInstance(this);
        presenter.loadData(user);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        init();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @OnClick({R.id.phoneNumber, R.id.iePassword, R.id.login, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnSendVerifyCode:
                sendVerifyCodeAction();
                break;
            case R.id.login:
                loginAction();
                break;
            case R.id.register:
                ActivityUtile.showFragment(RegisterUserFragment.newInstance("RegisterUserFragment"), (BaseFragmentActivity) getActivity());
                break;
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 发送验证码
     */
    private void sendVerifyCodeAction() {

    }

    @Override
    public int getMessageCode() {
        return MESSAGE_CODE;
    }
}