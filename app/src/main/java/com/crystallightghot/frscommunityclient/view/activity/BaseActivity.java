package com.crystallightghot.frscommunityclient.view.activity;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.util.ActivityUtile;
import com.xuexiang.xui.XUI;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    // 加入到返回栈的fragment 默认加载到activity的fragment为栈底 模拟返回栈的内容
    private final List<Fragment> fragmentsAddedInStack = new ArrayList<>();
    // 添加到activity的fragment
    private final List<Fragment> allFragmentAdded = new ArrayList<>();
    // 用来替换fragment的布局的ID
    private int fragmentContainerId;
    // 默认在activity显示的fragment并且不加入到返回栈的fragment
    Fragment defaultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contract_frscbase_contract);
        Context context = XUI.getContext();
        XUI.init((Application) context);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        ActivityUtile.removeAllFragments(this,fragments);
    }

    /**
     * 重写返回按钮方法 退fragment时退到null fragments时直接退出
     */
    @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        // 测试代码段
        Log.d("onBackPressed", "========================================");
        Log.d("onBackPressed", "系统栈里fragment一共有  " + fragmentManager.getBackStackEntryCount());
        Log.d("onBackPressed", "fragmentsAddedInStack fragment一共有  " + fragmentsAddedInStack.size());
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            Log.d("onBackPressed", "BackStackEntryCount" + i + " " + fragmentManager.getBackStackEntryAt(i).getName());
        }

        Log.d("onBackPressed", "========================================");

        int stackEntryCount = fragmentManager.getBackStackEntryCount();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 如果返回栈里只有一个fragment 推出栈后只要显示默认的fragment
        if (stackEntryCount >= 1) {
            fragmentManager.popBackStack();
            // 推出栈顶的fragment
            fragmentsAddedInStack.remove(fragmentsAddedInStack.size() - 1);
            // 将退栈后 栈的最后一个fragment显示
            transaction.show(fragmentsAddedInStack.get(fragmentsAddedInStack.size() - 1));
            transaction.commit();
        }else if (stackEntryCount == 0) { // 如果返回栈里没有Fragment 就直接销毁activity
            finish();
        }
    }

    public List<Fragment> getFragmentsAddedInStack() {
        return fragmentsAddedInStack;
    }

    public List<Fragment> getAllFragmentAdded() {
        return allFragmentAdded;
    }

    public int getFragmentContainerId() {
        return fragmentContainerId;
    }

    public void setFragmentContainerId(int fragmentContainerId) {
        this.fragmentContainerId = fragmentContainerId;
    }

    public Fragment getDefaultFragment() {
        return defaultFragment;
    }

    public void setDefaultFragment(Fragment defaultFragment) {
        fragmentsAddedInStack.add(defaultFragment);
        this.defaultFragment = defaultFragment;
    }

}
