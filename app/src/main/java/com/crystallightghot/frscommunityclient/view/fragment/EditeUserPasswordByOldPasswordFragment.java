package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.presenter.EditUserPasswordPresenter;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentManageUtil;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditeUserPasswordByOldPasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 * @author 30869
 */
public class EditeUserPasswordByOldPasswordFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.ieOldPassword)
    TextInputEditText ieOldPassword;
    @BindView(R.id.idNewPassword)
    TextInputEditText idNewPassword;
    @BindView(R.id.ieConfirmPassword)
    TextInputEditText ieConfirmPassword;
    @BindView(R.id.btnModify)
    Button btnModify;
    @BindView(R.id.btnOldPasswordForgot)
    TextView btnOldPasswordForgot;

    private String mParam1;

    BaseFragmentActivity activity;

    EditUserPasswordPresenter presenter;
    public EditeUserPasswordByOldPasswordFragment() {
        presenter = new EditUserPasswordPresenter(this);
    }

    public static EditeUserPasswordByOldPasswordFragment newInstance(String param1) {
        EditeUserPasswordByOldPasswordFragment fragment = new EditeUserPasswordByOldPasswordFragment();
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
        View view = inflater.inflate(R.layout.fragment_edite_user_password_by_old_password, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        activity = (BaseFragmentActivity) getActivity();
    }

    @OnClick({R.id.btnModify, R.id.btnOldPasswordForgot})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnModify:
                modifyAction();
                break;
            case R.id.btnOldPasswordForgot:
                FRSCFragmentManageUtil.intentToFragment(EditeUserPasswordByPhoneNumberFragment.newInstance(""), (BaseFragmentActivity) getActivity(),true);
                break;
        }
    }

    private void modifyAction() {
        User user = FRSCApplicationContext.getUser();
        String oldPassword = ieOldPassword.getText().toString();

        String newPassword = idNewPassword.getText().toString();
        String confirmPassword = ieConfirmPassword.getText().toString();

        if (oldPassword.equals("")) {
            showWarningToast("请输入修改的密码");
            return;
        }else if (newPassword.equals("")) {
            showWarningToast("请再次输入密码");
            return;
        }else if (confirmPassword.equals("")) {
            showWarningToast("请再次输入密码");
            return;
        }else if (!newPassword.equals(confirmPassword)) {
            showWarningToast("两次密码不一致");
            return;
        }

        user.setOldPassword(oldPassword);
        user.setPassword(newPassword);
        presenter.modifyUserPasswordByOldPassword(user);
    }

    public void clearDataInput() {
        ieOldPassword.setText("");
        idNewPassword.setText("");
        ieConfirmPassword.setText("");
    }
}