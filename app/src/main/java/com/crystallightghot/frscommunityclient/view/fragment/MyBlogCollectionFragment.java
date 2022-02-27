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
import com.crystallightghot.frscommunityclient.presenter.MyBlogCollectionFragmentPresenter;
import com.crystallightghot.frscommunityclient.view.adapter.MyBlogCollectionAdapter;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCollection;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xui.utils.ViewUtils;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyBlogCollectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyBlogCollectionFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    Activity activity;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnAddPackage)
    TextView btnAddPackage;
    @BindView(R.id.rvContents)
    RecyclerView rvContents;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    MyBlogCollectionFragmentPresenter presenter;
    MyBlogCollectionAdapter adapter;
    int pagerIndex = 0;
    // TODO: Rename and change types of parameters
    private String mParam1;

    public MyBlogCollectionFragment() {
        // Required empty public constructor
    }

    public static MyBlogCollectionFragment newInstance(String param1) {
        MyBlogCollectionFragment fragment = new MyBlogCollectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mine_blog, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        presenter = new MyBlogCollectionFragmentPresenter(this);
        adapter = new MyBlogCollectionAdapter();
        rvContents.setAdapter(adapter);
        tvTitle.setText("博客收藏");
        btnAddPackage.setVisibility(View.INVISIBLE);
        //下拉刷新
        ViewUtils.setViewsFont(refreshLayout);
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            adapter.getBlogCollections().clear();
            pagerIndex = 0;
            presenter.loadBlogCollections(pagerIndex);
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            presenter.loadBlogCollections(pagerIndex);
        });

        presenter.loadBlogCollections(pagerIndex);
    }


    public void loadMoreData(List<BlogCollection> blogCollections, boolean hasNext) {
        adapter.getBlogCollections().addAll(blogCollections);
        llStateful.showContent();
        refreshLayout.resetNoMoreData();
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        if (hasNext) {
            refreshLayout.setEnableLoadMore(true);
        } else {
            refreshLayout.setEnableLoadMore(false);
        }

        if (adapter.getBlogCollections().size() == 0) {
            llStateful.showEmpty();
            return;
        }
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.btnBack, R.id.btnAddPackage, R.id.rvContents})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                getActivity().onBackPressed();
                break;

        }
    }


}