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
import com.crystallightghot.frscommunityclient.presenter.MyBlogPresenter;
import com.crystallightghot.frscommunityclient.view.adapter.MyBlogCategoryRecycleViewAdapter;
import com.crystallightghot.frscommunityclient.view.dialog.CategoryDialogFragment;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCategory;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyBlogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyBlogFragment extends BaseFragment {

    CategoryDialogFragment dialogFragment;
    private static final String ARG_PARAM1 = "param1";

    Activity activity;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnAddPackage)
    TextView btnAddPackage;
    @BindView(R.id.rvMyBlogs)
    RecyclerView rvMyBlogs;

    MyBlogPresenter presenter;
    private String mParam1;

    public MyBlogFragment() {
        presenter = new MyBlogPresenter(this);
    }

    public static MyBlogFragment newInstance(String param1) {
        MyBlogFragment fragment = new MyBlogFragment();
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
        activity = getActivity();
        presenter.loadingCategory();
    }

    @OnClick({R.id.btnBack, R.id.btnAddPackage, R.id.rvMyBlogs})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                activity.onBackPressed();
                break;
            case R.id.btnAddPackage:
                dialogFragment = new CategoryDialogFragment();
                dialogFragment.show(getFragmentManager(), "AddClassificationDialogFragment");
                break;
        }
    }

    public void loadingData(List<BlogCategory> blogCategories) {
        MyBlogCategoryRecycleViewAdapter adapter = new MyBlogCategoryRecycleViewAdapter(blogCategories);
        rvMyBlogs.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FRSCEventBusUtil.unregister(presenter);
    }

}