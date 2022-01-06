package com.crystallightghot.frscommunityclient.view.activity;

import android.app.Application;
import android.content.Context;
import android.view.KeyEvent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import com.crystallightghot.frscommunityclient.R;
import com.xuexiang.xui.XUI;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contract_frscbase_contract);
        Context context = XUI.getContext();
        XUI.init((Application) context);
    }

    /**
     * 重写返回按钮方法 退fragment时退到null fragments时直接退出
     */
    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 剩下的只有空的activity
        if (fragmentManager.getBackStackEntryCount() == 1) {
            finish();
        } else {
            fragmentManager.popBackStack();
        }
        super.onBackPressed();
    }

}
