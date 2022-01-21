package com.crystallightghot.frscommunityclient.view.activity;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.util.ActivityUtile;
import com.xuexiang.xui.XUI;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class BaseFragmentActivity extends BaseActivity {

    // 加入到返回栈的fragment 默认加载到activity的fragment为栈底 模拟返回栈的内容
    private final List<Fragment> fragmentsAddedInStack = new ArrayList<>();
    // 添加到activity的fragment
    private final List<Fragment> allFragmentAdded = new ArrayList<>();
    // 用来替换fragment的布局的ID 默认为R.id.fragmentContainer
    @Setter
    private int fragmentContainerId = R.id.fragmentContainer;


    // 默认在activity显示的fragment并且不加入到返回栈的fragment
    Fragment defaultFragment;

    public BaseFragmentActivity() {
        setDefaultFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = XUI.getContext();
        XUI.init((Application) context);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        ActivityUtile.removeAllFragments(this, fragments);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 添加默认fragment 到页面
        ActivityUtile.showFragment(getDefaultFragment(), this, false);
    }

    /**
     * 重写返回按钮方法 退fragment时退到null fragments时直接退出
     */
    @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        int stackEntryCount = fragmentManager.getBackStackEntryCount();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 如果返回栈里只有一个fragment了 退出栈顶的这个唯一一个的fragment后,显示默认绑定到activity的的fragment（默认绑定到activity的的fragment默认放到自定义的存储栈里）
        if (stackEntryCount >= 1) {// 返回栈里还有添加到栈里的fragment
            fragmentManager.popBackStack();
            // 推出栈顶的fragment
            fragmentsAddedInStack.remove(fragmentsAddedInStack.size() - 1);
            // 将退栈后 栈的栈顶的fragment显示出来
            transaction.show(fragmentsAddedInStack.get(fragmentsAddedInStack.size() - 1));
            transaction.commit();
        } else if (stackEntryCount == 0) { // 如果返回栈里没有Fragment 就直接销毁activity
            finish();
        }
    }

    /**
     *
     * @param defaultFragment 默认加载、、绑定在activity并且不加入系统回退栈的fragment
     */
    public void setDefaultFragment(Fragment defaultFragment) {
        // 将默认加载的fragment加入到自定义的回退栈中
        fragmentsAddedInStack.add(defaultFragment);
        this.defaultFragment = defaultFragment;
    }

    /**
     * 设置默认加载的fragment
     */
    abstract void setDefaultFragment();
}