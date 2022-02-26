package com.crystallightghot.frscommunityclient.view.activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 在activity中需要使用到单个fragment进行页面跳转的
 * 所有继承此类的activity都要在 Activity的OnStart()前的生命周期使用 setDefaultFragment(Fragment defaultFragment) 方法传入默认加载的Fragmgent
 * 替换fragment的布局的ID 默认为R.id.fragmentContainer 将要替换fragment的容器 可以在OnStart()前的生命周期重新设置
 * <p>
 * 搭配 {@link FRSCFragmentUtil }工具类使用
 */
@Getter
public  class BaseFragmentActivity extends BaseActivity {

    // 存储加入到返回栈的fragment 默认加载到activity的fragment为栈底 模拟返回栈的内容
    private final List<Fragment> fragmentsAddedInBackStack = new ArrayList<>();
    // 存储添加到activity的不添加到返回栈的同等显示的fragment 例如：HomeFragment和BlogFragment 但都在在activity中显示（都不加入到返回栈中）
    private final List<Fragment> fragmentsNoInBackStack = new ArrayList<>();

    @Setter
    // 用来替换fragment的布局的ID 默认为R.id.fragmentContainer
    private int fragmentContainerId = R.id.fragmentContainer;

    // 默认在activity显示的fragment并且不加入到返回栈的fragment
    @Setter
    Fragment defaultFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        FRSCFragmentUtil.removeFragments(this, fragments);
    }


    @Override
    protected void onStart() {
        FRSCApplicationContext.setBaseFragmentActivity(this);
        super.onStart();
        // 添加默认fragment 到页面
        FRSCFragmentUtil.intentToFragment(getDefaultFragment(), this, false);
    }

    /**
     * 重写返回按钮方法 退fragment时退到null fragments时直接退出
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // 返回栈里还有fragment
        if (fragmentsAddedInBackStack.size() > 0) {
            // 退出栈顶的fragment
            fragmentsAddedInBackStack.remove(fragmentsAddedInBackStack.size() - 1);
            // 将退栈后 栈的栈顶的fragment显示出来
            // 如果fragment返回栈里还有fragment
            FragmentManager fragmentManager = this.getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (fragmentsAddedInBackStack.size() > 0) {
                // 将放回栈的栈顶的fragment显示出来
                transaction.show(fragmentsAddedInBackStack.get(fragmentsAddedInBackStack.size() - 1));
            } else if (fragmentsNoInBackStack.size() > 0) {
                // 将默认绑定在activity的fragment显示出来
                transaction.show(fragmentsNoInBackStack.get(fragmentsNoInBackStack.size() - 1));
                fragmentsNoInBackStack.remove(fragmentsNoInBackStack.size() - 1);
            }
            transaction.commitNowAllowingStateLoss();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FRSCFragmentUtil.removeFragments(this,fragmentsAddedInBackStack);
        FRSCFragmentUtil.removeFragments(this,fragmentsNoInBackStack);
    }
}
