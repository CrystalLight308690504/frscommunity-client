package com.crystallightghot.frscommunityclient.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.crystallightghot.frscommunityclient.view.fragment.HomeViewPagerItemFragment;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author crystallightghost
 * @date 2022/1/5
 * @Version: 1.0
 * description：
 */

public class HomeViewPagerAdapter extends FragmentStateAdapter {

    List<HomeViewPagerItemFragment> fragments;
    String[] titles;

    public HomeViewPagerAdapter(@NonNull @NotNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        return new  HomeViewPagerItemFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
