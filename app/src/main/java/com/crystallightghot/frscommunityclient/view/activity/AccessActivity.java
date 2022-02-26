package com.crystallightghot.frscommunityclient.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.presenter.AccessActivityPresenter;
import com.crystallightghot.frscommunityclient.view.util.FRSCThreadPoolUtil;
import com.crystallightghot.frscommunityclient.view.value.MessageCode;
import com.crystallightghot.frscommunityclient.view.fragment.LoginFragment;
import com.crystallightghot.frscommunityclient.view.message.TimeMessage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.crystallightghot.frscommunityclient.view.util.FRSCIntentUtil.intentToSingleFragmentActivity;

public class AccessActivity extends BaseActivity {

    @BindView(R.id.tv_time)
    TextView tvTime;
    AccessActivityPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AccessActivityPresenter(this);
        setContentView(R.layout.activity_access);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onStart() {
        super.onStart();

        Runnable runnable =()-> {
            int i = 1;

            try {
                while (i-- >0){
                    Thread.sleep(1000);
                    EventBus.getDefault().post(new TimeMessage(i, MessageCode.ACCESS_TIME));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        FRSCThreadPoolUtil.executeThread(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetStickyEvent(TimeMessage message) {
        if (message.getCode() != MessageCode.ACCESS_TIME){
            return;
        }
        int time = message.getTime();
        tvTime.setText(time+"");

        if (time == 0){
            presenter.checkUserLoginState();
        }
    }

    public void stateToLogin(boolean isLogin) {
        if (isLogin) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else {
            intentToSingleFragmentActivity(LoginFragment.newInstance("login"));
        }
        finish();
    }
}
