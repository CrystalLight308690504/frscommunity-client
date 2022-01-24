package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.activity.MainActivity;
import com.crystallightghot.frscommunityclient.view.util.FRSCIntentUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
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
    @BindView(R.id.blog_viewPager)
    ViewPager blogViewPager;

    static HelpNeededFragment blogFragment;
    MainActivity activity;
    @BindView(R.id.btnSearch)
    ImageButton btnSearch;
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

    private void init() {
        activity = (MainActivity) getActivity();
        tabTitles = activity.getResources().getStringArray(R.array.tags_values);
        fragments = new ArrayList<>();
        int i = 0;
        while (i < tabTitles.length) {
            blogTabs.addTab(blogTabs.newTab().setText(tabTitles[i]));
            fragments.add(new HomeViewPagerItemFragment(tabTitles[i], null));
            i++;
        }
        setViewPages(null);
    }


    private void setViewPages(List<View> views) {

        blogViewPager.setAdapter(new FragmentPagerAdapter(activity.getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabTitles[position];
            }
        });
        blogTabs.setupWithViewPager(blogViewPager);
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
                FRSCIntentUtil.IntentToSingleFragmentActivity(HomeSkatingTypeFragment.newInstance(""));
                break;
            case R.id.btnSearch:
                FRSCIntentUtil.IntentToSingleFragmentActivity(BlogSearchFragment.newInstance(""));
                break;
            default:
                break;
        }
    }
}