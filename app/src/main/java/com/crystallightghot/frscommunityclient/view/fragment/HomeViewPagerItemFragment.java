package com.crystallightghot.frscommunityclient.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnScrollChangeListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.adapter.HomeRecyclerViewAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xui.utils.ViewUtils;
import com.xuexiang.xui.utils.WidgetUtils;
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
public class HomeViewPagerItemFragment extends Fragment {

    @BindView(R.id.rv_lists)
    RecyclerView rvLists;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;

    public HomeViewPagerItemFragment() {
    }


    List<HashMap> dataAll;

    public HomeViewPagerItemFragment(String label, List<HashMap> dataAll) {
        this.dataAll = dataAll;
        Bundle args = new Bundle();
        args.putString("label", label);
        setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_home_view_page_item, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        addRecycleViewScrolledListener();
        putDataToRV(dataAll);

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

        rvLists.setAdapter(new HomeRecyclerViewAdapter(getActivity(), dataAll));
        WidgetUtils.initRecyclerView(rvLists);
        //下拉刷新
        ViewUtils.setViewsFont(refreshLayout);
        refreshLayout.setOnRefreshListener(refreshLayout -> refreshLayout.getLayout().postDelayed(() -> {
            Status status = getRefreshStatus();
            switch (status) {
                case SUCCESS:
                    refreshLayout.resetNoMoreData();//setNoMoreData(false);
                    llStateful.showContent();
                    refreshLayout.setEnableLoadMore(true);
                    break;
                case EMPTY:
                    llStateful.showEmpty();
                    refreshLayout.setEnableLoadMore(false);
                    break;
                case ERROR:
                    showError();
                    break;
                case NO_NET:
                    showOffline();
                    break;
                default:
                    break;
            }
            refreshLayout.finishRefresh();
        }, 2000));
        //上拉加载
        refreshLayout.setOnLoadMoreListener(refreshLayout -> refreshLayout.getLayout().postDelayed(() -> {
            refreshLayout.finishLoadMore();

        }, 2000));
        refreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果
    }

    private void showOffline() {
        llStateful.showOffline(v -> refreshLayout.autoRefresh());
        refreshLayout.setEnableLoadMore(false);
    }

    private void showError() {
        llStateful.showError(v -> refreshLayout.autoRefresh());
        refreshLayout.setEnableLoadMore(false);
    }

    private enum Status {
        SUCCESS,
        EMPTY,
        ERROR,
        NO_NET,
    }

    private Status getRefreshStatus() {
        return Status.SUCCESS;
//        int status = (int) (Math.random() * 10);
//        if (status % 2 == 0) {
//        } else if (status % 3 == 0) {
//            return Status.EMPTY;
//        } else if (status % 5 == 0) {
//            return Status.ERROR;
//        } else {
//            return Status.NO_NET;
//        }
    }

}
