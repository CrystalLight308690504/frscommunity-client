package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditeUserPasswordByPhoneNumberFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditeUserPasswordByPhoneNumberFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.phoneNumber)
    TextInputEditText phoneNumber;
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


    private String mParam1;

    public EditeUserPasswordByPhoneNumberFragment() {
        // Required empty public constructor
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
        return view;
    }

    private void initView() {
    }

}