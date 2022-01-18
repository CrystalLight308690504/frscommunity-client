package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.activity.FragmentNeededActivity;
import com.crystallightghot.frscommunityclient.view.messageEvent.VerifyCodeMessageEvent;
import com.crystallightghot.frscommunityclient.view.util.ActivityUtile;
import com.crystallightghot.frscommunityclient.view.util.ThreadPoolUtil;
import com.google.android.material.textfield.TextInputEditText;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    static LoginFragment loginFragment;
    @BindView(R.id.phoneName)
    TextInputEditText phoneName;
    @BindView(R.id.codVerified)
    TextInputEditText codVerified;
    @BindView(R.id.sendVerifyCode)
    AppCompatButton sendVerifyCode;
    @BindView(R.id.login)
    AppCompatButton login;
    @BindView(R.id.register)
    AppCompatButton register;

    private String mParam1;

    public LoginFragment() {

    }

    public static LoginFragment newInstance(String param1) {
        if (null == loginFragment) {
            loginFragment = new LoginFragment();
            Bundle args = new Bundle();
            loginFragment.setArguments(args);
        }

        return loginFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    public void init() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @OnClick({R.id.phoneName, R.id.codVerified, R.id.sendVerifyCode, R.id.login, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.sendVerifyCode:
                setSendVerifyCodeState(false);

                break;
            case R.id.login:
                break;
            case R.id.register:
                ActivityUtile.showFragment(RegisterUserFragment.newInstance("RegisterUserFragment"), (FragmentNeededActivity) getActivity());
                Toast.makeText(getActivity(), "点击； 注册", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void setSendVerifyCodeState(boolean enable) {
        sendVerifyCode.setEnabled(enable);
        if (enable) {
            sendVerifyCode.setText("发送验证码");
        } else {
            sendVerifyCode.setText("0");
            ThreadPoolExecutor poolExecutor = ThreadPoolUtil.getThreadPoolExecutor();
            Runnable runnable = new Runnable() {
                int i = 60;
                @Override
                public void run() {

                    try {
                        while (i-- >0){
                            Thread.sleep(1000);
                            EventBus.getDefault().post(new VerifyCodeMessageEvent(i));
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            poolExecutor.execute(runnable);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(VerifyCodeMessageEvent message) {
        int time = message.getTime();
        Log.d("TAG", "onGetMessage: " + time );

        // 修改按钮显示字体
        sendVerifyCode.setText(time+"");
        if (time == 0){
            sendVerifyCode.setEnabled(true);
            sendVerifyCode.setText("发送验证码");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}