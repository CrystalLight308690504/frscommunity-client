package com.crystallightghot.frscommunityclient.view.fragment;

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
import com.crystallightghot.frscommunityclient.contract.RegisterContract;
import com.crystallightghot.frscommunityclient.presenter.RegisterPresenter;
import com.crystallightghot.frscommunityclient.view.util.FRSCThreadPoolUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCVerifyCodeUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import com.crystallightghot.frscommunityclient.view.activity.BaseActivity;
import com.crystallightghot.frscommunityclient.view.value.MessageCode;
import com.crystallightghot.frscommunityclient.view.message.RegisterMessage;
import com.crystallightghot.frscommunityclient.view.message.TimeMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.google.android.material.textfield.TextInputEditText;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends BaseFragment implements RegisterContract.View {


    // 发送的验证码
    String verifyCode;
    BaseActivity activity;
    RegisterPresenter presenter;
    @BindView(R.id.iePhoneNumber)
    TextInputEditText iePhoneNumber;
    @BindView(R.id.iePassword)
    TextInputEditText iePassword;
    @BindView(R.id.ieVerifyCode)
    TextInputEditText ieVerifyCode;
    @BindView(R.id.btnSendVerifyCode)
    AppCompatButton btnSendVerifyCode;
    @BindView(R.id.register)
    AppCompatButton register;


    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance(String param1) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_user, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        activity = (BaseActivity) getActivity();
        presenter = new RegisterPresenter(this);

    }


    @OnClick({R.id.btnSendVerifyCode, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSendVerifyCode:
                if (btnSendVerifyCode.isEnabled()) {
                    SendVerifyCodeStateAction();
                }
                break;
            case R.id.register:
                registerAction();
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(TimeMessage message) {
        if (message.getCode() != MessageCode.REGISTER_BUTTON_NO_ENABLE_TIME) {
            return;
        }
        int time = message.getTime();
        // 修改按钮显示字体
        btnSendVerifyCode.setText(time + "");
        btnSendVerifyCode.setEnabled(false);
        if (time == 0) {
            btnSendVerifyCode.setEnabled(true);
            btnSendVerifyCode.setText("发送验证码");
        }
    }

    private void registerAction() {
        String verifyCodeInput = ieVerifyCode.getText().toString();
        Editable phoneNameText = iePhoneNumber.getText();
        String password = iePassword.getText().toString();
        int length = phoneNameText.length();

        if (length != 11) {
            XToastUtils.error("请输入正确手机号");
            return;
        }
        if (null == verifyCode || verifyCodeInput.equals("")) {
            XToastUtils.error("请输入验证码");
            return;
        }
        if (password.equals("")) {
            XToastUtils.error("请输入密码");
            return;
        }
        if (!verifyCodeInput.equals(verifyCode)) {
            XToastUtils.error("验证码错误");
            return;
        }
        User user = User.getInstance();
        user.setPhoneNumber(phoneNameText.toString());
        user.setPassword(password);

        presenter.loadData(user);
    }

    private void SendVerifyCodeStateAction() {
        // 随机获取验证码
        verifyCode = FRSCVerifyCodeUtil.getVerifyCode();
        XToastUtils.info("验证码：" + verifyCode);
        btnSendVerifyCode.setEnabled(false);

        Runnable runnable = () -> {
            int i = 60;
            try {
                while (i-- > 0) {
                    Thread.sleep(1000);
                    EventBus.getDefault().post(new TimeMessage(i, MessageCode.REGISTER_BUTTON_NO_ENABLE_TIME));
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        FRSCThreadPoolUtil.executeThread(runnable);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RegisterMessage message) {
        if (!message.isSuccess()) {
            XToastUtils.error(message.getMessage());
        } else {
            // 使验证码失效
            verifyCode = null;
            ieVerifyCode.setText("");
            XToastUtils.success(message.getMessage());
            activity.onBackPressed();
        }
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