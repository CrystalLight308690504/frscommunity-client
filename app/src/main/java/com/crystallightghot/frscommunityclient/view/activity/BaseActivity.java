package com.crystallightghot.frscommunityclient.view.activity;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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

        Log.d("onBackPressed", "========================================");
        Log.d("onBackPressed", "栈一共数量" + fragmentManager.getBackStackEntryCount());
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            Log.d("onBackPressed", "BackStackEntryCount" + i +" "+ fragmentManager.getBackStackEntryAt(i).getName());
        }
        fragmentManager.popBackStack();
        int stackEntryCount = fragmentManager.getBackStackEntryCount();
        // 如果返回栈里还有Fragment 就显示出来
        if ( stackEntryCount> 0 ) {
            FragmentManager.BackStackEntry entryAt = fragmentManager.getBackStackEntryAt(stackEntryCount - 1);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            Log.d("onBackPressed", "entryAt instanceof Fragment" + (entryAt instanceof Fragment));
            if (entryAt instanceof Fragment){
                transaction.show((Fragment)entryAt);
            }
            transaction .commit();
            Log.d("onBackPressed", " ");
            Log.d("onBackPressed", "========================================");
        }else { // 如果返回栈里没有Fragment 就直接销毁activity
            finish();
        }
    }

}
