package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentManageUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticleContentSpecifiedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticleContentSpecifiedFragment extends Fragment {

    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.articleDateCreated)
    TextView articleDateCreated;
    @BindView(R.id.btnFollow)
    AppCompatButton btnFollow;
    @BindView(R.id.articleType)
    TextView articleType;
    @BindView(R.id.articleTitle)
    TextView articleTitle;
    @BindView(R.id.btnCllection)
    ImageButton btnCllection;
    @BindView(R.id.articleContent)
    TextView articleContent;
    @BindView(R.id.report)
    TextView report;
    @BindView(R.id.btnLove)
    AppCompatButton btnLove;
    @BindView(R.id.articleComments)
    RecyclerView articleComments;
    @BindView(R.id.profile)
    RadiusImageView profile;

    public ArticleContentSpecifiedFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ArticleContentSpecifiedFragment newInstance(String param1) {
        return new ArticleContentSpecifiedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article_content_spefied, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.profile, R.id.btnFollow, R.id.btnCllection, R.id.btnLove})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile:
                FRSCFragmentManageUtil.intentToFragment(UserInformationFragment.newInstance("UserInformationFragment"), (BaseFragmentActivity) getActivity(), true);
                break;
            case R.id.btnFollow:
                break;
            case R.id.btnCllection:
                break;
            case R.id.btnLove:
                break;
            default:
                break;
        }
    }
}