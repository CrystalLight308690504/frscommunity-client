package com.crystallightghot.frscommunityclient.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.crystallightghot.frscommunityclient.view.fragment.BlogSearchResultViewPagerItemFragment;
import com.crystallightghot.frscommunityclient.view.fragment.HomeSearchResultDefaultViewPagerItemFragment;
import com.crystallightghot.frscommunityclient.view.fragment.UserSearchResultViewPagerItemFragment;
import org.jetbrains.annotations.NotNull;

/**
 * @author crystallightghost
 * @date 2022/1/5
 * @Version: 1.0
 * description：
 */

public class SearchResultViewPagerAdapter extends FragmentStateAdapter {
    String searchText;

    public SearchResultViewPagerAdapter(@NotNull Fragment fragment, String searchText) {
        super(fragment);
        this.searchText = searchText;
    }

    public SearchResultViewPagerAdapter(@NotNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new BlogSearchResultViewPagerItemFragment(searchText);
            case 1:
                return new UserSearchResultViewPagerItemFragment(searchText);
            default:
                return new BlogSearchResultViewPagerItemFragment(searchText);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
