package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.activity.BaseActivity;
import com.crystallightghot.frscommunityclient.view.util.FRSCIntentUtil;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView2;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.userProfile)
    QMUIRadiusImageView2 userProfile;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.userDescription)
    TextView userDescription;

    @BindView(R.id.tviFllowed)
    TextView tviFllowed;
    @BindView(R.id.tvFan)
    TextView tvFan;
    @BindView(R.id.tvArticleBrowed)
    TextView tvArticleBrowed;
    @BindView(R.id.tvicArticleCreated)
    TextView tvicArticleCreated;
    @BindView(R.id.tvArticleCreated)
    TextView tvArticleCreated;
    @BindView(R.id.ivBlog)
    AppCompatImageView ivBlog;
    @BindView(R.id.ivAnswer)
    AppCompatImageView ivAnswer;
    @BindView(R.id.ivCllection)
    AppCompatImageView ivCllection;
    @BindView(R.id.tvicFllowed)
    TextView tvicFllowed;
    @BindView(R.id.tvicFan)
    TextView tvicFan;
    @BindView(R.id.tvicScanded)
    TextView tvicScanded;
    @BindView(R.id.ivicBlog)
    TextView ivicBlog;
    @BindView(R.id.ivicAnswer)
    TextView ivicAnswer;
    @BindView(R.id.ivicCllection)
    TextView ivicCllection;
    @BindView(R.id.icivSetting)
    ImageView icivSetting;

    private String mParam1;

    BaseActivity activity;

    public MineFragment() {
        // Required empty public constructor
    }


    public static MineFragment newInstance(String param1) {
        MineFragment fragment = new MineFragment();
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
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        activity = (BaseActivity) getActivity();
    }

    @OnClick({R.id.userProfile, R.id.tviFllowed, R.id.tvFan, R.id.tvArticleBrowed, R.id.tvArticleCreated, R.id.ivBlog, R.id.ivAnswer, R.id.ivCllection})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.userProfile:
                break;
            case R.id.tviFllowed:
                break;
            case R.id.tvFan:
                break;
            case R.id.tvArticleBrowed:
                break;
            case R.id.tvArticleCreated:
                break;
            case R.id.ivBlog:
                FRSCIntentUtil.IntentToSingleFragmentActivity(activity,MineBlogFragment.newInstance(""));
                break;
            case R.id.ivAnswer:
                break;
            case R.id.ivCllection:
                break;
        }
    }
}