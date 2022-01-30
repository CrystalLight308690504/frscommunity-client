package com.crystallightghot.frscommunityclient.view.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;
import com.crystallightghot.frscommunityclient.view.message.UserChangedMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserInformationFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.user_information_background)
    ImageView userInformationBackground;

    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_gender)
    TextView userGender;
    @BindView(R.id.user_self_introduce)
    TextView userSelfIntroduce;
    @BindView(R.id.followers)
    TextView followers;
    @BindView(R.id.btnEditeUserInformation)
    TextView btnEditeUserInformation;
    @BindView(R.id.half_top)
    ConstraintLayout halfTop;
    @BindView(R.id.tab_s)
    TableLayout tabS;
    @BindView(R.id.search_article)
    ImageView searchArticle;
    @BindView(R.id.user_profile)
    RadiusImageView userProfile;

    private String mParam1;
    User user;

    public UserInformationFragment() {
        // Required empty public constructor
        FRSCEventBusUtil.register(this);
        user = FRSCApplicationContext.getUser();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment UserInformationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserInformationFragment newInstance(String param1) {
        UserInformationFragment fragment = new UserInformationFragment();
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
        View view = inflater.inflate(R.layout.fragment_user_information, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        if (null != user) {
            userName.setText(user.getUserName());
            String userProfileBase64 = user.getProfile();
            byte[] decodedString = Base64.decode(userProfileBase64, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            userProfile.setImageBitmap(decodedByte);
            userGender.setText(user.getGender());
            userSelfIntroduce.setText(user.getDescription());
        }

    }

    @OnClick({R.id.followers, R.id.btnEditeUserInformation})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEditeUserInformation:
                FRSCFragmentUtil.intentToFragment(EditeUserInformationFragment.newInstance(""), (BaseFragmentActivity) getActivity(), true);
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
}