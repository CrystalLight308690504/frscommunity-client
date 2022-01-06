package com.crystallightghot.frscommunityclient.view.util;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

/**
 * @author crystallightghost
 * @date 2022/1/6
 * @Version: 1.0
 * description：
 */
/*
 * @Description TODO
 * @Date 2022/1/6 11:20
 * @Created by CrystalLightGhost
 */
public class ActivityUtile {


    /**
     * 清楚原来fragment里现有的fragment
     */
    public  void removeAllFragments(AppCompatActivity activity, List<Fragment> fragmentsNeededHidden) {

        if (null == fragmentsNeededHidden) {
            return;
        }
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragmentsNeededHidden.size(); i++) {
            fragmentTransaction.remove(fragmentsNeededHidden.get(i));
        }
        fragmentTransaction.commit();
    }

    /**
     * 隐藏所有已加入的fragment
     */
    public static void hideFragment(AppCompatActivity activity, Fragment fragment) {

        if (null == fragment) { //没有就不需要清理
            return;
        }
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(fragment);
        transaction.commitAllowingStateLoss();
    }


    /**
     * 显示fragment
     *
     * @param
     */

    public static void showFragment(Fragment showedFragment, AppCompatActivity activity, List<Fragment> fragmentsNeededHidden, int viewId) {

        // 隐藏所有fragment
        if (null != fragmentsNeededHidden || fragmentsNeededHidden.size() == 0) {
            setFragmentsHidden(activity, fragmentsNeededHidden);
        }

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 已经添加fragment 显示
        if (showedFragment.isAdded()) {
            transaction.show(showedFragment);
        } else { // 新加入的fragment 添加到回退栈
            transaction.add(viewId, showedFragment,showedFragment.getClass().getSimpleName());
            // 加入到fragments
            fragmentsNeededHidden.add(showedFragment);
            transaction.addToBackStack(null);
        }
        transaction.commit();
        List<Fragment> fragments = fragmentManager.getFragments();

        Log.d("栈测试", "============================ " );
        Log.d("栈测试", "fragment的数量: " + fragments.size());
        for (int i = 0; i < fragments.size(); i++) {
            Log.d("栈测试", "栈的内容1 : " + fragments.get(i).toString());
        }
        Log.d("栈测试", "============================ " );

    }

    /**
     * 隐藏所有已加入的fragment
     */
    public static void setFragmentsHidden(AppCompatActivity activity, List<Fragment> yourFragments) {

        if (null == yourFragments) { //没有就不需要清理
            return;
        }
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (int i = 0; i < yourFragments.size(); i++) {
            Fragment fragment = yourFragments.get(i);
            transaction.hide(fragment);
        }
        transaction.commitAllowingStateLoss();
    }

}
