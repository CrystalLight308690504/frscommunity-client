package com.crystallightghot.frscommunityclient.view.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.xuexiang.xui.XUI;

/**
 * @Date 2022/1/12
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public abstract class BaseActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XUI.initTheme(this);
    }

    @Override
    protected void onStart() {
        FRSCApplicationContext.setActivity(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
