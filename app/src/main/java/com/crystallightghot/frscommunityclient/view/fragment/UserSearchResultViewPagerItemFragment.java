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
import com.crystallightghot.frscommunityclient.presenter.UserSearchResultViewPagerItemFragmentPresenter;
import com.crystallightghot.frscommunityclient.view.adapter.UserSearchResultRecyclerViewAdapter;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xui.utils.ViewUtils;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserSearchResultViewPagerItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * @author
 */
public class UserSearchResultViewPagerItemFragment extends BaseFragment {

    @BindView(R.id.rv_lists)
    RecyclerView rvLists;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    String searchText;
    UserSearchResultViewPagerItemFragmentPresenter presenter;
    UserSearchResultRecyclerViewAdapter recyclerViewAdapter;
    int pagerIndex = 0;

    public UserSearchResultViewPagerItemFragment(String searchText) {
        this.searchText = searchText;
        presenter = new UserSearchResultViewPagerItemFragmentPresenter(this);
    }

    public static UserSearchResultViewPagerItemFragment newInstance(String searchText) {
        UserSearchResultViewPagerItemFragment fragment = new UserSearchResultViewPagerItemFragment(searchText);
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
        recyclerViewAdapter = new UserSearchResultRecyclerViewAdapter();
        rvLists.setAdapter(recyclerViewAdapter);
        //下拉刷新
        ViewUtils.setViewsFont(refreshLayout);
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            pagerIndex = 0;
            recyclerViewAdapter.getUsers().clear();
            presenter.loadSearchUserResult(searchText, pagerIndex);
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            refreshLayout.finishLoadMore();
            presenter.loadSearchUserResult(searchText, ++pagerIndex);
        });
        presenter.loadSearchUserResult(searchText, pagerIndex);
    }

    /**
     * 将数据加入到RecycleView中
     */
    public void addDataToRV(List<User> users, boolean hasNext) {
        if (null == users || users.size() == 0) {
            llStateful.showEmpty();
            return;
        }
        llStateful.showContent();
        if (hasNext) {
            refreshLayout.setEnableLoadMore(true);
        }else  {
            refreshLayout.setEnableLoadMore(false);
        }
        refreshLayout.finishRefresh();
        recyclerViewAdapter.getUsers().addAll(users);
        recyclerViewAdapter.notifyDataSetChanged();
    }


    private void showOffline() {
        llStateful.showOffline(v -> refreshLayout.autoRefresh());
        refreshLayout.setEnableLoadMore(false);
    }

    public void showError(String errorMessage) {
        llStateful.showError(errorMessage, view ->  presenter.loadSearchUserResult(searchText, pagerIndex));
        refreshLayout.setEnableLoadMore(false);
    }

}