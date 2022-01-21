package com.crystallightghot.frscommunityclient.view.fragment;

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
import com.crystallightghot.frscommunityclient.contract.RegisterContract;
import com.crystallightghot.frscommunityclient.presenter.RegisterPresenter;
import com.crystallightghot.frscommunityclient.utils.VerifyCodeUtil;
import com.crystallightghot.frscommunityclient.utils.XToastUtils;
import com.crystallightghot.frscommunityclient.view.activity.BaseActivity;
import com.crystallightghot.frscommunityclient.view.messageEvent.RegisterMessage;
import com.crystallightghot.frscommunityclient.view.messageEvent.TimeMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.utils.ThreadPoolUtil;
import com.google.android.material.textfield.TextInputEditText;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterUserFragment extends BaseFragment implements RegisterContract.View {

    @BindView(R.id.phoneNumber)
    TextInputEditText phoneName;
    @BindView(R.id.iePassword)
    TextInputEditText tvCodVerified;
    @BindView(R.id.btnSendVerifyCode)
    AppCompatButton btnSendVerifyCode;
    @BindView(R.id.register)
    AppCompatButton register;
    @BindView(R.id.password)
    TextInputEditText ietPassword;

    // 发送的验证码
    String verifyCode;
    BaseActivity activity;
    RegisterPresenter presenter;


    public RegisterUserFragment() {
        // Required empty public constructor
    }

    public static RegisterUserFragment newInstance(String param1) {
        RegisterUserFragment fragment = new RegisterUserFragment();
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
        EventBus.getDefault().register(this);
        init();
        return view;
    }

    private void init() {
        activity = (BaseActivity) getActivity();

    }


    @OnClick({R.id.phoneNumber, R.id.iePassword, R.id.btnSendVerifyCode, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSendVerifyCode:
                if (btnSendVerifyCode.isEnabled()) {
                    setSendVerifyCodeState(false);
                }
                break;
            case R.id.register:
                registerAction();
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(TimeMessage message) {
        if (message.getCode() != 1) {
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
        String verifyCodeInput = tvCodVerified.getText().toString();
        Editable phoneNameText = phoneName.getText();
        String password = ietPassword.getText().toString();
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
        presenter = new RegisterPresenter(this, user);
        presenter.loadData();

        // 使验证码失效
        verifyCode = null;
    }

    private void setSendVerifyCodeState(boolean enable) {
        verifyCode = VerifyCodeUtil.getVerifyCode();
        XToastUtils.info("验证码：" + verifyCode);
        btnSendVerifyCode.setEnabled(enable);
        if (enable) {
            btnSendVerifyCode.setText("发送验证码");
        } else {
            Runnable runnable = () -> {
                int i = 60;
                try {
                    while (i-- > 0) {
                        Thread.sleep(1000);
                        EventBus.getDefault().post(new TimeMessage(i, 1));
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            ThreadPoolUtil.executeThread(runnable);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RegisterMessage message) {
        Log.d("TAG", "RegisterUserFragment getMessage: " + message);
        XToastUtils.success(message.getMessage());
        activity.onBackPressed();
    }
}