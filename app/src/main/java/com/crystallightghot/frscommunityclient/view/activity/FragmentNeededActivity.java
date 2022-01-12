package com.crystallightghot.frscommunityclient.view.activity;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
public abstract class FragmentNeededActivity extends BaseActivity {

    // 加入到返回栈的fragment 默认加载到activity的fragment为栈底 模拟返回栈的内容
    private final List<Fragment> fragmentsAddedInStack = new ArrayList<>();
    // 添加到activity的fragment
    private final List<Fragment> allFragmentAdded = new ArrayList<>();
    // 用来替换fragment的布局的ID
    @Setter
    private int fragmentContainerId;


    // 默认在activity显示的fragment并且不加入到返回栈的fragment
    Fragment defaultFragment;

    public FragmentNeededActivity() {
        setContainerId();
        setDefaultFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contract_frscbase_contract);
        Context context = XUI.getContext();
        XUI.init((Application) context);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        Log.d("测试", "fragments 里的数量========" + fragments.size() + "===========");
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

        // 测试代码段
        Log.d("fragmentsAddedInStack", "========================================");
        Log.d("fragmentsAddedInStack", "系统栈里fragment一共有  " + fragmentManager.getBackStackEntryCount());
        Log.d("fragmentsAddedInStack", "fragmentsAddedInStack fragment一共有  " + fragmentsAddedInStack.size());
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            Log.d("fragmentsAddedInStack", "BackStackEntryCount" + i + " " + fragmentManager.getBackStackEntryAt(i).getName());
        }
        Log.d("fragmentsAddedInStack", "========================================");

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
     * 设置fragment加载的容器的ID
     */
    abstract void setContainerId();

    /**
     * 设置默认加载的fragment
     */
    abstract void setDefaultFragment();
}
