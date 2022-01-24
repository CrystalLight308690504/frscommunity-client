package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.utils.XToastUtils;
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;
import com.crystallightghot.frscommunityclient.view.util.FRSCShowFragmentToActivityUtil;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.widget.grouplist.XUICommonListItemView;
import com.xuexiang.xui.widget.grouplist.XUIGroupListView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.groupListView)
    XUIGroupListView mGroupListView;

    private String mParam1;

    BaseFragmentActivity activity;

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
        initView();
        return view;
    }

    private void initView() {
        activity = (BaseFragmentActivity) getActivity();
        View.OnClickListener onClickListener = v -> {
            if (v instanceof XUICommonListItemView) {
                CharSequence text = ((XUICommonListItemView) v).getText();
                if ("用户名".equals(text)) {
                    FRSCShowFragmentToActivityUtil.showFragmentAddedToBackStack(EditUserNameFragment.newInstance(""));
                }else if("邮箱".equals(text)){
                    FRSCShowFragmentToActivityUtil.showFragmentAddedToBackStack(EditUserEmailFragment.newInstance(""));
                }else if("密码".equals(text)){
                    FRSCShowFragmentToActivityUtil.showFragmentAddedToBackStack(EditeUserPasswordByOldPasswordFragment.newInstance(""));
                }
                XToastUtils.toast(text + " is Clicked");
            }
        };

        int size = DensityUtils.dp2px(getContext(), 20);
        XUIGroupListView.newSection(getContext())
                .setLeftIconSize(size, ViewGroup.LayoutParams.WRAP_CONTENT)
                .addItemView(getItem(R.mipmap.icon_application,"头像:", ""), onClickListener)
                .addItemView(getItem("用户名", "crystalLight"), onClickListener)
                .addItemView(getItem("邮箱", "308690504@qq.com"), onClickListener)
                .addItemView(getItem("密码", ""), onClickListener)

                .addTo(mGroupListView);


        XUICommonListItemView unLogin = mGroupListView.createItemView(
               null,
                "注销",
                "",
                XUICommonListItemView.HORIZONTAL,
                XUICommonListItemView.ACCESSORY_TYPE_NONE);
        XUIGroupListView.newSection(getContext())
                .setLeftIconSize(size, ViewGroup.LayoutParams.WRAP_CONTENT)
                .addItemView(unLogin, onClickListener)
                .addTo(mGroupListView);

    }



    private XUICommonListItemView getItem(int pictureId, String titleText, String detailText) {
        XUICommonListItemView item = mGroupListView.createItemView(
                ContextCompat.getDrawable(getContext(),pictureId),
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
}