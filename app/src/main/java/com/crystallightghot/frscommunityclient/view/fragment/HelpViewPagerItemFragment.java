package com.crystallightghot.frscommunityclient.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.adapter.HelpRecycleViewOfContentAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xui.utils.ViewUtils;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpViewPagerItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpViewPagerItemFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.rlContents)
    RecyclerView rlContents;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private String mParam1;

    public HelpViewPagerItemFragment() {
        // Required empty public constructor
    }

    /**
     * @param param1 Parameter 1.
     * @return A new instance of fragment HelpViewPagerItemFragment.
     */
    public static HelpViewPagerItemFragment newInstance(String param1) {
        HelpViewPagerItemFragment fragment = new HelpViewPagerItemFragment();
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
        View view = inflater.inflate(R.layout.fragment_help_view_pager_item, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        rlContents.setAdapter(new HelpRecycleViewOfContentAdapter());
        /**
         * 多recycleView 滑动事件监听
         */
        rlContents.setOnScrollChangeListener(new View.OnScrollChangeListener() {
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