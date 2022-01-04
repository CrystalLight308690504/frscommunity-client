package com.crystallightghot.frscommunityclient.activity;

import android.app.Application;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.crystallightghot.frscommunityclient.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.xuexiang.xui.XUI;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contract_frscbase_contract);
        Context context = XUI.getContext();
        XUI.init((Application) context);
    }
}
