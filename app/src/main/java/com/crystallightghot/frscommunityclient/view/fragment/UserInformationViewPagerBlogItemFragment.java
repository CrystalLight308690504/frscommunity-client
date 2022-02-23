package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.presenter.UserInformationBlogViewPagerItemAdapterPresenter;
import com.crystallightghot.frscommunityclient.view.adapter.BlogRecyclerViewAdapter;
import com.crystallightghot.frscommunityclient.view.adapter.MyBlogCategoryRecycleViewAdapter;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCategory;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xui.utils.ViewUtils;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author crystallightghost
 * @date 2022/1/2
 * @Version: 1.0
 * description：
 */
public class UserInformationViewPagerBlogItemFragment extends Fragment {

    @BindView(R.id.rv_lists)
    RecyclerView rvLists;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    private User user;
    UserInformationBlogViewPagerItemAdapterPresenter presenter;

    public UserInformationViewPagerBlogItemFragment(User user) {
        this.user = user;
        presenter = new UserInformationBlogViewPagerItemAdapterPresenter(this);
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
        rvLists.setLayoutManager(new LinearLayoutManager(getActivity()));
        //下拉刷新
        ViewUtils.setViewsFont(refreshLayout);
        refreshLayout.setOnRefreshListener(refreshLayout ->{
            presenter.loadingCategory(user.getUserId());
        });
        presenter.loadingCategory(user.getUserId());
    }

    private void showOffline() {
        llStateful.showOffline(v -> refreshLayout.autoRefresh());
        refreshLayout.setEnableLoadMore(false);
    }

    public void loadingData(List<BlogCategory> blogCategories) {
        MyBlogCategoryRecycleViewAdapter adapter = new MyBlogCategoryRecycleViewAdapter(blogCategories);
        rvLists.setAdapter(adapter);
        refreshLayout.resetNoMoreData();
        refreshLayout.setEnableLoadMore(true);
        llStateful.showContent();
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
    }

    public void showError() {
        llStateful.showError(v -> refreshLayout.autoRefresh());
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.finishRefresh();
    }

}
