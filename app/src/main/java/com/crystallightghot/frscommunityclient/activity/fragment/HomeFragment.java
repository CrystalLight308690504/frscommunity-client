package com.crystallightghot.frscommunityclient.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.activity.AllSkatingCategoryActivity;
import com.google.android.material.tabs.TabLayout;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author crystallightghost
 * @date 2022/1/2
 * @Version: 1.0
 * description：
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "调试";
    @BindView(R.id.home_search_input_box)
    TextView homeSearchInputBox;
    @BindView(R.id.imageButton)
    ImageButton imageButton;
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.blog_more_list)
    ImageButton blogMoreList;

    AppCompatActivity activity;
    Unbinder bind;
    static HomeFragment homeFragment;

    private List<TabFragment> tabFragmentList = new ArrayList<>();

    String[] s = {"tab1", "tab2", "tab3", "tab4"};


    public static HomeFragment newInstant(String str) {
        if (null == homeFragment) {
            Bundle bundle = new Bundle();
            homeFragment = new HomeFragment();
            bundle.putString("label", str);
            homeFragment.setArguments(bundle);
        }

        return homeFragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        bind = ButterKnife.bind(this, view);
        init();
        setViewPages(null);
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
    }

    private void init() {
        activity = (AppCompatActivity) getActivity();

    }

    /**
     * @param views
     */
    private void setViewPages(List<View> views) {

        viewPager.setAdapter(new MyFragment(activity.getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        tabs.setupWithViewPager(viewPager);
    }

    @OnClick(R.id.blog_more_list)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.blog_more_list:
                Intent intent = new Intent(activity, AllSkatingCategoryActivity.class);
                activity.startActivity(intent);
                break;

        }
    }
    class MyFragment extends FragmentPagerAdapter {
        public MyFragment(@NonNull @NotNull FragmentManager fm, int behavior) {
            super(fm, behavior);
            if (tabFragmentList != null) {
                tabFragmentList.clear();
                tabs.removeAllTabs();
            }

            int i = 0;
            while (i < s.length) {
                tabs.addTab(tabs.newTab().setText(s[i]));
                tabFragmentList.add(TabFragment.newInstance(s[i]));
                i++;
            }
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return tabFragmentList.get(position);
        }

        @Override
        public int getCount() {
            Log.d(TAG, "tabFragmentList 的数量: " + tabFragmentList.size());
            return tabFragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            Log.d(TAG, "viewPager  position: " + position);

            if (position < s.length) {
                return s[position];
            } else {
                return "超过";
            }
        }
    }

}
