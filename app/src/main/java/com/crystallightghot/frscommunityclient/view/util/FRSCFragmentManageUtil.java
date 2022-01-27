package com.crystallightghot.frscommunityclient.view.util;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.crystallightghot.frscommunityclient.utils.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;

import java.util.List;

/**
 * @author crystallightghost
 * @date 2022/1/6
 * @Version: 1.0
 * description：
 */
public class FRSCFragmentManageUtil {

    /**
     * 删除加入到activity的在FragmentManager的fragment
     */
    public static void removeFragments(AppCompatActivity activity, List<Fragment> fragmentsNeededHidden) {
        if (null == fragmentsNeededHidden || fragmentsNeededHidden.size() == 0) {
            return;
        }
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragmentsNeededHidden.size(); i++) {
            Fragment fragment = fragmentsNeededHidden.get(i);
            if (fragment.getActivity() != activity) {
                Log.d("FRSCShowFragmentToActivityUtil", "=====removeFragments: 加入的fragment 不属于此 activity======");
            } else {
                fragmentTransaction.remove(fragment);
            }
        }
        fragmentTransaction.commit();
    }

    /**
     * 隐藏所有已加入的fragment
     */
    public static void hideFragment(AppCompatActivity activity, Fragment fragment) {

        //没有就不需要隐藏
        if (null == fragment || null == activity) {
            return;
        }
        if (fragment.getActivity() != activity) {
            Log.d("FRSCShowFragmentToActivityUtil", "=====hideFragment: 加入的fragment 不属于此 activity======");
        }
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(fragment);
        transaction.commitAllowingStateLoss();
    }

    public static void intentToFragmentAddedToBackStack(Fragment showedFragment) {
        intentToFragment(showedFragment, FRSCApplicationContext.getBaseFragmentActivity(), true);
    }

    public static void intentToFragmentAddedToBackStack(Fragment showedFragment, BaseFragmentActivity activity) {
        intentToFragment(showedFragment, activity, true);
    }

    public static void intentToFragmentNoAddedToBackStack(Fragment showedFragment, BaseFragmentActivity activity) {
        intentToFragment(showedFragment, activity, false);
    }

    /**
     * 添加fragment
     *
     * @param fragmentIntended 要跳转到页面的fragment
     * @param activity         使用此方法的activity
     * @param addedToBackStack 是否将显示的fragment添加到返回栈
     *                         一般只把activity默认的加载的fragment(也就是第一个fragment)设为false 不加入退回栈中
     */
    public static void intentToFragment(Fragment fragmentIntended, BaseFragmentActivity activity, boolean addedToBackStack) {

        if (null == fragmentIntended || null == activity) {
            return;
        }
        if (fragmentIntended.getActivity() != activity) {
            Log.d("FRSCShowFragmentToActivityUtil", "=====intentToFragment: 加入的fragment 不属于此 activity======");
        }
        List<Fragment> fragmentsNoInBackStack = activity.getFragmentsNoInBackStack();
        List<Fragment> fragmentsAddedInBackStack = activity.getFragmentsAddedInBackStack();
        // 隐藏前面显示的的fragment
        // 将不添加到返回栈的fragment隐藏
        if (null != fragmentsNoInBackStack && fragmentsNoInBackStack.size() != 0) {
            hideFragments(activity, fragmentsNoInBackStack);
        }

        if (addedToBackStack) {
            // 将添加到返回栈的fragment隐藏
            if (null != fragmentsAddedInBackStack && fragmentsAddedInBackStack.size() != 0) {
                hideFragments(activity, fragmentsAddedInBackStack);
            }
        } else { // 不加入fragment回退栈 将这个fragment当作第一个显示的view 将回退栈里的fragment给全部清除回退栈
            removeFragments(activity, fragmentsAddedInBackStack);
        }

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();

        // 已经添加fragment 直接显示出来
        if (fragmentIntended.isAdded()) {
            transaction.show(fragmentIntended);
        } else {// 新添加fragment
            // 替换fragment的控件的ID
            int viewId = activity.getFragmentContainerId();
            transaction.add(viewId, fragmentIntended, fragmentIntended.getClass().getSimpleName());
            // 如果新fragment添加到回退栈
            if (addedToBackStack) {
                transaction.addToBackStack(fragmentIntended.getClass().getSimpleName());
                // 记录加入到返回栈的fragment
                fragmentsAddedInBackStack.add(fragmentIntended);
            } else { // 不加入到fragment放回栈 作为默认加载fragment
                // 将fragment加入存储不返回的栈的list
                fragmentsNoInBackStack.add(fragmentIntended);
            }
        }

        // 不添加到回退栈则作为默认fragment
        if (!addedToBackStack) {
            activity.setDefaultFragment(fragmentIntended);
        }
        transaction.commit();
    }

    /**
     * 隐藏所有已加入的fragment
     */
    public static void hideFragments(AppCompatActivity activity, List<Fragment> yourFragments) {

        //没有就不需要清理
        if (null == yourFragments || null == activity) {
            return;
        }
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (int i = 0; i < yourFragments.size(); i++) {
            Fragment fragment = yourFragments.get(i);
            if (fragment.getActivity() != activity) {
                Log.d("FRSCShowFragmentToActivityUtil", "=====intentToFragment: 加入的fragment 不属于此 activity======");
            }
            if (!fragment.isHidden()) {
                transaction.hide(fragment);
            }
        }
        transaction.commitAllowingStateLoss();
    }

}
