package com.crystallightghot.frscommunityclient.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.crystallightghot.frscommunityclient.view.fragment.HelpViewPagerItemFragment;
import org.jetbrains.annotations.NotNull;

/**
 * @Date 2022/1/25
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class HelpViewPagerAdapter  extends FragmentStateAdapter {
    public HelpViewPagerAdapter(@NonNull @NotNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        HelpViewPagerItemFragment fragment = new HelpViewPagerItemFragment();
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
