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
import com.crystallightghot.frscommunityclient.view.activity.BaseActivity;
import com.crystallightghot.frscommunityclient.view.message.UserChangedMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCIntentUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.userName)
    TextView userName;

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

    private String mParam1;

    BaseActivity activity;

    public MyFragment() {
        FRSCEventBusUtil.register(this);
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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        activity = (BaseActivity) getActivity();
        User user = FRSCApplicationContext.getUser();
        if (null != user) {
            userName.setText(user.getUserName());
            String userProfile = user.getProfile();
            byte[] decodedString = Base64.decode(userProfile, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            this.userProfile.setImageBitmap(decodedByte);
        }
    }

    @OnClick({R.id.btnArrowRight, R.id.tviFllowed, R.id.tvFan, R.id.tvArticleBrowed, R.id.tvArticleCreated, R.id.ivBlog, R.id.ivAnswer, R.id.ivCllection, R.id.ivMyHelp, R.id.btnSetting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnArrowRight:
                FRSCIntentUtil.intentToSingleFragmentActivity(UserInformationFragment.newInstance());
                break;
            case R.id.ivBlog:
                FRSCIntentUtil.intentToSingleFragmentActivity(MyBlogFragment.newInstance("MyBlogFragment"));
                break;
            case R.id.ivAnswer:
                FRSCIntentUtil.intentToSingleFragmentActivity(MyAnswerFragment.newInstance("MyAnswerFragment"));
                break;
            case R.id.ivCllection:
                FRSCIntentUtil.intentToSingleFragmentActivity(MyCollectionFragment.newInstance("MyCollectionFragment"));
                break;
            case R.id.ivMyHelp:
                FRSCIntentUtil.intentToSingleFragmentActivity(MyQuestionFragment.newInstance("MyHelpQuestionedFragment"));
                break;
            case R.id.btnSetting:
                FRSCIntentUtil.intentToSingleFragmentActivity(SettingFragment.newInstance("MyHelpQuestionedFragment"));
                break;
            default:
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getUserChangedMessage(UserChangedMessage message) {
        User user = FRSCApplicationContext.getUser();
        Drawable userProfileD = FRSCApplicationContext.getUserProfile();
        userProfile.setImageDrawable(userProfileD);
        userName.setText(user.getUserName());
    }
}


