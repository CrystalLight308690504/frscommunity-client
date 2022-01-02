package com.crystallightghot.frscommunityclient.activity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * @author crystallightghost
 * @date 2022/1/2
 * @Version: 1.0
 * description：
 */
/*
 * @Description TODO
 * @Date 2022/1/2 13:54
 * @Created by CrystalLightGhost
 */

public class HomeViewpageAdapter extends PagerAdapter {

    int i = 0;

    Context context;
    List<View> lists;

    public HomeViewpageAdapter(Context context, List<View> lists) {
        this.context = context;
        this.lists = lists;
    }


    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {//必须实现，实例化

        container.addView(lists.get(position));
        return lists.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(lists.get(position));
    }
}
