package com.crystallightghot.frscommunityclient.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnScrollChangeListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.presenter.BlogViewPagerItemFragmentPresenter;
import com.crystallightghot.frscommunityclient.view.adapter.BlogRecyclerViewAdapter;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.crystallightghot.frscommunityclient.view.pojo.skatingtype.SkatingType;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xui.utils.ViewUtils;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import java.util.List;

/**
 * @author crystallightghost
 * @date 2022/1/2
 * @Version: 1.0
 * description：
 */
public class BlogViewPagerItemFragment extends BaseFragment {

    @BindView(R.id.rv_lists)
    RecyclerView rvLists;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;

    SkatingType skatingType;
    BlogViewPagerItemFragmentPresenter presenter;
    public BlogViewPagerItemFragment() {
    }

    public BlogViewPagerItemFragment(SkatingType skatingType) {
        presenter = new BlogViewPagerItemFragmentPresenter(this);
        this.skatingType = skatingType;
    }

    private void addRecycleViewScrolledListener() {
        /**
         * 多recycleView 滑动事件监听
         */
        rvLists.setOnScrollChangeListener(new OnScrollChangeListener() {
            int lastScrolledInstance = 0;
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int scrolledInstance) {

                // 当 lastScrolledInstance 和 scrolledInstance 都为 0 表示为滑动停止 所以保持原来的状态（）
                Intent intent = new Intent("HomeViewPagerItemScrollChangedReceiver");
                if (lastScrolledInstance < 0 && scrolledInstance <= 0) { // 如果是向下划屏幕
                    intent.putExtra("isScrollUpward", true);
                } else if (lastScrolledInstance > 0 && scrolledInstance >= 0) { // 如果是向上划屏幕
                    intent.putExtra("isScrollUpward", false);
                }
                lastScrolledInstance = scrolledInstance;
                getActivity().sendBroadcast(intent);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_page_content_item, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        addRecycleViewScrolledListener();
        presenter.loadingBlogs(skatingType);
    }

    /**
     * 将数据加入到RecycleView中
     */
    public void loadData(List<Blog> blogs) {

        //下拉刷新
        ViewUtils.setViewsFont(refreshLayout);
        refreshLayout.setOnRefreshListener(refreshLayout ->{
            presenter.loadingBlogs(skatingType);
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(refreshLayout ->{
            presenter.loadingBlogs(skatingType);
        });
        rvLists.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvLists.setAdapter(new BlogRecyclerViewAdapter(getActivity(), blogs));
        refreshLayout.resetNoMoreData();
        refreshLayout.setEnableLoadMore(true);
        llStateful.showContent();
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
    }

    private void showOffline() {
        llStateful.showOffline(v -> refreshLayout.autoRefresh());
        refreshLayout.setEnableLoadMore(false);
    }

    private void showError() {
        llStateful.showError(v -> refreshLayout.autoRefresh());
        refreshLayout.setEnableLoadMore(false);
    }

}
