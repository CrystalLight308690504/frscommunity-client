package com.crystallightghot.frscommunityclient.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.crystallightghot.frscommunityclient.view.fragment.HomeFragment;
import com.crystallightghot.frscommunityclient.view.fragment.HomeViewPagerItemFragment;
import com.crystallightghot.frscommunityclient.view.fragment.UserInformationViewPagerBlogItemFragment;
import com.crystallightghot.frscommunityclient.view.pojo.skatingtype.SkatingType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * @author crystallightghost
 * @date 2022/1/5
 * @Version: 1.0
 * description：
 */

public class UserInformationViewPagerAdapter extends FragmentStateAdapter {


    public UserInformationViewPagerAdapter(@NonNull @NotNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new UserInformationViewPagerBlogItemFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
