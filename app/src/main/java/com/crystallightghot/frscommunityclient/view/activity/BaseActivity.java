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

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    // 加入到返回栈的fragment
   private final List <Fragment> fragmentsAddedInStack = new ArrayList<>();

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

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        // 测试代码段
        Log.d("onBackPressed", "========================================");
        Log.d("onBackPressed", "栈里fragment一共有  " + fragmentManager.getBackStackEntryCount());
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            Log.d("onBackPressed", "BackStackEntryCount" + i +" "+ fragmentManager.getBackStackEntryAt(i).getName());
        }

        int stackEntryCount = fragmentManager.getBackStackEntryCount();
        Log.d("onBackPressed", "========================================");

        fragmentManager.popBackStack();
        if (fragmentsAddedInStack.size() > 0){
            fragmentsAddedInStack.remove(fragmentsAddedInStack.size()-1);
        }

        // 如果返回栈里还有Fragment 就显示出来
        if ( stackEntryCount> 0 ) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.show(fragmentsAddedInStack.get(stackEntryCount-1));
            transaction .commit();
        }else { // 如果返回栈里没有Fragment 就直接销毁activity
            finish();
        }
    }

    public List<Fragment> getFragmentsAddedInStack() {
        return fragmentsAddedInStack;
    }


}
