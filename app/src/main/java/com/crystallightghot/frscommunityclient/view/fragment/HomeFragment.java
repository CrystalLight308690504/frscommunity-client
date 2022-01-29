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
import androidx.viewpager2.widget.ViewPager2;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.util.EventBusUtil;
import com.crystallightghot.frscommunityclient.view.activity.SingleFragmentActivity;
import com.crystallightghot.frscommunityclient.view.adapter.HomeViewPagerAdapter;
import com.crystallightghot.frscommunityclient.view.message.FragmentChangeMessage;
import com.crystallightghot.frscommunityclient.view.util.FRSCIntentUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


/**
 * @author crystallightghost
 * @date 2022/1/2
 * @Version: 1.0
 * description：
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.search_input_box)
    TextView searchInputBox;
    @BindView(R.id.imageButton)
    ImageButton imageButton;

    @BindView(R.id.tabs)
    TabLayout tbSkatingType;
    @BindView(R.id.blog_more_list)
    ImageButton blogMoreList;

    AppCompatActivity activity;
    Unbinder bind;
    static HomeFragment homeFragment;
    @BindView(R.id.contentViewPager)
    ViewPager2 contentViewPager;

    private List<HomeViewPagerItemFragment> homeViewPagerItemFragments = new ArrayList<>();

    String[] tabTitles;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String str) {

        return new HomeFragment();
    }


    private void init() {
        activity = (AppCompatActivity) getActivity();
        tabTitles = activity.getResources().getStringArray(R.array.skatingType);
    }

    /**
     * @param views
     */
    private void addDateToViewPager(List<View> views) {
        contentViewPager.setAdapter(new HomeViewPagerAdapter(this));
        new TabLayoutMediator(tbSkatingType, contentViewPager, (tab, position) -> tab.setText(tabTitles[position])
        ).attach();
    }

    @OnClick({R.id.blog_more_list, R.id.search_input_box})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.blog_more_list:
                intent = new Intent(activity, SingleFragmentActivity.class);
                startActivity(intent);
                FragmentChangeMessage fragmentChangeMessage = new FragmentChangeMessage();
                fragmentChangeMessage.setCode(SingleFragmentActivity.MESSAGE_COD);
                fragmentChangeMessage.setDefaultFragment(SkatingTypeFragment.newInstance("HomeSkatingTypeFragment"));
                EventBusUtil.sendStickMessage(fragmentChangeMessage);
                break;
            case R.id.search_input_box:
                FRSCIntentUtil.intentToSingleFragmentActivity(HomeSearchFragment.newInstance("login"));
                break;
            default:
                break;
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        bind = ButterKnife.bind(this, view);
        init();
        addDateToViewPager(null);
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
