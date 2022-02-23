package com.crystallightghot.frscommunityclient.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.crystallightghot.frscommunityclient.view.fragment.UserInformationViewPagerBlogItemFragment;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import org.jetbrains.annotations.NotNull;

/**
 * @author crystallightghost
 * @date 2022/1/5
 * @Version: 1.0
 * description：
 */

public class UserInformationBlogViewPagerItemAdapter extends FragmentStateAdapter {

    User user;
    public UserInformationBlogViewPagerItemAdapter(@NonNull @NotNull Fragment fragment, User user) {
        super(fragment);
        this.user = user;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new UserInformationViewPagerBlogItemFragment(user);
            default:
                return new UserInformationViewPagerBlogItemFragment(user);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
