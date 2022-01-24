package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.activity.MainActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpNeededFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpNeededFragment extends Fragment {

    String[] tabTitles;
    private static final String ARG_PARAM1 = "param1";
    Unbinder bind;
    @BindView(R.id.blog_tabs)
    TabLayout blogTabs;
    @BindView(R.id.blog_more_list)
    ImageButton blogMoreList;

    static HelpNeededFragment blogFragment;
    MainActivity activity;
    @BindView(R.id.btnSearch)
    ImageButton btnSearch;
    @BindView(R.id.blog_viewPager)
    ViewPager2 viewPager;
    private List<HomeViewPagerItemFragment> fragments;

    public HelpNeededFragment() {
        // Required empty public constructor
    }

    public static HelpNeededFragment newInstance(String param1) {

        if (null == blogFragment) {
            blogFragment = new HelpNeededFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            blogFragment.setArguments(args);
        }
        return blogFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help_needed, container, false);
        bind = ButterKnife.bind(this, view);
        return view;
    }
}
