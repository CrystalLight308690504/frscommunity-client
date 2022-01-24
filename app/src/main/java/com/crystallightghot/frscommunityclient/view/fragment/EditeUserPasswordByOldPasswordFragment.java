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
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;
import com.crystallightghot.frscommunityclient.view.util.FRSCShowFragmentToActivityUtil;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditeUserPasswordByOldPasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditeUserPasswordByOldPasswordFragment extends Fragment {

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

    public EditeUserPasswordByOldPasswordFragment() {
        // Required empty public constructor
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
                break;
            case R.id.btnOldPasswordForgot:
                FRSCShowFragmentToActivityUtil.showFragmentAddedToBackStack(EditeUserPasswordByPhoneNumberFragment.newInstance(""));
                break;
        }
    }
}