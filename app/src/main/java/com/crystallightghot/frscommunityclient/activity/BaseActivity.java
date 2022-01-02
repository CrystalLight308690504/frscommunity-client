package com.crystallightghot.frscommunityclient.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.crystallightghot.frscommunityclient.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contract_frscbase_contract);
        QMUIStatusBarHelper.translucent(BaseActivity.this);
        QMUIStatusBarHelper.setStatusBarLightMode(BaseActivity.this);
    }
}
