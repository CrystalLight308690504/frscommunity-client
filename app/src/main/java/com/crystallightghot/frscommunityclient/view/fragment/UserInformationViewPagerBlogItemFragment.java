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
import com.crystallightghot.frscommunityclient.view.adapter.BlogRecyclerViewAdapter;
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

    public UserInformationViewPagerBlogItemFragment() {
    }

    public UserInformationViewPagerBlogItemFragment(String label) {

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

    }

    /**
     * 将数据加入到RecycleView中
     */
    public void putDataToRV(List dataAll) {
        rvLists.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (null == dataAll) {
            dataAll = new LinkedList<>();
        }

        for (int i = 0; i < 20; i++) {
            HashMap<Object, Object> data = new HashMap();
            data.put("userName", "crystallightghost");
            data.put("putDate", "2020-10-3");
            data.put("articleStyle", "博客");
            data.put("articleTitle", "轮滑" + i);
            data.put("articleContent", "轮滑的地方轮滑的地方轮滑的地方轮滑的地方轮滑的地方" + i);
            dataAll.add(data);
        }

        rvLists.setAdapter(new BlogRecyclerViewAdapter(getActivity(), dataAll));

        //下拉刷新
        ViewUtils.setViewsFont(refreshLayout);
        refreshLayout.setOnRefreshListener(refreshLayout ->{

        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            refreshLayout.finishLoadMore();

        });
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
