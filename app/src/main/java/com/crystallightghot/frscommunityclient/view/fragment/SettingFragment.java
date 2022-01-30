package com.crystallightghot.frscommunityclient.view.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.contract.SettingContract;
import com.crystallightghot.frscommunityclient.presenter.SettingPresenter;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;
import com.crystallightghot.frscommunityclient.view.activity.MainActivity;
import com.crystallightghot.frscommunityclient.view.message.UnLoginMessage;
import com.crystallightghot.frscommunityclient.view.message.UserChangedMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentUtil;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.widget.grouplist.XUICommonListItemView;
import com.xuexiang.xui.widget.grouplist.XUIGroupListView;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends BaseFragment implements SettingContract.View {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.groupListView)
    XUIGroupListView mGroupListView;

    private String mParam1;

    BaseFragmentActivity activity;
    SettingPresenter presenter;
    XUICommonListItemView profileItem;
    XUICommonListItemView usernameItem;
    XUICommonListItemView emailItem;

    public SettingFragment() {
        // Required empty public constructor
    }

    public static SettingFragment newInstance(String param1) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FRSCEventBusUtil.register(this);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        activity = (BaseFragmentActivity) getActivity();
        presenter = new SettingPresenter(this, FRSCApplicationContext.getUser());

        View.OnClickListener onClickListener = v -> {
            if (v instanceof XUICommonListItemView) {
                CharSequence text = ((XUICommonListItemView) v).getText();
                if ("用户名".equals(text)) {
                    FRSCFragmentUtil.intentToFragment(EditUsernameFragment.newInstance(""), activity, true);
                } else if ("邮箱".equals(text)) {
                    FRSCFragmentUtil.intentToFragment(EditUserEmailFragment.newInstance(""), activity, true);
                } else if ("头像".equals(text)) {
                    FRSCFragmentUtil.intentToFragment(EditUserProfileFragment.newInstance(""), activity, true);
                } else if ("密码".equals(text)) {
                    FRSCFragmentUtil.intentToFragment(EditeUserPasswordByOldPasswordFragment.newInstance(""), activity, true);
                } else if ("退出登录".equals(text)) {
                    presenter.unLogin();
                }

            }
        };

        int size = DensityUtils.dp2px(getContext(), 20);

        User user = FRSCApplicationContext.getUser();
        if (null != user) {
            String userProfileBase64 = user.getProfile();
            byte[] decodedString = Base64.decode(userProfileBase64, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            profileItem = getItem(decodedByte, "头像", "");
            usernameItem = getItem("用户名", user.getUserName());
            emailItem = getItem("邮箱", user.getEmail());

            XUIGroupListView.newSection(getContext())
                    .setLeftIconSize(size, ViewGroup.LayoutParams.WRAP_CONTENT)
                    .addItemView(profileItem, onClickListener)
                    .addItemView(usernameItem, onClickListener)
                    .addItemView(emailItem, onClickListener)
                    .addItemView(getItem("密码", "******"), onClickListener)
                    .addTo(mGroupListView);

            XUICommonListItemView unLogin = mGroupListView.createItemView(
                    null,
                    "退出登录",
                    "",
                    XUICommonListItemView.HORIZONTAL,
                    XUICommonListItemView.ACCESSORY_TYPE_NONE);
            XUIGroupListView.newSection(getContext())
                    .setLeftIconSize(size, ViewGroup.LayoutParams.WRAP_CONTENT)
                    .addItemView(unLogin, onClickListener)
                    .addTo(mGroupListView);
        }
    }


    private XUICommonListItemView getItem(Bitmap bitmap, String titleText, String detailText) {

        XUICommonListItemView item = mGroupListView.createItemView(
                new BitmapDrawable(bitmap),
                titleText,
                detailText,
                XUICommonListItemView.HORIZONTAL,
                XUICommonListItemView.ACCESSORY_TYPE_NONE);
        item.setAccessoryType(XUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        return item;
    }

    private XUICommonListItemView getItem(String titleText, String detailText) {
        XUICommonListItemView item = mGroupListView.createItemView(
                null,
                titleText,
                detailText,
                XUICommonListItemView.HORIZONTAL,
                XUICommonListItemView.ACCESSORY_TYPE_NONE);
        item.setAccessoryType(XUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        return item;
    }

    /**
     * 登入消息
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(UnLoginMessage message) {
        MainActivity mainActivity = FRSCApplicationContext.getMainActivity();
        if (null != mainActivity) {
            mainActivity.finish();
        }
        FRSCFragmentUtil.intentToFragment(LoginFragment.newInstance(""), activity, false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getUserChangedMessage(UserChangedMessage message) {
        User user = FRSCApplicationContext.getUser();
        usernameItem.setDetailText(user.getUserName());
        String userProfileBase64 = user.getProfile();
        byte[] decodedString = Base64.decode(userProfileBase64, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        profileItem.setImageDrawable(new BitmapDrawable(decodedByte));
        emailItem.setDetailText(user.getEmail());
    }

}