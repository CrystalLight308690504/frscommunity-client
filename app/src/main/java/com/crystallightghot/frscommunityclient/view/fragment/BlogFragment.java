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
import butterknife.Unbinder;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.activity.HomeActivity;
import com.crystallightghot.frscommunityclient.view.util.ActivityUtile;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class BlogFragment extends Fragment {

    String[] tabTitles ;

    private static final String ARG_PARAM1 = "param1";
    Unbinder bind;
    @BindView(R.id.blog_tabs)
    TabLayout blogTabs;
    @BindView(R.id.blog_more_list)
    ImageButton blogMoreList;
    @BindView(R.id.blog_viewPager)
    ViewPager blogViewPager;

    static  BlogFragment blogFragment;
    HomeActivity activity;
    private List<ViewPagerItem> fragments;

    public BlogFragment() {
        // Required empty public constructor
    }

    public static BlogFragment newInstance(String param1) {

        if (null == blogFragment){
            blogFragment = new BlogFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            blogFragment.setArguments(args);
        }
        return blogFragment;
    }

    private void init() {
        activity = (HomeActivity) getActivity();
        tabTitles = activity.getResources().getStringArray(R.array.tags_values);
        fragments = new ArrayList<>();
        int i = 0;
        while (i < tabTitles.length) {
            blogTabs.addTab(blogTabs.newTab().setText(tabTitles[i]));
            fragments.add(new ViewPagerItem(tabTitles[i],null));
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_blog, container, false);
        bind = ButterKnife.bind(this, inflate);
        init();
        ActivityUtile.showFragment(BlogFragment.newInstance("blog"),activity,false);
        return inflate;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }
}
