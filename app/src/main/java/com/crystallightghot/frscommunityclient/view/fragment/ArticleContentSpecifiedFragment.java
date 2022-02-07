package com.crystallightghot.frscommunityclient.view.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;
import com.crystallightghot.frscommunityclient.view.message.BlogChangMessage;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.crystallightghot.frscommunityclient.view.pojo.skatingtype.SkatingType;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCImagePatternChangeUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    TextView btnReport;
    @BindView(R.id.btnLove)
    AppCompatButton btnLove;
    @BindView(R.id.articleComments)
    RecyclerView articleComments;
    @BindView(R.id.profile)
    RadiusImageView profile;
    @BindView(R.id.btnModify)
    TextView btnModify;
    private Blog blog;
    private User user;
    private SkatingType skatingType;

    public ArticleContentSpecifiedFragment() {
        FRSCEventBusUtil.register(this);
        // Required empty public constructor
    }

    public ArticleContentSpecifiedFragment(@NonNull Blog blog) {
        FRSCEventBusUtil.register(this);
        this.blog = blog;
        this.user = blog.getUser();
    }

    // TODO: Rename and change types and number of parameters
    public static ArticleContentSpecifiedFragment newInstance(String param1) {
        return new ArticleContentSpecifiedFragment();
    }

    // TODO: Rename and change types and number of parameters
    public static ArticleContentSpecifiedFragment newInstance(Blog blog) {
        return new ArticleContentSpecifiedFragment(blog);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article_content_spefied, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        if (null != blog && null != user) {
            articleType.setText("博文");
            Drawable profileDrawable = FRSCImagePatternChangeUtil.getDrawableFromBase64(user.getProfile());
            profile.setImageDrawable(profileDrawable);
            userName.setText(user.getUserName());
            articleDateCreated.setText(blog.getCreatedTime().toString());
            articleTitle.setText(blog.getBlogTitle());
            articleContent.setText(blog.getContent());

            User userLogin = FRSCApplicationContext.getUser();
            Long userLoginUserId = userLogin.getUserId();
            Long userId = user.getUserId();
            if (userLoginUserId.equals(userId)) {
                btnFollow.setVisibility(View.GONE);
                btnLove.setVisibility(View.GONE);
                btnCllection.setVisibility(View.GONE);
                btnReport.setVisibility(View.GONE);
                btnModify.setVisibility(View.VISIBLE);
            }
        }
    }

    @OnClick({R.id.profile, R.id.btnFollow, R.id.btnCllection, R.id.btnLove,R.id.btnModify})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile:
                FRSCFragmentUtil.intentToFragment(UserInformationFragment.newInstance("UserInformationFragment"), (BaseFragmentActivity) getActivity(), true);
                break;
            case R.id.btnFollow:
                break;
            case R.id.btnCllection:
                break;
            case R.id.btnLove:
                break;
                case R.id.btnModify:
                    FRSCFragmentUtil.intentToFragmentAddedToBackStack(PutBlogContentFragment.newInstance(blog));
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(BlogChangMessage message ) {
        initView();
    }

}