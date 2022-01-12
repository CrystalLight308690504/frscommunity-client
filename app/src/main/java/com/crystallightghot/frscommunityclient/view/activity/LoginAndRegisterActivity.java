package com.crystallightghot.frscommunityclient.view.activity;

import android.os.Bundle;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.fragment.LoginFragment;

public class LoginAndRegisterActivity extends FragmentNeededActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        ButterKnife.bind(this);
        init();
    }

    @Override
    void setContainerId() {
        setFragmentContainerId(R.id.fragmentContainer);
    }

    @Override
    void setDefaultFragment() {
        setDefaultFragment(LoginFragment.newInstance("LoginFragment"));
    }

    private void init() {


    }
}