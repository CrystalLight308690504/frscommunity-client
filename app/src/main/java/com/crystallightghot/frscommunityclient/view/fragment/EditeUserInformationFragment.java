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
 * Use the {@link EditeUserInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditeUserInformationFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.groupListView)
    XUIGroupListView mGroupListView;

    private String mParam1;
    private String mParam2;

    BaseFragmentActivity activity;

    public EditeUserInformationFragment() {
        // Required empty public constructor
    }

    public static EditeUserInformationFragment newInstance(String param1) {
        EditeUserInformationFragment fragment = new EditeUserInformationFragment();
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
        View view = inflater.inflate(R.layout.fragment_edite_user_information, container, false);
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
                    FRSCShowFragmentToActivityUtil.showFragmentAddedToBackStack(EditeUserNameFragment.newInstance(""));
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
                .setTitle("用户信息")
                .setDescription("点击对应标签修改信息")
                .setLeftIconSize(size, ViewGroup.LayoutParams.WRAP_CONTENT)
                .addItemView(getItem(R.mipmap.icon_application,"头像:", ""), onClickListener)
                .addItemView(getItem("用户名", "crystalLight"), onClickListener)
                .addItemView(getItem("邮箱", "308690504@qq.com"), onClickListener)
                .addItemView(getItem("密码", ""), onClickListener)

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