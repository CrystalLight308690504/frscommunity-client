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
import com.crystallightghot.frscommunityclient.presenter.ManageBlogFragmentPresenter;
import com.crystallightghot.frscommunityclient.view.adapter.ManageBlogRecycleViewAdapter;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xui.utils.ViewUtils;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManageBlogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageBlogFragment extends Fragment {

    @BindView(R.id.rvLists)
    RecyclerView rvLists;

    ManageBlogFragmentPresenter presenter;
    ManageBlogRecycleViewAdapter adapter = new ManageBlogRecycleViewAdapter();
    int pagerIndex = 0;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    public ManageBlogFragment() {
        presenter = new ManageBlogFragmentPresenter(this);
    }


    // TODO: Rename and change types and number of parameters
    public static ManageBlogFragment newInstance() {
        ManageBlogFragment fragment = new ManageBlogFragment();
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
        rvLists.setAdapter(adapter);
        tvTitle.setText("博客管理");
        rvLists.setAdapter(adapter);
        //下拉刷新
        ViewUtils.setViewsFont(refreshLayout);
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            pagerIndex = 0;
            adapter.getBlogs().clear();
            presenter.loadBlogs(pagerIndex);
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            refreshLayout.finishLoadMore();
            presenter.loadBlogs(++pagerIndex);
        });

        presenter.loadBlogs(pagerIndex);
    }

    public void showMoreBlogs(List<Blog> blogs, boolean hasNext) {
        refreshLayout.finishRefresh();
        if (null == blogs || blogs.size() == 0) {
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
        adapter.getBlogs().addAll(blogs);
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btnBack)
    public void onClick() {
        getActivity().onBackPressed();
    }
}