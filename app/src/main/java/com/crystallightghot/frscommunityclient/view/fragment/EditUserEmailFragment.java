package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.utils.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditUserEmailFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * @author 30869
 */
public class EditUserEmailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.ieEmail)
    TextInputEditText ieEmail;
    @BindView(R.id.ieVerifyCode)
    TextInputEditText ieVerifyCode;
    @BindView(R.id.btnSendVerifyCode)
    AppCompatButton btnSendVerifyCode;
    @BindView(R.id.btnModify)
    Button btnModify;
    private String mParam1;

    public EditUserEmailFragment() {
        // Required empty public constructor
    }

    public static EditUserEmailFragment newInstance(String param1) {
        EditUserEmailFragment fragment = new EditUserEmailFragment();
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
        View view = inflater.inflate(R.layout.fragment_edite_user_email, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        User user = FRSCApplicationContext.getUser();
        ieEmail.setText(user.getUserName());
    }

    @OnClick({R.id.btnSendVerifyCode, R.id.btnModify})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSendVerifyCode:
                break;
            case R.id.btnModify:
                break;
        }
    }
}