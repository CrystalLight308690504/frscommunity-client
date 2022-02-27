package com.crystallightghot.frscommunityclient.view.fragment;

import android.app.Activity;
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
import com.crystallightghot.frscommunityclient.presenter.MyFanFragmentPresenter;
import com.crystallightghot.frscommunityclient.presenter.MyUserFollowedFragmentPresenter;
import com.crystallightghot.frscommunityclient.view.adapter.UserFollowedRecycleViewAdapter;
import com.crystallightghot.frscommunityclient.view.pojo.system.UserFollowerEntity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xui.utils.ViewUtils;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFanFragment extends Fragment {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rvUsersFollowed)
    RecyclerView rvUsersFollowed;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Activity activity;
    MyFanFragmentPresenter presenter;
    int pageIndex = 0;
    UserFollowedRecycleViewAdapter adapter = new UserFollowedRecycleViewAdapter();

    public MyFanFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static MyFanFragment newInstance() {
        MyFanFragment fragment = new MyFanFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_user_followed, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }
    private void init() {
        presenter = new MyFanFragmentPresenter(this);
        activity = getActivity();
        rvUsersFollowed.setAdapter(adapter);
        presenter.loadUsersFan(pageIndex);
        //下拉刷新
        ViewUtils.setViewsFont(refreshLayout);
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            pageIndex = 0;
            adapter.getUsersFollowers().clear();
            presenter.loadUsersFan(pageIndex);
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            presenter.loadUsersFan(++pageIndex);
        });
    }

    public void loadMoreData(List<UserFollowerEntity> userFollowerEntities, boolean hasNext) {
        adapter.getUsersFollowers().addAll(userFollowerEntities);
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();

        if (adapter.getUsersFollowers().size() == 0) {
            llStateful.showEmpty();
            return;
        }else {
            llStateful.showContent();
            refreshLayout.resetNoMoreData();
        }
        if (hasNext) {
            refreshLayout.setEnableLoadMore(true);
        } else {
            refreshLayout.setEnableLoadMore(false);
        }
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btnBack)
    public void onClick() {
        Objects.requireNonNull(getActivity()).onBackPressed();
    }
}