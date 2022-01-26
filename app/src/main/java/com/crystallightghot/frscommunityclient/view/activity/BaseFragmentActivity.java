package com.crystallightghot.frscommunityclient.view.activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.utils.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSFragmentManageUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 在activity中需要使用到单个fragment进行页面跳转的
 * 所有继承此类的activity都要在 Activity的OnStart()前的生命周期使用 setDefaultFragment(Fragment defaultFragment) 方法传入默认加载的Fragmgent
 * 替换fragment的布局的ID 默认为R.id.fragmentContainer 将要替换fragment的容器 可以在OnStart()前的生命周期重新设置
 * <p>
 * 搭配 {@link FRSFragmentManageUtil }工具类使用
 */
@Getter
public abstract class BaseFragmentActivity extends BaseActivity {

    // 存储加入到返回栈的fragment 默认加载到activity的fragment为栈底 模拟返回栈的内容
    private final List<Fragment> fragmentsAddedInBackStack = new ArrayList<>();
    // 存储添加到activity的不添加到返回栈的同等显示的fragment 例如：HomeFragment和BlogFragment 但都在在activity中显示（都不加入到返回栈中）
    private final List<Fragment> fragmentsNoInBackStack = new ArrayList<>();

    @Setter
    // 用来替换fragment的布局的ID 默认为R.id.fragmentContainer
    private int fragmentContainerId = R.id.fragmentContainer;

    // 默认在activity显示的fragment并且不加入到返回栈的fragment
    @Setter
    Fragment defaultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        FRSFragmentManageUtil.removeFragments(this, fragments);
    }


    @Override
    protected void onStart() {
        FRSCApplicationContext.setBaseFragmentActivity(this);
        super.onStart();
        // 添加默认fragment 到页面
        FRSFragmentManageUtil.intentToFragment(getDefaultFragment(),this,false);
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
            fragmentsAddedInBackStack.remove(fragmentsAddedInBackStack.size() - 1);

            // 将退栈后 栈的栈顶的fragment显示出来
            // 如果fragment返回栈里还有fragment
            if (fragmentsAddedInBackStack.size() > 0) {
                transaction.show(fragmentsAddedInBackStack.get(fragmentsAddedInBackStack.size() - 1));
            } else {// fragment返回栈里没有fragment了 则显示加载到activity的默认fragment
                transaction.show(fragmentsNoInBackStack.get(fragmentsNoInBackStack.size() - 1));
            }
            transaction.commit();
        } else if (stackEntryCount == 0) { // 如果返回栈里没有Fragment 就直接销毁activity
            finish();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
