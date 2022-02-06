package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.adapter.HomeSearchBlogAndHelpResultRecyclerViewAdapter;
import com.crystallightghot.frscommunityclient.view.adapter.HomeSearchUserResultRecyclerViewAdapter;
import com.crystallightghot.frscommunityclient.view.value.SearchType;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xui.utils.ViewUtils;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeSearchResultDefaultViewPagerItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * @author
 */
public class HomeSearchResultDefaultViewPagerItemFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    @BindView(R.id.rv_lists)
    RecyclerView rvLists;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private String mParam1;

    public HomeSearchResultDefaultViewPagerItemFragment(SearchType searchType) {
        this.searchType = searchType;
    }

    SearchType searchType;


    public HomeSearchResultDefaultViewPagerItemFragment() {

    }


    public static HomeSearchResultDefaultViewPagerItemFragment newInstance(String param1) {
        HomeSearchResultDefaultViewPagerItemFragment fragment = new HomeSearchResultDefaultViewPagerItemFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_result_view_pager, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        putDataToRV(null);

    }

    /**
     * 将数据加入到RecycleView中
     */
    public void putDataToRV(List dataAll) {
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

        if (SearchType.USE == searchType) {
            rvLists.setAdapter(new HomeSearchUserResultRecyclerViewAdapter(null));
        }else {

            rvLists.setAdapter(new HomeSearchBlogAndHelpResultRecyclerViewAdapter(getActivity(), dataAll));
        }

        //下拉刷新
        ViewUtils.setViewsFont(refreshLayout);
        refreshLayout.setOnRefreshListener(refreshLayout -> refreshLayout.getLayout().postDelayed(() -> {
            HomeViewPagerItemFragment.Status status = getRefreshStatus();
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
//        refreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果
    }


    private void showOffline() {
        llStateful.showOffline(v -> refreshLayout.autoRefresh());
        refreshLayout.setEnableLoadMore(false);
    }

    private void showError() {
        llStateful.showError(v -> refreshLayout.autoRefresh());
        refreshLayout.setEnableLoadMore(false);
    }

    public enum Status {
        SUCCESS,
        EMPTY,
        ERROR,
        NO_NET,
    }



    private HomeViewPagerItemFragment.Status getRefreshStatus() {
        return HomeViewPagerItemFragment.Status.SUCCESS;
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