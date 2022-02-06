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
import com.crystallightghot.frscommunityclient.view.dialog.AddCategoryDialogFragment;
import com.crystallightghot.frscommunityclient.view.message.TransportDataMessage;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyCollectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyCollectionFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    Activity activity;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnAddPackage)
    TextView btnAddPackage;
    @BindView(R.id.rvMyBlogs)
    RecyclerView rvMyBlogs;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    AddCategoryDialogFragment dialogFragment;


    // TODO: Rename and change types of parameters
    private String mParam1;

    public MyCollectionFragment() {
        dialogFragment = new AddCategoryDialogFragment();
        FRSCEventBusUtil.register(this);
        // Required empty public constructor
    }

    public static MyCollectionFragment newInstance(String param1) {
        MyCollectionFragment fragment = new MyCollectionFragment();
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
        View view = inflater.inflate(R.layout.fragment_mine_blog, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        activity = getActivity();
        tvTitle.setText("我的收藏");


    }

    @OnClick({R.id.btnBack, R.id.btnAddPackage, R.id.rvMyBlogs})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                activity.onBackPressed();
                break;
            case R.id.btnAddPackage:
                dialogFragment.show(getFragmentManager(), "AddClassificationDialogFragment");
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void getMessage(TransportDataMessage message) {
        if (message.getMessageKey() != dialogFragment) {
            return;
        }
        Map data = (Map) message.getData();
        String categoryName = (String) data.get("categoryName");
        String description = (String) data.get("description");
    }

}