package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;
import com.crystallightghot.frscommunityclient.view.adapter.HomeViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;


public class BlogSearchResultsFragment extends Fragment {

    static BlogSearchResultsFragment allSearchResultsFragment;
    @BindView(R.id.top_bar_back)
    ImageButton topBarBack;
    @BindView(R.id.input_box)
    TextInputEditText inputBox;
    @BindView(R.id.btn_search)
    TextView btnSearch;
    @BindView(R.id.searchResultType)
    TabLayout searchResultType;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    BaseFragmentActivity activity;

    public BlogSearchResultsFragment() {
        // Required empty public constructor
    }

    public static BlogSearchResultsFragment newInstance(String param1) {

        if (null == allSearchResultsFragment) {
            allSearchResultsFragment = new BlogSearchResultsFragment();
            Bundle args = new Bundle();
            allSearchResultsFragment.setArguments(args);
        }
        return allSearchResultsFragment;
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
        addData();
    }

    /** 数据翻倍增加BUG
     * 每次编译器应用改变后 会重新调用 会重新createView()方法  而对象this 不会重新创建 所以pagerFragments 的内容成倍数增加
     *
     * */
    public void addData() {
        String[] tabTitles = activity.getResources().getStringArray(R.array.searchResultType);
        // 添加测试数据
        List<HomeViewPagerItemFragment> pagerFragments = new ArrayList<>();

        viewPager.setAdapter(new HomeViewPagerAdapter(activity.getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, pagerFragments, tabTitles));
        searchResultType.setupWithViewPager(viewPager);
    }

}