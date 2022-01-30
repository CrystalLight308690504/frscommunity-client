package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.presenter.EditUserPasswordPresenter;
import com.crystallightghot.frscommunityclient.view.enums.MessageCode;
import com.crystallightghot.frscommunityclient.view.message.TimeMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.*;
import com.google.android.material.textfield.TextInputEditText;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditeUserPasswordByPhoneNumberFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditeUserPasswordByPhoneNumberFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.iePhoneNumber)
    TextInputEditText iePhoneNumber;
    @BindView(R.id.iePassword)
    TextInputEditText iePassword;
    @BindView(R.id.ieNewPassword)
    TextInputEditText ieNewPassword;
    @BindView(R.id.ieVerifyCode)
    TextInputEditText ieVerifyCode;
    @BindView(R.id.btnSendVerifyCode)
    AppCompatButton btnSendVerifyCode;
    @BindView(R.id.btnModify)
    AppCompatButton btnModify;

    String verifyCode;
    EditUserPasswordPresenter presenter;
    @BindView(R.id.log)
    RadiusImageView log;

    private String mParam1;
    User user = FRSCApplicationContext.getUser();

    public EditeUserPasswordByPhoneNumberFragment() {
        super();
        presenter = new EditUserPasswordPresenter(this);
    }

    public static EditeUserPasswordByPhoneNumberFragment newInstance(String param1) {
        EditeUserPasswordByPhoneNumberFragment fragment = new EditeUserPasswordByPhoneNumberFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edite_user_password_by_phone_number, container, false);
        ButterKnife.bind(this, view);
        initView();
        FRSCEventBusUtil.register(this);
        return view;
    }

    private void initView() {
        iePhoneNumber.setHint(user.getPhoneNumber());
        log.setImageDrawable(FRSCApplicationContext.getUserProfile());

    }

    @OnClick({R.id.btnSendVerifyCode, R.id.btnModify})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSendVerifyCode:
                SendVerifyCodeStateAction();
                break;
            case R.id.btnModify:
                modifyAction();
                break;
        }
    }

    private void modifyAction() {
        String verifyCodeInput = ieVerifyCode.getText().toString();
        String password = iePassword.getText().toString();
        String comfirmPassword = ieNewPassword.getText().toString();

        if (null == verifyCode || verifyCodeInput.equals("")) {
            XToastUtils.error("请输入验证码");
            return;
        }

        if (password.equals("") || comfirmPassword.equals("")) {
            XToastUtils.error("请输入密码");
            return;
        } else if (!comfirmPassword.equals(password)) {
            XToastUtils.error("两次输入密码不一致");
            return;
        }
        if (!verifyCodeInput.equals(verifyCode)) {
            XToastUtils.error("验证码错误");
            return;
        }


        user.setPassword(password);
        presenter.modifyUserPasswordByPhoneNumber(user);
    }

    private void SendVerifyCodeStateAction() {
        // 随机获取验证码
        verifyCode = FRSCVerifyCodeUtil.getVerifyCode();
        XToastUtils.info("验证码：" + verifyCode);
        btnSendVerifyCode.setEnabled(false);
        Runnable runnable = () -> {
            int i = 2;
            try {
                while (i-- > 0) {
                    Thread.sleep(1000);
                    EventBus.getDefault().post(new TimeMessage(i, MessageCode.EDITE_PASSWD_SEND_COD_BUTTON_NO_ENABLE_TIME));
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        FRSCThreadPoolUtil.executeThread(runnable);
    }

    public void clearDataInput() {
        verifyCode = null;
        iePassword.setText("");
        ieNewPassword.setText("");
        ieVerifyCode.setText("");
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(TimeMessage message) {
        if (message.getCode() != MessageCode.EDITE_PASSWD_SEND_COD_BUTTON_NO_ENABLE_TIME) {
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

}