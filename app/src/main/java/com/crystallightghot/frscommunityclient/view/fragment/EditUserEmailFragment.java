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
import com.crystallightghot.frscommunityclient.presenter.EditUserEmailPresenter;
import com.crystallightghot.frscommunityclient.view.enums.MessageCode;
import com.crystallightghot.frscommunityclient.view.message.TimeMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.*;
import com.google.android.material.textfield.TextInputEditText;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditUserEmailFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * @author 30869
 */
public class EditUserEmailFragment extends BaseFragment {

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

    String verifyCode;
    EditUserEmailPresenter presenter;

    public EditUserEmailFragment() {
        presenter = new EditUserEmailPresenter(this);
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
        FRSCEventBusUtil.register(this);
        initView();
        return view;
    }

    private void initView() {
        User user = FRSCApplicationContext.getUser();
        ieEmail.setText(user.getEmail());
    }

    @OnClick({R.id.btnSendVerifyCode, R.id.btnModify})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSendVerifyCode:
                clickSendVerifyBtnAction();
                break;
            case R.id.btnModify:
                modifyAction();
                break;
        }
    }

    private void modifyAction() {

        String email = ieEmail.getText().toString();
        if (!presenter.verifyEmailPattern(email)){
            return;
        }

        String verifyCodeInput = ieVerifyCode.getText().toString();
        if (null == verifyCode || verifyCodeInput.equals("")) {
            XToastUtils.warning("请输入验证码");
            return;
        }

        presenter.modifyUserEmail(email);
    }

    private void clickSendVerifyBtnAction() {
        verifyCode = FRSCVerifyCodeUtil.getVerifyCode();
        XToastUtils.info("验证码：" + verifyCode);
        btnSendVerifyCode.setEnabled(false);
        Runnable runnable = () -> {
            int i = 60;
            try {
                while (i-- > 0) {
                    Thread.sleep(1000);
                    TimeMessage timeMessage = new TimeMessage(i, MessageCode.EDITE_EMAIL_SEND_COD_BUTTON_NO_ENABLE_TIME);
                    EventBus.getDefault().post(timeMessage);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        FRSCThreadPoolUtil.executeThread(runnable);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(TimeMessage message) {
        if (message.getCode() != MessageCode.EDITE_EMAIL_SEND_COD_BUTTON_NO_ENABLE_TIME) {
            return;
        }
        int time = message.getTime();
        // 修改按钮显示字体
        btnSendVerifyCode.setText(time + "");
        btnSendVerifyCode.setEnabled(false);
        if (time == 0) {
            btnSendVerifyCode.setEnabled(true);
            btnSendVerifyCode.setText("发送验证码");
        }
    }

    public void clearDataInput(){
        verifyCode = null;
        ieVerifyCode.setText("");
    }

}