package com.crystallightghot.frscommunityclient.activity.fragment;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.crystallightghot.frscommunityclient.R;

import java.util.LinkedList;
import java.util.List;

/**
 * @author crystallightghost
 * @date 2022/1/2
 * @Version: 1.0
 * description：
 */
public class HomeFragment extends Fragment {

    Activity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_frag, container, false);
        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        initPagers();
    }

    private void init() {
        activity = getActivity();
    }


    List<View> lists = new LinkedList<>();

    private void initPagers() {
        int i = 0;
        int j = 0;
        while (j++ < 8) {
            TextView textView = new TextView(activity);
            textView.setHeight(60);
            textView.setWidth(100);
            textView.setText("dfsad" + i++);
            Drawable a = activity.getDrawable(R.color.applicationMainTheme);
            textView.setBackground(a);
            lists.add(textView);
        }

    }
}
