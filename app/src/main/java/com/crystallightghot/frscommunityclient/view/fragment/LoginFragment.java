package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
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
import com.crystallightghot.frscommunityclient.view.util.ActivityUtile;
import com.google.android.material.textfield.TextInputEditText;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick({R.id.phoneName, R.id.codVerified, R.id.sendVerifyCode, R.id.login, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.sendVerifyCode:
                break;
            case R.id.login:
                break;
            case R.id.register:
                ActivityUtile.showFragment(RegisterUserFragment.newInstance("RegisterUserFragment"), (FragmentNeededActivity) getActivity());
                Toast.makeText(getActivity(),"点击； 注册",Toast.LENGTH_LONG).show();
                break;
        }
    }
}