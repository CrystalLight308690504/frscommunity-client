package com.crystallightghot.frscommunityclient.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.crystallightghot.frscommunityclient.view.fragment.BlogFragment;
import com.crystallightghot.frscommunityclient.view.fragment.BlogViewPagerItemFragment;
import com.crystallightghot.frscommunityclient.view.pojo.skatingtype.SkatingType;

import java.util.ArrayList;

/**
 * @Date 2022/2/11
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class BlogViewPagerAdapter extends FragmentStateAdapter {
    ArrayList<SkatingType> skatingTypes;

    public BlogViewPagerAdapter(BlogFragment blogFragment, ArrayList<SkatingType> skatingTypes) {
        super(blogFragment);
        this.skatingTypes = skatingTypes;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new BlogViewPagerItemFragment(skatingTypes.get(position));
    }

    @Override
    public int getItemCount() {
        return skatingTypes.size();
    }
}
