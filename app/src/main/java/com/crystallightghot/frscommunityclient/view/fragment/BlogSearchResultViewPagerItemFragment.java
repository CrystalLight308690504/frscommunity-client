package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.presenter.BlogSearchResultViewPagerItemFragmentPresenter;
import com.crystallightghot.frscommunityclient.view.adapter.BlogSearchResultRecyclerViewAdapter;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xui.utils.ViewUtils;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlogSearchResultViewPagerItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * @author
 */
public class BlogSearchResultViewPagerItemFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";

    @BindView(R.id.rv_lists)
    RecyclerView rvLists;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    String searchText;
    BlogSearchResultViewPagerItemFragmentPresenter presenter;
    BlogSearchResultRecyclerViewAdapter recyclerViewAdapter;
    int pagerIndex = 0;

    public BlogSearchResultViewPagerItemFragment(String searchText) {
        this.searchText = searchText;
        presenter = new BlogSearchResultViewPagerItemFragmentPresenter(this);
    }

    public static BlogSearchResultViewPagerItemFragment newInstance(String searchText) {
        BlogSearchResultViewPagerItemFragment fragment = new BlogSearchResultViewPagerItemFragment(searchText);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result_view_pager, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        recyclerViewAdapter = new BlogSearchResultRecyclerViewAdapter();
        rvLists.setAdapter(recyclerViewAdapter);
        //下拉刷新
        ViewUtils.setViewsFont(refreshLayout);
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            pagerIndex = 0;
            recyclerViewAdapter.getBlogs().clear();
            presenter.loadSearchBlogResult(searchText, pagerIndex);
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            refreshLayout.finishLoadMore();
            presenter.loadSearchBlogResult(searchText, ++pagerIndex);
        });
        presenter.loadSearchBlogResult(searchText, pagerIndex);
    }

    /**
     * 将数据加入到RecycleView中
     */
    public void addDataToRV(List<Blog> blogs, boolean hasNext) {
        refreshLayout.finishRefresh();
        if (null == blogs || blogs.size() == 0) {
            llStateful.showEmpty();
            refreshLayout.setEnableLoadMore(false);
            return;
        }else {
            llStateful.showContent();
        }
        if (hasNext) {
            refreshLayout.setEnableLoadMore(true);
        }else  {
            refreshLayout.setEnableLoadMore(false);
        }
        recyclerViewAdapter.getBlogs().addAll(blogs);
        recyclerViewAdapter.notifyDataSetChanged();
    }


    private void showOffline() {
        llStateful.showOffline(v -> refreshLayout.autoRefresh());
        refreshLayout.setEnableLoadMore(false);
    }

    public void showError(String errorMessage) {
        llStateful.showError(errorMessage, view ->  presenter.loadSearchBlogResult(searchText, pagerIndex));
        refreshLayout.setEnableLoadMore(false);
    }

}