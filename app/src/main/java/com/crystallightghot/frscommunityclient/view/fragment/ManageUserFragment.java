package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.presenter.ManageUserFragmentPresenter;
import com.crystallightghot.frscommunityclient.view.adapter.ManageUserRecycleViewAdapter;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xui.utils.ViewUtils;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManageUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageUserFragment extends BaseFragment {
    ManageUserFragmentPresenter presenter;
    @BindView(R.id.rvLists)
    RecyclerView rvLists;
    ManageUserRecycleViewAdapter adapter = new ManageUserRecycleViewAdapter();
    int pagerIndex;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    public ManageUserFragment() {
        presenter = new ManageUserFragmentPresenter(this);
    }


    // TODO: Rename and change types and number of parameters
    public static ManageUserFragment newInstance() {
        ManageUserFragment fragment = new ManageUserFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        tvTitle.setText("用户管理");
        rvLists.setAdapter(adapter);
        //下拉刷新
        ViewUtils.setViewsFont(refreshLayout);
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            pagerIndex = 0;
            adapter.getUsers().clear();
            presenter.loadUsers(pagerIndex);
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            refreshLayout.finishLoadMore();
            presenter.loadUsers(++pagerIndex);
        });
        presenter.loadUsers(pagerIndex);

    }

    public void showMoreUsers(List<User> users, boolean hasNext) {
        refreshLayout.finishRefresh();
        if (null == users || users.size() == 0) {
            llStateful.showEmpty();
            refreshLayout.setEnableLoadMore(false);
            return;
        } else {
            llStateful.showContent();
        }
        if (hasNext) {
            refreshLayout.setEnableLoadMore(true);
        } else {
            refreshLayout.setEnableLoadMore(false);
        }
        adapter.getUsers().addAll(users);
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btnBack)
    public void onClick() {
        getActivity().onBackPressed();
    }
}