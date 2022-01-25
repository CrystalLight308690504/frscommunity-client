package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.viewpager2.widget.ViewPager2;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.activity.MainActivity;
import com.crystallightghot.frscommunityclient.view.adapter.HomeViewPagerAdapter;
import com.crystallightghot.frscommunityclient.view.util.FRSCIntentUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;


public class BlogFragment extends BaseFragment {

    String[] tabTitles;
    private static final String ARG_PARAM1 = "param1";
    Unbinder bind;
    @BindView(R.id.tbSkatingTypes)
    TabLayout tbSkatingTypes;
    @BindView(R.id.blog_more_list)
    ImageButton blogMoreList;

    static BlogFragment blogFragment;
    MainActivity activity;
    @BindView(R.id.btnSearch)
    ImageButton btnSearch;
    @BindView(R.id.contentViewPager)
    ViewPager2 contentViewPager;
    private List<HomeViewPagerItemFragment> fragments;

    public BlogFragment() {
        // Required empty public constructor
    }

    public static BlogFragment newInstance(String param1) {

        if (null == blogFragment) {
            blogFragment = new BlogFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            blogFragment.setArguments(args);
        }
        return blogFragment;
    }

    private void init() {
        tabTitles = getResources().getStringArray(R.array.skattingType);
        setViewPages(null);
    }


    private void setViewPages(List<View> views) {
        contentViewPager.setAdapter(new HomeViewPagerAdapter(this));
        new TabLayoutMediator(tbSkatingTypes, contentViewPager, (tab, position) -> tab.setText(tabTitles[position])
        ).attach();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blog, container, false);
        bind = ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }


    @OnClick({R.id.blog_more_list, R.id.btnSearch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.blog_more_list:
                FRSCIntentUtil.IntentToSingleFragmentActivity(SkatingTypeFragment.newInstance(""));
                break;
            case R.id.btnSearch:
                FRSCIntentUtil.IntentToSingleFragmentActivity(BlogSearchFragment.newInstance(""));
                break;
            default:
                break;
        }
    }
}
