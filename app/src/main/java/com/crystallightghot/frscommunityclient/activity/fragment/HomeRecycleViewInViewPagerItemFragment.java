package com.crystallightghot.frscommunityclient.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.crystallightghot.frscommunityclient.activity.adapter.HomeViewPagerRecyclerAdapter;

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
public class HomeRecycleViewInViewPagerItemFragment extends Fragment {

    @BindView(R.id.rv_lists)
    RecyclerView rvLists;

    public HomeRecycleViewInViewPagerItemFragment() {
    }


    List<HashMap<Object, Object>> dataAll;

    public HomeRecycleViewInViewPagerItemFragment(String label, List<HashMap<Object, Object>> dataAll) {
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
                Log.d("调试", "---------------------: i");
                Log.d("调试", "onScrollChange: i  " + i);
                Log.d("调试", "onScrollChange: i1 " + i1);
                Log.d("调试", "onScrollChange: i2 " + i2);
                Log.d("调试", "onScrollChange: i3  " + scrolledInstance);
                Log.d("调试", "---------------------: i");

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
