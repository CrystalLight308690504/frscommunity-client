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
import com.crystallightghot.frscommunityclient.presenter.BlogPresenter;
import com.crystallightghot.frscommunityclient.view.activity.MainActivity;
import com.crystallightghot.frscommunityclient.view.adapter.BlogViewPagerAdapter;
import com.crystallightghot.frscommunityclient.view.pojo.skatingtype.SkatingType;
import com.crystallightghot.frscommunityclient.view.util.FRSCIntentUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import java.util.ArrayList;


public class BlogFragment extends BaseFragment {

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

    BlogPresenter presenter;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    private String[] skatingTypesName;

    public BlogFragment() {
        presenter = new BlogPresenter(this);
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

    public void init(String[] skatingTypesName, ArrayList<SkatingType> skatingTypes) {
        this.skatingTypesName = skatingTypesName;
        contentViewPager.setAdapter(new BlogViewPagerAdapter(this, skatingTypes));
        new TabLayoutMediator(tbSkatingTypes, contentViewPager, (tab, position) -> tab.setText(skatingTypesName[position])
        ).attach();
    }

    private void ladingSkatingType() {
        presenter.loadingSkatingType();
    }
    public void showSuccessState() {
        llStateful.showContent();
    }
    public void showErrorState(String message) {
        llStateful.showError(message, view -> {presenter.loadingSkatingType();});
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog, container, false);
        bind = ButterKnife.bind(this, view);
        ladingSkatingType();
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
                FRSCIntentUtil.intentToSingleFragmentActivity(SkatingTypeFragment.newInstance(""));
                break;
            case R.id.btnSearch:
                FRSCIntentUtil.intentToSingleFragmentActivity(BlogSearchFragment.newInstance(""));
                break;
            default:
                break;
        }
    }
}
