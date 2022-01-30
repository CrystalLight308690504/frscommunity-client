package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.presenter.EditUserGenderPresenter;
import com.crystallightghot.frscommunityclient.view.activity.BaseFragmentActivity;
import com.crystallightghot.frscommunityclient.view.message.UserChangedMessage;
import com.crystallightghot.frscommunityclient.view.pojo.system.User;
import com.crystallightghot.frscommunityclient.view.util.FRSCApplicationContext;
import com.crystallightghot.frscommunityclient.view.util.FRSCEventBusUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCFragmentUtil;
import com.crystallightghot.frscommunityclient.view.util.XToastUtils;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.widget.grouplist.XUICommonListItemView;
import com.xuexiang.xui.widget.grouplist.XUIGroupListView;
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView;
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditeUserInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * @author 30869
 */
public class EditeUserInformationFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.groupListView)
    XUIGroupListView groupListView;

    private String mParam1;
    XUICommonListItemView itemGender;
    XUICommonListItemView itemDescription;
    EditUserGenderPresenter presenter;

    String[] sex = {"男", "女"};
    User user;

    public EditeUserInformationFragment() {
        // Required empty public constructor
         user = FRSCApplicationContext.getUser();
        presenter = new EditUserGenderPresenter(this);
        FRSCEventBusUtil.register(this);
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
        View view = inflater.inflate(R.layout.fragment_edit_user_information, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }


    private void initView() {

        itemGender = getItem("性别", user.getGender());
        itemDescription = getItem("自我介绍", user.getDescription());
        View.OnClickListener onClickListener = v -> {
            if (v instanceof XUICommonListItemView) {
                CharSequence text = ((XUICommonListItemView) v).getText();
                if ("性别".equals(text)) {
                    showSexPickerView();
                } else if ("自我介绍".equals(text)) {
                    FRSCFragmentUtil.intentToFragment(EditMyDescriptionFragment.newInstance("EditMyDescriptionFragment"), (BaseFragmentActivity) getActivity(),true);
                }

            }
        };

        int size = DensityUtils.dp2px(getContext(), 20);
        XUIGroupListView.newSection(getContext())
                .setTitle("用户信息")
                .setDescription("点击对应标签修改信息")
                .setLeftIconSize(size, ViewGroup.LayoutParams.WRAP_CONTENT)
                .addItemView(itemGender, onClickListener)
                .addItemView(itemDescription, onClickListener)
                .addTo(groupListView);

    }

    private XUICommonListItemView getItem(String titleText, String detailText) {
        XUICommonListItemView item = groupListView.createItemView(
                null,
                titleText,
                detailText,
                XUICommonListItemView.HORIZONTAL,
                XUICommonListItemView.ACCESSORY_TYPE_NONE);
        item.setAccessoryType(XUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        return item;
    }

    /**
     * 性别选择
     */
    private void showSexPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), (v, options1, options2, options3) -> {
            // 请求修改
              presenter.modifyUserGender(sex[options1]);
            return false;
        })
                .setTitleText("性别选择")
                .setSelectOptions(0)
                .build();
        pvOptions.setPicker(sex);
        pvOptions.show();
    }
    /**
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(UserChangedMessage message) {
        itemGender.setDetailText(user.getGender());
        itemDescription.setDetailText(user.getDescription());
    }
}