package com.crystallightghot.frscommunityclient.view.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.crystallightghot.frscommunityclient.presenter.ArticleContentSpecifiedFragmentPresenter;
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;
import com.crystallightghot.frscommunityclient.view.adapter.BlogCriticismAdapter;
import com.crystallightghot.frscommunityclient.view.message.BlogChangMessage;
import com.crystallightghot.frscommunityclient.view.pojo.blog.Blog;
import com.crystallightghot.frscommunityclient.view.pojo.blog.BlogCriticism;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCImagePatternChangeUtil;
import com.google.android.material.textfield.TextInputEditText;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xui.utils.ViewUtils;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

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
    @BindView(R.id.btnCollection)
    ImageButton btnCollection;
    @BindView(R.id.report)
    TextView btnReport;
    @BindView(R.id.btnLove)
    AppCompatButton btnLove;
    @BindView(R.id.profile)
    RadiusImageView profile;
    @BindView(R.id.btnModify)
    TextView btnModify;
    @BindView(R.id.ieCriticism)
    TextInputEditText ieCriticism;
    @BindView(R.id.btnCriticism)
    Button btnCriticism;
    @BindView(R.id.articleSkatingType)
    TextView articleSkatingType;
    @BindView(R.id.articleContent)
    TextView articleContent;
    @BindView(R.id.rlArticleCriticisms)
    RecyclerView rlArticleCriticisms;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Blog blog;
    private User user;
    int pagerIndex = 0;
    boolean hasNext;
    BlogCriticismAdapter blogCriticismAdapter = new BlogCriticismAdapter();
    ArticleContentSpecifiedFragmentPresenter presenter;
    public ArticleContentSpecifiedFragment(@NonNull Blog blog) {
        FRSCEventBusUtil.register(this);
        this.blog = blog;
        this.user = blog.getUser();
        presenter = new ArticleContentSpecifiedFragmentPresenter(this);
    }



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

        View view = inflater.inflate(R.layout.fragment_article_content_spefied, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        if (null != blog && null != user) {
            rlArticleCriticisms.setAdapter(blogCriticismAdapter);
            presenter.checkIfFollowed(user.getUserId());
            presenter.checkIfCollection(blog.getBlogId());
            presenter.checkIsApplauseBlog(blog.getBlogId());
            presenter.loadCriticisms(blog.getBlogId(), pagerIndex);

            //下拉刷新
            ViewUtils.setViewsFont(refreshLayout);
            refreshLayout.setOnRefreshListener(refreshLayout -> {
                blogCriticismAdapter.getBlogCriticisms().clear();
                pagerIndex = 0;
                presenter.loadCriticisms(blog.getBlogId(), pagerIndex);
            });

            //上拉加载
            refreshLayout.setOnLoadMoreListener(refreshLayout -> {
                presenter.loadCriticisms(blog.getBlogId(), ++pagerIndex);
            });

            articleType.setText("博文");
            Drawable profileDrawable = FRSCImagePatternChangeUtil.getDrawableFromBase64(user.getProfile());
            profile.setImageDrawable(profileDrawable);
            userName.setText(user.getUserName());
            articleDateCreated.setText(blog.getCreatedTime().toString());
            articleTitle.setText(blog.getBlogTitle());
            articleContent.setText(blog.getContent());
            articleSkatingType.setText(blog.getSkatingType().getName());
            ieCriticism.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length() > 0) {
                        btnCriticism.setEnabled(true);
                    } else {
                        btnCriticism.setEnabled(false);
                    }
                }
            });
            User userLogin = FRSCApplicationContext.getUser();
            Long userLoginUserId = userLogin.getUserId();
            Long userId = user.getUserId();
            if (userLoginUserId.equals(userId)) {
                changeToSelfState();
            }

        }
    }


    private void changeToSelfState() {
        btnFollow.setVisibility(View.INVISIBLE);
        btnCollection.setVisibility(View.INVISIBLE);
        btnModify.setVisibility(View.VISIBLE);
    }

    public void isFollowed(boolean isFollowed) {
        btnFollow.setSelected(isFollowed);
        if (btnFollow.isSelected()) { // 取消关注
            btnFollow.setText("已关注");
        } else { // 关注
            btnFollow.setText("关注");
        }
    }

    public void showMoreBlogCriticism(List<BlogCriticism> criticismsAdded, boolean hasNext) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        this.hasNext = hasNext;
        llStateful.showContent();
        refreshLayout.resetNoMoreData();

        if (hasNext) {
            refreshLayout.setEnableLoadMore(true);
        } else {
            refreshLayout.setEnableLoadMore(false);
        }
        blogCriticismAdapter.getBlogCriticisms().addAll(criticismsAdded);
        blogCriticismAdapter.notifyDataSetChanged();
    }

    public void showIsCollectionBlog(boolean isCollection) {
        btnCollection.setActivated(isCollection);
    }

    public void showIsApplauseBlog(boolean isLove) {
        btnLove.setActivated(isLove);
    }

    public void criticiseBlogAfter(boolean isSuccess,BlogCriticism blogCriticism) {
        if (isSuccess) {
            ieCriticism.setText("");
            ieCriticism.clearFocus();
            blogCriticismAdapter.getBlogCriticisms().add(0,blogCriticism);
            blogCriticismAdapter.notifyItemInserted(0);
        }

    }

    @OnClick({R.id.profile, R.id.btnFollow, R.id.btnCriticism, R.id.btnCollection, R.id.btnLove, R.id.btnModify})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile:
                FRSCFragmentUtil.intentToFragment(UserInformationFragment.newInstance(user), (BaseFragmentActivity) getActivity(), true);
                break;
            case R.id.btnFollow:
                btnFollow.setSelected(!btnFollow.isSelected());
                if (btnFollow.isSelected()) { // 取消关注
                    btnFollow.setText("已关注");
                    presenter.followUser(user.getUserId());
                } else { // 关注
                    btnFollow.setText("关注");
                    presenter.cancelFollower(FRSCApplicationContext.getUser().getUserId(), user.getUserId());
                }
                break;
            case R.id.btnCollection:
                btnCollection.setActivated(!btnCollection.isActivated());
                if (btnCollection.isActivated()) { // 收藏
                    presenter.collectionBlog(blog);
                } else {// 取消收藏
                    presenter.cancelCollectionBlog(blog);
                }
                break;
            case R.id.btnLove:
                btnLove.setActivated(!btnLove.isActivated());
                if (btnLove.isActivated()) { // 点赞
                    presenter.applauseBlog(blog);
                } else {// 取消点赞
                    presenter.cancelApplauseBlog(blog);
                }
                break;
            case R.id.btnModify:
                FRSCFragmentUtil.intentToFragmentAddedToBackStack(PutBlogContentFragment.newInstance(blog));
                break;
            case R.id.btnCriticism:
                presenter.criticiseBlog(ieCriticism.getText().toString(), blog.getBlogId());
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(BlogChangMessage message) {
        initView();
    }

    public void showMoreBlogCriticismError(String message) {
        llStateful.showError(message, v -> refreshLayout.autoRefresh());
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
    }
}