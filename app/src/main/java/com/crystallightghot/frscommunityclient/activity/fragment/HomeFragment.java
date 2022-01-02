package com.crystallightghot.frscommunityclient.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.crystallightghot.frscommunityclient.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author crystallightghost
 * @date 2022/1/2
 * @Version: 1.0
 * description：
 */
public class HomeFragment extends Fragment {

    AppCompatActivity activity;
    @BindView(R.id.home_top_bar_background)
    TextView homeTopBarBackground;
    @BindView(R.id.home_search_input_box)
    TextView homeSearchInputBox;
    @BindView(R.id.imageButton)
    ImageButton imageButton;
    @BindView(R.id.pager)
    ViewPager viewPager;

    Unbinder bind;
    @BindView(R.id.tabs)
    TabLayout tabs;

    String[] s = {"tab1", "tab2", "tab3", "tab4", "tab4", "tab4", "tab4", "tab4", "tab4", "tab4", "tab4"};
    private List<TabFragment> tabFragmentList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_frag, container, false);
        bind = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        setViewPages(null);
        tabs.setupWithViewPager(viewPager);
    }

    private void init() {
        activity = (AppCompatActivity) getActivity();
        int i = 0;
        while ( i < s.length){
            tabs.addTab(tabs.newTab().setText(s[i]));
            tabFragmentList.add(TabFragment.newInstance(s[i]));
            i++;
        }
    }

    /**
     * @param views
     */
    private void setViewPages(List<View> views) {

        viewPager.setAdapter(new FragmentPagerAdapter(activity.getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return tabFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return tabFragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return s[position];
            }
        });
        tabs.setupWithViewPager(viewPager);
    }
}
