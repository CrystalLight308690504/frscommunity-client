package com.crystallightghot.frscommunityclient.activity.fragment;

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
import com.crystallightghot.frscommunityclient.activity.adapter.HomeViewPagerRecyclerAdapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
public class HomeViewPagerItemFragment extends Fragment {

    @BindView(R.id.rv_lists)
    RecyclerView rvLists;
    public HomeViewPagerItemFragment(){}


    List<HashMap<Object,Object>> dataAll;
    public  HomeViewPagerItemFragment(String label, List<HashMap<Object,Object>> dataAll) {
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
        if (null == dataAll){
            dataAll= new LinkedList<>();
        }

        for (int i = 0; i < 20; i++) {
            HashMap<Object,Object> data = new HashMap();
            data.put("userName","crystallightghost");
            data.put("putDate","2020-10-3");
            data.put("articleStyle","博客");
            data.put("articleTitle","轮滑");
            data.put("articleContent","轮滑的地方轮滑的地方轮滑的地方轮滑的地方轮滑的地方");
            dataAll.add(data);
        }
        rvLists.setAdapter(new HomeViewPagerRecyclerAdapter(getActivity(),dataAll));
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
