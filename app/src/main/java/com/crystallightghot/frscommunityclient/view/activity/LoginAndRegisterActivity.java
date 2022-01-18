package com.crystallightghot.frscommunityclient.view.activity;

import android.os.Bundle;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.fragment.LoginFragment;
import org.greenrobot.eventbus.EventBus;

public class LoginAndRegisterActivity extends FragmentNeededActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        ButterKnife.bind(this);
        init();
    }

    @Override
    void setDefaultFragment() {
        setDefaultFragment(LoginFragment.newInstance("LoginFragment"));
    }

    public void init() {


    }

}