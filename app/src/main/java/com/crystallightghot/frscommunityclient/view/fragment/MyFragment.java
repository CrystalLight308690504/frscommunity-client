package com.crystallightghot.frscommunityclient.view.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.presenter.MyFragmentPresenter;
import com.crystallightghot.frscommunityclient.view.activity.BaseActivity;
import com.crystallightghot.frscommunityclient.view.pojo.system.Role;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCIntentUtil;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment implements BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.userName)
    TextView userName;

    TextView tvArticleCreated;
    @BindView(R.id.ivBlog)
    AppCompatImageView ivBlog;
    @BindView(R.id.ivAnswer)
    AppCompatImageView ivAnswer;
    @BindView(R.id.ivCllection)
    AppCompatImageView ivCllection;

    @BindView(R.id.icivSetting)
    ImageView icivSetting;

    @BindView(R.id.ivMyHelp)
    AppCompatImageView ivMyHelp;
    @BindView(R.id.btnSetting)
    ConstraintLayout btnSetting;
    @BindView(R.id.btnArrowRight)
    ImageView btnArrowRight;
    @BindView(R.id.ivicMyHelp)
    TextView ivicMyHelp;
    @BindView(R.id.userProfile)
    RadiusImageView userProfile;
    @BindView(R.id.tvicFllowed)
    TextView tvicFllowed;
    @BindView(R.id.tvFollowUserCount)
    TextView tvFollowUserCount;
    @BindView(R.id.tvicFan)
    TextView tvicFan;
    @BindView(R.id.tvFanCount)
    TextView tvFanCount;
    @BindView(R.id.tvBlogCount)
    TextView tvBlogCount;
    @BindView(R.id.tvApplauseCount)
    TextView tvApplauseCount;
    @BindView(R.id.tvUserRoleName)
    TextView tvUserRoleName;

    User user;
    Role role;
    BaseActivity activity;
    MyFragmentPresenter presenter;
    @BindView(R.id.btnAdm)
    ConstraintLayout btnAdm;

    public MyFragment() {
        presenter = new MyFragmentPresenter(this);
    }


    public static MyFragment newInstance(String param1) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        activity = (BaseActivity) getActivity();
         user = FRSCApplicationContext.getUser();
        if (null != user) {
            userName.setText(user.getUserName());
            if (user.getRole() != null) {
                tvUserRoleName.setText(user.getRole().getRoleName());
            }
            String userProfile = user.getProfile();
            byte[] decodedString = Base64.decode(userProfile, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            this.userProfile.setImageBitmap(decodedByte);
            role = user.getRole();
            if (role.getCode().equals("user")) {
                btnAdm.setVisibility(View.INVISIBLE);
            }
        }
        loadData();
    }

    private void loadData() {
        presenter.loadFollowUserCount();
        presenter.loadFanOfUserCount();
        presenter.loadBlogCount();
        presenter.loadApplauseCount();

    }

    @OnClick({R.id.btnArrowRight, R.id.ivBlog, R.id.ivAnswer, R.id.ivCllection, R.id.ivMyHelp, R.id.btnSetting, R.id.tvicFllowed, R.id.tvicFan, R.id.btnAdm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnArrowRight:
                FRSCIntentUtil.intentToSingleFragmentActivity(UserInformationFragment.newInstance());
                break;
            case R.id.ivBlog:
                FRSCIntentUtil.intentToSingleFragmentActivity(MyBlogFragment.newInstance("MyBlogFragment"));
                break;
            case R.id.ivAnswer:
                FRSCIntentUtil.intentToSingleFragmentActivity(MyBlogCollectionFragment.newInstance("MyBlogCollectionFragment"));
                break;
            case R.id.ivCllection:
                FRSCIntentUtil.intentToSingleFragmentActivity(MyQuestionCollectionFragment.newInstance("MyQuestionCollectionFragment"));
                break;
            case R.id.ivMyHelp:
                FRSCIntentUtil.intentToSingleFragmentActivity(MyQuestionFragment.newInstance("MyHelpQuestionedFragment"));
                break;
            case R.id.btnSetting:
                FRSCIntentUtil.intentToSingleFragmentActivity(SettingFragment.newInstance("MyHelpQuestionedFragment"));
                break;
            case R.id.tvicFllowed:
                FRSCIntentUtil.intentToSingleFragmentActivity(MyUserFollowedFragment.newInstance());

                break;
            case R.id.tvicFan:
                FRSCIntentUtil.intentToSingleFragmentActivity(MyFanFragment.newInstance());
                break;
             case R.id.btnAdm:
                 if (role.getCode().equals("superAdm")) {
                     new BottomSheet.BottomListSheetBuilder(getActivity())
                             .addItem("用户管理", "用户管理")
                             .addItem("博客管理", "用户管理")
                             .setIsCenter(true)
                             .setOnSheetItemClickListener(this)
                             .build()
                             .show();
                 }else if (role.getCode().equals("blogAdm")) {
                     new BottomSheet.BottomListSheetBuilder(getActivity())
                             .addItem("博客管理", "用户管理")
                             .setIsCenter(true)
                             .setOnSheetItemClickListener(this)
                             .build()
                             .show();
                 }else if(role.getCode().equals("userAdm")) {
                     new BottomSheet.BottomListSheetBuilder(getActivity())
                             .addItem("用户管理", "用户管理")
                             .setIsCenter(true)
                             .setOnSheetItemClickListener(this)
                             .build()
                             .show();
                 }
                break;
            default:
                break;
        }
    }

    /**
     * 显示关注的用户数量
     *
     * @param followUserCount
     */
    public void showFollowUserCount(long followUserCount) {
        tvFollowUserCount.setText(followUserCount + "");
    }

    public void showFanCount(long fanCount) {
        tvFanCount.setText(fanCount + "");
    }

    public void showApplauseCount(long questionCount) {
        tvApplauseCount.setText(questionCount + "");
    }

    public void showBlogCount(long blogCount) {
        tvBlogCount.setText(blogCount + "");
    }

    public void userChanged() {
        User user = FRSCApplicationContext.getUser();
        Drawable userProfileD = FRSCApplicationContext.getUserProfile();
        userProfile.setImageDrawable(userProfileD);
        userName.setText(user.getUserName());
    }

    @Override
    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
        dialog.dismiss();
        switch (tag) {
            case "用户管理":
                FRSCIntentUtil.intentToSingleFragmentActivity(ManageUserFragment.newInstance());
                break;
            case "博客管理":
                FRSCIntentUtil.intentToSingleFragmentActivity(ManageBlogFragment.newInstance());
                break;
            default:
                break;
        }
    }
}


