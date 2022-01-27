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
import com.crystallightghot.frscommunityclient.utils.EventBusUtil;
import com.crystallightghot.frscommunityclient.utils.ThreadPoolUtil;
import com.crystallightghot.frscommunityclient.utils.VerifyCodeUtil;
import com.crystallightghot.frscommunityclient.utils.XToastUtils;
import com.crystallightghot.frscommunityclient.view.enums.MessageCode;
import com.crystallightghot.frscommunityclient.view.message.TimeMessage;
import com.google.android.material.textfield.TextInputEditText;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditeUserPasswordByPhoneNumberFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditeUserPasswordByPhoneNumberFragment extends Fragment {

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

    private String mParam1;

    public EditeUserPasswordByPhoneNumberFragment() {
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
        EventBusUtil.register(this);
        return view;
    }

    private void initView() {

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
        Editable phoneNameText = iePhoneNumber.getText();
        String password = iePassword.getText().toString();
        String comfirmPassword = ieNewPassword.getText().toString();
        int length = phoneNameText.length();

        if (length != 11) {
            XToastUtils.error("请输入正确手机号");
            return;
        }
        if (null == verifyCode || verifyCodeInput.equals("")) {
            XToastUtils.error("请输入验证码");
            return;
        }

        if (password.equals("") || comfirmPassword.equals("")) {
            XToastUtils.error("请输入密码");
            return;
        }else if (!comfirmPassword.equals(password)) {
            XToastUtils.error("两次输入密码不一致");
        }
        if (!verifyCodeInput.equals(verifyCode)) {
            XToastUtils.error("验证码错误");
            return;
        }
    }

    private void SendVerifyCodeStateAction() {
        // 随机获取验证码
        verifyCode = VerifyCodeUtil.getVerifyCode();
        XToastUtils.info("验证码：" + verifyCode);
        btnSendVerifyCode.setEnabled(false);
        Runnable runnable = () -> {
            int i = 60;
            try {
                while (i-- > 0) {
                    Thread.sleep(1000);
                    EventBus.getDefault().post(new TimeMessage(i, MessageCode.EDITE_PASSWD_SEND_COD_BUTTON_NO_ENABLE_TIME));
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        ThreadPoolUtil.executeThread(runnable);
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