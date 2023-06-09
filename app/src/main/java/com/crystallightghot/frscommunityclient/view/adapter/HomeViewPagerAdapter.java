package com.crystallightghot.frscommunityclient.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.crystallightghot.frscommunityclient.view.fragment.HomeFragment;
import com.crystallightghot.frscommunityclient.view.fragment.HomeViewPagerItemFragment;
import com.crystallightghot.frscommunityclient.view.pojo.skatingtype.SkatingType;

import java.util.ArrayList;

/**
 * @author crystallightghost
 * @date 2022/1/5
 * @Version: 1.0
 * description：
 */

public class HomeViewPagerAdapter extends FragmentStateAdapter {
    ArrayList<SkatingType> skatingTypes;

    public HomeViewPagerAdapter(HomeFragment fragment, ArrayList<SkatingType> skatingTypes) {
        super(fragment);
        this.skatingTypes = skatingTypes;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new  HomeViewPagerItemFragment();
    }

    @Override
    public int getItemCount() {
        return skatingTypes.size();
    }
}
