package com.crystallightghot.frscommunityclient.view.fragment;

import android.content.Intent;
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
import butterknife.OnClick;
import butterknife.Unbinder;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.activity.AllSkatingCategoryActivity;
import com.crystallightghot.frscommunityclient.view.activity.SearchActivity;
import com.crystallightghot.frscommunityclient.view.adapter.HomeViewPagerAdapter;
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

    @BindView(R.id.search_input_box)
    TextView searchInputBox;
    @BindView(R.id.imageButton)
    ImageButton imageButton;
    @BindView(R.id.type_container_pager)
    ViewPager typeContentContainer;
    @BindView(R.id.tabs)
    TabLayout tl_types;
    @BindView(R.id.blog_more_list)
    ImageButton blogMoreList;

    AppCompatActivity activity;
    Unbinder bind;


    static HomeFragment homeFragment;

    private List<ViewPagerItem> viewPagerItem = new ArrayList<>();

    String[] tabTitles;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String str) {
        if (null == homeFragment) {
            Bundle bundle = new Bundle();
            homeFragment = new HomeFragment();
            bundle.putString("label", str);
            homeFragment.setArguments(bundle);
        }

        return homeFragment;
    }



    private void init() {
        activity = (AppCompatActivity) getActivity();
        tabTitles = activity.getResources().getStringArray(R.array.tags_values);
    }

    /**
     * @param views
     */
    private void setViewPages(List<View> views) {

        // 添加测试数据
        int i = 0;
        while (i < tabTitles.length) {
            viewPagerItem.add(new ViewPagerItem(tabTitles[i], null));
            i++;
        }
        typeContentContainer.setAdapter(new HomeViewPagerAdapter(activity.getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, viewPagerItem, tabTitles));
        tl_types.setupWithViewPager(typeContentContainer);
    }

    @OnClick({R.id.blog_more_list,R.id.search_input_box})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.blog_more_list:
                intent = new Intent(activity, AllSkatingCategoryActivity.class);
                activity.startActivity(intent);
                break;
                case R.id.search_input_box:
                intent = new Intent(activity, SearchActivity.class);
                activity.startActivity(intent);
                break;
        }
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
}
