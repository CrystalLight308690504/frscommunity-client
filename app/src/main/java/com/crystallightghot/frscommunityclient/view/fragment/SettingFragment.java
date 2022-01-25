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
import com.crystallightghot.frscommunityclient.utils.EventBusUtil;
import com.crystallightghot.frscommunityclient.utils.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.utils.XToastUtils;
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;
import com.crystallightghot.frscommunityclient.view.message.UnLoginMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCShowFragmentToActivityUtil;
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
    SettingPresenter presenter ;

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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        EventBusUtil.register(this);
        initView();
        return view;
    }

    private void initView() {
        activity = (BaseFragmentActivity) getActivity();
        presenter = new SettingPresenter(this,FRSCApplicationContext.getUser());

        View.OnClickListener onClickListener = v -> {
            if (v instanceof XUICommonListItemView) {
                CharSequence text = ((XUICommonListItemView) v).getText();
                if ("用户名".equals(text)) {
                    FRSCShowFragmentToActivityUtil.showFragmentAddedToBackStack(EditUserNameFragment.newInstance(""));
                }else if("邮箱".equals(text)){
                    FRSCShowFragmentToActivityUtil.showFragmentAddedToBackStack(EditUserEmailFragment.newInstance(""));
                }else if("密码".equals(text)){
                    FRSCShowFragmentToActivityUtil.showFragmentAddedToBackStack(EditeUserPasswordByOldPasswordFragment.newInstance(""));
                }else if("退出登陆".equals(text)){
                    presenter.unLogin();

                }
                XToastUtils.toast(text + " is Clicked");
            }
        };

        int size = DensityUtils.dp2px(getContext(), 20);

        User user = FRSCApplicationContext.getUser();
        if (null != user){

            String userProfileBase64 = user.getProfile();
            byte[] decodedString = Base64.decode(userProfileBase64, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        XUIGroupListView.newSection(getContext())
                .setLeftIconSize(size, ViewGroup.LayoutParams.WRAP_CONTENT)
                .addItemView(getItem(decodedByte,"头像:", ""), onClickListener)
                .addItemView(getItem("用户名", user.getUserName()), onClickListener)
                .addItemView(getItem("邮箱", user.getEmail()), onClickListener)
                .addItemView(getItem("密码", "******"), onClickListener)

                .addTo(mGroupListView);


        XUICommonListItemView unLogin = mGroupListView.createItemView(
               null,
                "退出登陆",
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

    private XUICommonListItemView getItem( String titleText, String detailText) {
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

        // 注销失败
        if (!message.isSuccess()) {
            XToastUtils.error(message.getMessage());
            return;
        }
        XToastUtils.success(message.getMessage());

        FRSCShowFragmentToActivityUtil.showFragmentNoAddedToBackStack(LoginFragment.newInstance(""));
    }

}