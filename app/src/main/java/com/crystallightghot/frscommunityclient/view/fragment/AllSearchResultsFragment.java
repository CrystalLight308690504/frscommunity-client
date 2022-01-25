package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;
import com.crystallightghot.frscommunityclient.view.adapter.HomeViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textfield.TextInputEditText;


public class AllSearchResultsFragment extends Fragment {

    @BindView(R.id.top_bar_back)
    ImageButton topBarBack;
    @BindView(R.id.input_box)
    TextInputEditText inputBox;
    @BindView(R.id.btn_search)
    TextView btnSearch;
    @BindView(R.id.searchResultType)
    TabLayout searchResultType;

    BaseFragmentActivity activity;
    @BindView(R.id.viewPager)
    ViewPager2 viewPager;

    public AllSearchResultsFragment() {
        // Required empty public constructor
    }

    public static AllSearchResultsFragment newInstance(String param1) {

        return  new AllSearchResultsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_resultes, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        activity = (BaseFragmentActivity) getActivity();
        topBarBack.setOnClickListener(view -> activity.onBackPressed());

        // CS
        initView();
    }

    public void initView() {
        String[] tabTitles = activity.getResources().getStringArray(R.array.searchResultType);
        // 添加测试数据
        viewPager.setAdapter(new HomeViewPagerAdapter(this));
        new TabLayoutMediator(searchResultType, viewPager, (tab, position) -> tab.setText(tabTitles[position])
        ).attach();
    }

}