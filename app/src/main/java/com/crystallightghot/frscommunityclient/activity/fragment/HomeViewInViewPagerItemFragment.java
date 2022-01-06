package com.crystallightghot.frscommunityclient.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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
import com.crystallightghot.frscommunityclient.activity.adapter.HomeViewPagerRecyclerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author crystallightghost
 * @date 2022/1/2
 * @Version: 1.0
 * description：
 */
/*
 * @Description TODO
 * @Date 2022/1/2 19:00
 * @Created by CrystalLightGhost
 */
public class HomeViewInViewPagerItemFragment extends Fragment {

    @BindView(R.id.rv_lists)
    RecyclerView rvLists;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    public HomeViewInViewPagerItemFragment() {
    }


    List<HashMap<Object, Object>> dataAll;

    public HomeViewInViewPagerItemFragment(String label, List<HashMap<Object, Object>> dataAll) {
        this.dataAll = dataAll;
        Bundle args = new Bundle();
        args.putString("label", label);
        setArguments(args);
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
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);
            }
        });

        rvLists.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (null == dataAll) {
            dataAll = new LinkedList<>();
        }

        for (int i = 0; i < 20; i++) {
            HashMap<Object, Object> data = new HashMap();
            data.put("userName", "crystallightghost");
            data.put("putDate", "2020-10-3");
            data.put("articleStyle", "博客");
            data.put("articleTitle", "轮滑");
            data.put("articleContent", "轮滑的地方轮滑的地方轮滑的地方轮滑的地方轮滑的地方");
            dataAll.add(data);
        }
        rvLists.setAdapter(new HomeViewPagerRecyclerAdapter(getActivity(), dataAll));
        rvLists.setOnScrollChangeListener(new View.OnScrollChangeListener() {
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


    @Override
    public void onStart() {
        super.onStart();
    }
}
