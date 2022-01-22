package com.crystallightghot.frscommunityclient.view.util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;

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
public class FRSCActivityUtile {

    /**
     * 删除加入到activity的在FragmentManager的fragment
     */
    public static void removeAllFragments(AppCompatActivity activity, List<Fragment> fragmentsNeededHidden) {

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

    public static void showFragment(Fragment showedFragment, BaseFragmentActivity activity) {
        showFragment(showedFragment, activity,  true);
    }

    /**
     * 添加fragment
     *
     * @param showedFragment        要添加的fragment
     * @param activity              使用此方法的activity
     * @param addedToBackStack    是否将显示的fragment添加到返回栈
     *                              一般只把activity默认的加载的fragment(也就是第一个fragment)设为false 不加入退回栈中
     */
    public static void showFragment(Fragment showedFragment, BaseFragmentActivity activity, boolean addedToBackStack) {

        if(null == showedFragment){
            return;
        }
        // 隐藏前面显示的的fragment
        // 将不添加到返回栈的fragment隐藏
        List<Fragment> fragmentsNoInBackStack = activity.getFragmentsNoInBackStack();
        if (null != fragmentsNoInBackStack && fragmentsNoInBackStack.size() != 0) {
            setFragmentsHidden(activity, fragmentsNoInBackStack);
        }
        // 将添加到返回栈的fragment隐藏
        List<Fragment> fragmentsAddedInBackStack = activity.getFragmentsAddedInBackStack();
        if (null != fragmentsAddedInBackStack && fragmentsAddedInBackStack.size() != 0) {
            setFragmentsHidden(activity, fragmentsAddedInBackStack);
        }

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 已经添加fragment 直接显示出来
        if (showedFragment.isAdded()) {
            transaction.show(showedFragment);
        } else {// 新添加fragment
            // 替换fragment的控件的ID
            int viewId = activity.getFragmentContainerId();
            transaction.add(viewId, showedFragment, showedFragment.getClass().getSimpleName());
            // 如果新fragment添加到回退栈
            if (addedToBackStack) {
                transaction.addToBackStack(showedFragment.getClass().getSimpleName());
                // 记录加入到返回栈的fragment
                fragmentsAddedInBackStack.add(showedFragment);
            }else { // 不加入到fragment放回栈 作为默认加载fragment
                // 将fragment加入存储不返回的栈的list
                fragmentsNoInBackStack.add(showedFragment);
            }
        }

        // 不添加到回退栈则作为默认fragment
        if (!addedToBackStack){
            activity.setDefaultFragment(showedFragment);
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
            if (!fragment.isHidden()){
                transaction.hide(fragment);
            }
        }
        transaction.commitAllowingStateLoss();
    }

}
