package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
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
    @BindView(R.id.icBack)
    TextView icBack;
    private String[] skatingTypesName;
    BlogViewPagerAdapter blogViewPagerAdapter;
    ArrayList<Fragment> fragments = new ArrayList<>();

    public BlogFragment() {
        presenter = new BlogPresenter(this);
    }

    public static BlogFragment newInstance(String param1) {

      /*  if (null == blogFragment ) {
            blogFragment = new BlogFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            blogFragment.setArguments(args);
        }*/
        return new BlogFragment();
    }

    public void init(String[] skatingTypesName, ArrayList<SkatingType> skatingTypes) {
        this.skatingTypesName = skatingTypesName;

        for (int i = 0; i < skatingTypes.size(); i++) {
            BlogViewPagerItemFragment itemFragment = new BlogViewPagerItemFragment(skatingTypes.get(i));
            fragments.add(itemFragment);
        }

        blogViewPagerAdapter = new BlogViewPagerAdapter(this, fragments);
        contentViewPager.setAdapter(blogViewPagerAdapter);
        new TabLayoutMediator(tbSkatingTypes, contentViewPager, (tab, position) -> {
            tab.setText(skatingTypesName[position]);
        }
        ).attach();
        contentViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateHeightP( position);
            }
        });
    }

    /**
     * 这段代码作用是让viewpaper2自适应高度，使得scrollview能在高度不一的fragment滑动，解决fragment出现裁切或空白的问题
     *
     * @param position
     */
    private void updateHeightP(int position) {
        if (fragments.size() > position) {
            Fragment fragment = fragments.get(position);
            if (fragment.getView() != null) {
                int viewWidth = View.MeasureSpec.makeMeasureSpec(fragment.getView().getWidth(), View.MeasureSpec.EXACTLY);
                int viewHeight = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                fragment.getView().measure(viewWidth, viewHeight);
                if (contentViewPager.getLayoutParams().height != fragment.getView().getMeasuredHeight()) {
                    //必须要用对象去接收，然后修改该对象再采用该对象，否则无法生效...
                    Log.e("TAG", "updatePagerHeightForChild: " + contentViewPager.getLayoutParams().height);
                    ViewGroup.LayoutParams layoutParams = contentViewPager.getLayoutParams();
                    layoutParams.height = fragment.getView().getMeasuredHeight();
                    Log.e("TAG", "updatePagerHeightForChild: " + contentViewPager.getLayoutParams().height);
                    contentViewPager.setLayoutParams(layoutParams);
                }
            }
        }
    }

    public void updateHeight(Fragment fragment) {
        if (fragment.getView() != null) {
            int viewWidth = View.MeasureSpec.makeMeasureSpec(fragment.getView().getWidth(), View.MeasureSpec.EXACTLY);
            int viewHeight = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            fragment.getView().measure(viewWidth, viewHeight);
            if (contentViewPager.getLayoutParams().height != fragment.getView().getMeasuredHeight()) {
                //必须要用对象去接收，然后修改该对象再采用该对象，否则无法生效...
                Log.e("TAG", "updatePagerHeightForChild: " + contentViewPager.getLayoutParams().height);
                ViewGroup.LayoutParams layoutParams = contentViewPager.getLayoutParams();
                layoutParams.height = fragment.getView().getMeasuredHeight();
                Log.e("TAG", "updatePagerHeightForChild: " + contentViewPager.getLayoutParams().height);
                contentViewPager.setLayoutParams(layoutParams);
            }
        }
    }

    private void ladingSkatingType() {
        presenter.loadingSkatingType();
    }

    public void showSuccessState() {
        llStateful.showContent();
    }

    public void showErrorState(String message) {
        llStateful.showError(message, view -> {
            presenter.loadingSkatingType();
        });
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

    @OnClick({R.id.blog_more_list, R.id.btnSearch, R.id.icBack})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.blog_more_list:
                FRSCIntentUtil.intentToSingleFragmentActivity(SkatingTypeFragment.newInstance(""));
                break;
            case R.id.btnSearch:
            case R.id.icBack:
                FRSCIntentUtil.intentToSingleFragmentActivity(BlogSearchFragment.newInstance(""));
                break;
            default:
                break;
        }
    }

}
