package com.crystallightghot.frscommunityclient.view.activity;

import android.os.Bundle;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.message.FragmentChangeMessage;
import com.crystallightghot.frscommunityclient.view.util.FRSCActivityUtile;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SingleFragmentActivity extends BaseFragmentActivity {

   public static final  int MESSAGE_COD = 8001;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_one_fragment_showed);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getMessage(FragmentChangeMessage message){
        if (message.getCode() != MESSAGE_COD){
            return;
        }
        // 设置默认加载Fragment
        setDefaultFragment(message.getDefaultFragment());
        // 显示fragment
        FRSCActivityUtile.showFragment(getDefaultFragment(),this,false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}