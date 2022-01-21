package com.crystallightghot.frscommunityclient.view.activity;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.utils.ThreadPoolUtil;
import com.crystallightghot.frscommunityclient.view.fragment.LoginFragment;
import com.crystallightghot.frscommunityclient.view.message.TimeMessage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.crystallightghot.frscommunityclient.view.util.FRSCIntentUtil.IntentToSingleFragmentActivity;

public class AccessActivity extends BaseActivity {

    @BindView(R.id.tv_time)
    TextView tvTime;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                    EventBus.getDefault().post(new TimeMessage(i,0));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        ThreadPoolUtil.executeThread(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onGetStickyEvent(TimeMessage message) {
        int time = message.getTime();
        tvTime.setText(time+"");

        if (time == 0){
            IntentToSingleFragmentActivity(this,LoginFragment.newInstance("login"));
            finish();
        }
    }


}
