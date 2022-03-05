package com.crystallightghot.frscommunityclient.view.message;

import androidx.fragment.app.Fragment;
import com.crystallightghot.frscommunityclient.view.fragment.BlogViewPagerItemFragment;
import lombok.Data;

/**
 * @Date 2022/3/3
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
@Data
public class ViewPagerHeightUpdateMessage {
    private Fragment fragment;

    public ViewPagerHeightUpdateMessage(Fragment fragment) {

        this.fragment = fragment;
    }
}
