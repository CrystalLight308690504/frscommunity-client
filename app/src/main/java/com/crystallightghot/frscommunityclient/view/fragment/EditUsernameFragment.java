package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.presenter.EditUsernamePresenter;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import com.crystallightghot.frscommunityclient.view.message.RegisterMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.google.android.material.textfield.TextInputEditText;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditUsernameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditUsernameFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.ieEmail)
    TextInputEditText ieUserName;
    @BindView(R.id.btnModify)
    Button btnModify;

    private String mParam1;


    EditUsernamePresenter presenter;

    public EditUsernameFragment() {
        presenter = new EditUsernamePresenter(this);
    }

    public static EditUsernameFragment newInstance(String param1) {
        EditUsernameFragment fragment = new EditUsernameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(RegisterMessage message) {

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
        View view = inflater.inflate(R.layout.fragment_edite_user_name, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        ieUserName.setText(FRSCApplicationContext.getUser().getUserName());
    }

    @OnClick(R.id.btnModify)
    public void onClick() {
        String userNameInput = ieUserName.getText().toString();
        if (userNameInput.equals("")) {
            XToastUtils.error("请输入用户名");
        }

        User user = FRSCApplicationContext.getUser();
        user.setUserName(userNameInput);
        presenter.modifyUsername(user);
    }

}