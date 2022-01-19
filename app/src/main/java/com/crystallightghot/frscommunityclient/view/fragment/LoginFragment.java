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
import com.crystallightghot.frscommunityclient.utils.XToastUtils;
import com.crystallightghot.frscommunityclient.view.activity.AbstractFragmentNeededActivity;
import com.crystallightghot.frscommunityclient.view.activity.BaseActivity;
import com.crystallightghot.frscommunityclient.view.activity.HomeActivityAbstract;
import com.crystallightghot.frscommunityclient.view.messageEvent.RegisterMessage;
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
public class LoginFragment extends Fragment {
    static LoginFragment loginFragment;
    @BindView(R.id.phoneNumber)
    TextInputEditText phoneName;
    @BindView(R.id.iePassword)
    TextInputEditText iePassword;

    @BindView(R.id.login)
    AppCompatButton login;
    @BindView(R.id.register)
    AppCompatButton register;

    private String mParam1;

    BaseActivity activity;

    public LoginFragment() {

    }

    public static LoginFragment newInstance(String param1) {

        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        init();
        EventBus.getDefault().unregister(this);
        return view;
    }

    private void init() {
        activity = (BaseActivity) getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @OnClick({R.id.phoneNumber, R.id.iePassword, R.id.login, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnSendVerifyCode:
                sendVerifyCode();
                break;
            case R.id.login:
                login();

                break;
            case R.id.register:
                ActivityUtile.showFragment(RegisterUserFragment.newInstance("RegisterUserFragment"), (AbstractFragmentNeededActivity) getActivity());
                break;
        }
    }

    /**
     * 发送验证码
     */
    private void sendVerifyCode() {

    }

    private void login() {
        String codeInput = iePassword.getText().toString();
        Editable phoneNameText = phoneName.getText();
        int length = phoneNameText.length();

        if (length != 11 || codeInput == null) {
            XToastUtils.error( "请输入正确手机号");
            return;
        }

        Intent intent = new Intent(activity, HomeActivityAbstract.class);
        startActivity(intent);

    }


    @Override
    public void onStop() {
        super.onStop();
//        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RegisterMessage message){
        Log.d("TAG", "Login getMessage: " +message);
        phoneName.setText(message.getPhoneNumber());
        iePassword.setText(message.getPassword());
    }
}