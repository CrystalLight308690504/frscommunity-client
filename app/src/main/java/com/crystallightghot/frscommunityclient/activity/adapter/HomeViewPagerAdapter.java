package com.crystallightghot.frscommunityclient.activity.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.crystallightghot.frscommunityclient.activity.fragment.HomeViewPagerItemFragment;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author crystallightghost
 * @date 2022/1/5
 * @Version: 1.0
 * description：
 */
/*
 * @Description TODO
 * @Date 2022/1/5 10:22
 * @Created by CrystalLightGhost
 */
public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    List<HomeViewPagerItemFragment> fragments;
    String[] titles;
    public HomeViewPagerAdapter(@NonNull @NotNull FragmentManager fm, int behavior, List<HomeViewPagerItemFragment> fragments, String[] titles) {
        super(fm, behavior);
        this.titles = titles;
        this.fragments = fragments;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return  titles[position];
    }
}
