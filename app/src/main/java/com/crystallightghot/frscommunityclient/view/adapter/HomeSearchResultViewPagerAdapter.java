package com.crystallightghot.frscommunityclient.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.crystallightghot.frscommunityclient.view.fragment.HomeSearchResultDefaultViewPagerItemFragment;
import org.jetbrains.annotations.NotNull;

/**
 * @author crystallightghost
 * @date 2022/1/5
 * @Version: 1.0
 * description：
 */

public class HomeSearchResultViewPagerAdapter extends FragmentStateAdapter {

    public HomeSearchResultViewPagerAdapter(@NotNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
            return new HomeSearchResultDefaultViewPagerItemFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
