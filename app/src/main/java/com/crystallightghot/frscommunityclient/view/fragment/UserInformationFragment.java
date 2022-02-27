package com.crystallightghot.frscommunityclient.view.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.presenter.UserInformationFragmentPresenter;
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;
import com.crystallightghot.frscommunityclient.view.adapter.UserInformationBlogViewPagerItemAdapter;
import com.crystallightghot.frscommunityclient.view.message.UserChangedMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserInformationFragment extends Fragment {
    UserInformationFragmentPresenter presenter;
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.ivInformationBackground)
    ImageView userInformationBackground;

    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_gender)
    TextView userGender;
    @BindView(R.id.user_self_introduce)
    TextView userSelfIntroduce;
    @BindView(R.id.btnEditeUserInformation)
    TextView btnEditeUserInformation;
    @BindView(R.id.clUserInformation)
    ConstraintLayout clUserInformation;

    @BindView(R.id.search_article)
    ImageView searchArticle;
    @BindView(R.id.user_profile)
    RadiusImageView userProfile;
    @BindView(R.id.vpContentViewPager)
    ViewPager2 vpContentViewPager;
    @BindView(R.id.tlSkatingTypes)
    TabLayout tlSkatingTypes;
    @BindView(R.id.btnFollow)
    Button btnFollow;
    @BindView(R.id.icFollowerUserCount)
    TextView icFollowerUserCount;
    @BindView(R.id.tvFollowUserCount)
    TextView tvFollowUserCount;
    @BindView(R.id.icFollowerCount)
    TextView icFollowerCount;
    @BindView(R.id.tvFollowerCount)
    TextView tvFollowerCount;
    User user;
    @BindView(R.id.tvApplauseCount)
    TextView tvApplauseCount;

    public UserInformationFragment() {
        FRSCEventBusUtil.register(this);
        presenter = new UserInformationFragmentPresenter(this);
        user = FRSCApplicationContext.getUser();
    }

    public UserInformationFragment(User user) {
        this.user = user;
        presenter = new UserInformationFragmentPresenter(this);
    }

    public static UserInformationFragment newInstance(User user) {
        UserInformationFragment fragment = new UserInformationFragment(user);

        return fragment;
    }

    public static UserInformationFragment newInstance() {
        UserInformationFragment fragment = new UserInformationFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_information, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        if (null == user) {
            return;
        }
        btnFollow.setOnClickListener(view -> {
            btnFollow.setSelected(!btnFollow.isSelected());
            if (btnFollow.isSelected()) { // 取消关注
                btnFollow.setText("已关注");
                presenter.followUser(user.getUserId());
            } else { // 关注
                btnFollow.setText("关注");
                presenter.cancelFollower(FRSCApplicationContext.getUser().getUserId(), user.getUserId());
            }
        });
        userName.setText(user.getUserName());
        String userProfileBase64 = user.getProfile();
        byte[] decodedString = Base64.decode(userProfileBase64, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        userProfile.setImageBitmap(decodedByte);
        userInformationBackground.setImageBitmap(decodedByte);
        userGender.setText(user.getGender());
        userSelfIntroduce.setText(user.getDescription());

        // 隐藏按钮
        if (user.getUserId().equals(FRSCApplicationContext.getUser().getUserId())) {
            btnFollow.setVisibility(View.INVISIBLE);
            btnEditeUserInformation.setVisibility(View.VISIBLE);
        } else {
            btnFollow.setVisibility(View.VISIBLE);
            btnEditeUserInformation.setVisibility(View.INVISIBLE);
            presenter.checkIfFollowed(user.getUserId());
        }

        presenter.loadFollowUserCount(user.getUserId());
        presenter.loadFollowerCount(user.getUserId());
        presenter.loadApplauseCount(user.getUserId());

        String[] tabTitles = new String[]{"博客", "问答"};
        vpContentViewPager.setAdapter(new UserInformationBlogViewPagerItemAdapter(this, user));
        new TabLayoutMediator(tlSkatingTypes, vpContentViewPager, (tab, position) -> tab.setText(tabTitles[position])
        ).attach();
    }

    public void isFollowed(boolean isFollowed) {
        btnFollow.setSelected(isFollowed);
        if (btnFollow.isSelected()) { // 取消关注
            btnFollow.setText("已关注");
        } else { // 关注
            btnFollow.setText("关注");
        }
    }

    public void showFollowUserCount(long articleCount) {
        tvFollowUserCount.setText("" + articleCount);
    }

    public void showFollowerCount(long followerCount) {
        tvFollowerCount.setText("" + followerCount);
    }

    public void showApplauseCount(long applauseCount) {
        tvApplauseCount.setText("" + applauseCount);
    }


    @OnClick({R.id.btnEditeUserInformation, R.id.icFollowerUserCount, R.id.icFollowerCount})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEditeUserInformation:
                FRSCFragmentUtil.intentToFragment(EditeUserInformationFragment.newInstance(""), (BaseFragmentActivity) getActivity(), true);
                break;
            case R.id.icFollowerUserCount:
                break;
            case R.id.icFollowerCount:
                break;
        }
    }

    /**
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(UserChangedMessage message) {
        userName.setText(user.getUserName());
        userProfile.setImageDrawable(FRSCApplicationContext.getUserProfile());
        userGender.setText(user.getGender());
        userSelfIntroduce.setText(user.getDescription());
    }

    @OnClick(R.id.btnFollow)
    public void onClick() {
    }

}