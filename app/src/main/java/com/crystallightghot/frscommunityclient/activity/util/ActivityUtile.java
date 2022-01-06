package com.crystallightghot.frscommunityclient.activity.util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.crystallightghot.frscommunityclient.R;

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
     * 显示fragment
     *
     * @param
     */
  public static  void addFragmentToShow(Fragment showedFragment, AppCompatActivity activity , List<Fragment> fragments) {
        if (null == showedFragment ) {
            return;
        }
        // 隐藏所有fragment
        ActivityUtile.setAllFragmentsToHiden(activity,fragments);
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 已经添加fragment
        if (showedFragment.isAdded()) {
            transaction.show(showedFragment);
        } else { // 新加入的fragment
            transaction.add(R.id.homeFragment,showedFragment);
            fragments.add(showedFragment);
        }
        transaction.commit();
    }

    /** 清楚原来fragment里现有的fragment*/
    public static void removeFragments(AppCompatActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            fragmentTransaction.remove(fragments.get(i));
        }
        fragmentTransaction.commit();
    }

    /**
     * 隐藏所有已加入的fragment
     */
    public static void setAllFragmentsToHiden(AppCompatActivity activity, List<Fragment> fragments) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            transaction.hide(fragment);
        }
        transaction.commitAllowingStateLoss();
    }




}
