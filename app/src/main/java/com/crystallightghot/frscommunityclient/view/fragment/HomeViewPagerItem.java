package com.crystallightghot.frscommunityclient.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnScrollChangeListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.adapter.HomeRecyclerViewAdapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author crystallightghost
 * @date 2022/1/2
 * @Version: 1.0
 * description：
 */
public class HomeViewPagerItem extends Fragment {

    @BindView(R.id.rv_lists)
    RecyclerView rvLists;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    public HomeViewPagerItem() {
    }


    List<HashMap<Object, Object>> dataAll;

    public HomeViewPagerItem(String label, List<HashMap<Object, Object>> dataAll) {
        this.dataAll = dataAll;
        Bundle args = new Bundle();
        args.putString("label", label);
        setArguments(args);
    }

    private void addRecycleViewScrolledListent() {
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

        swipeRefreshLayout.setColorSchemeResources(R.color.xui_config_color_main_theme);
        swipeRefreshLayout.setOnRefreshListener(() -> new Handler().postDelayed(() -> swipeRefreshLayout.setRefreshing(false),2000));

        addRecycleViewScrolledListent();
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
            data.put("articleContent", "轮滑的地方轮滑的地方轮滑的地方轮滑的地方轮滑的地方"+i);
            dataAll.add(data);
        }
        rvLists.setAdapter(new HomeRecyclerViewAdapter(getActivity(), dataAll));
    }

}
