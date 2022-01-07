package com.crystallightghot.frscommunityclient.view.util;

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
    public void removeAllFragments(AppCompatActivity activity, List<Fragment> fragmentsNeededHidden) {

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


    public static void showFragment(Fragment showedFragment, AppCompatActivity activity, List<Fragment> fragmentsNeededHidden, int viewId) {
        showFragment(showedFragment, activity, fragmentsNeededHidden, viewId, false);
    }

    /**
     * 添加fragment
     *
     * @param showedFragment        要添加的fragment
     * @param activity              使用此方法的activity
     * @param fragmentsNeededHidden 为null 时表示不需要隐藏
     * @param viewId                把fragment添加到的View的id
     * @param isAddedToBackStack    是否将显示的fragment添加到返回栈
     */
    public static void showFragment(Fragment showedFragment, AppCompatActivity activity, List<Fragment> fragmentsNeededHidden, int viewId, boolean isAddedToBackStack) {

        // 隐藏所有fragment
        if (null != fragmentsNeededHidden || fragmentsNeededHidden.size() != 0) {
            setFragmentsHidden(activity, fragmentsNeededHidden);
        }

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 已经添加fragment 显示
        if (showedFragment.isAdded()) {
            transaction.show(showedFragment);
        } else {
            // 添加fragment
            transaction.add(viewId, showedFragment, showedFragment.getClass().getSimpleName());
            fragmentsNeededHidden.add(showedFragment);
            // 新加入的fragment 添加到回退栈
            if (isAddedToBackStack) {
                transaction.addToBackStack(showedFragment.getClass().getSimpleName());
            }
        }
        transaction.commit();
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
