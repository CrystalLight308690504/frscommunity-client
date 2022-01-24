package com.crystallightghot.frscommunityclient.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.utils.XToastUtils;
import com.crystallightghot.frscommunityclient.view.util.FRSCShowFragmentToActivityUtil;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.widget.grouplist.XUICommonListItemView;
import com.xuexiang.xui.widget.grouplist.XUIGroupListView;
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView;
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditeUserInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * @author 30869
 */
public class EditeUserInformationFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.groupListView)
    XUIGroupListView groupListView;

    private String mParam1;
    private String mParam2;

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
        View view = inflater.inflate(R.layout.fragment_edit_user_information, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }


    private void initView() {
        View.OnClickListener onClickListener = v -> {
            if (v instanceof XUICommonListItemView) {
                CharSequence text = ((XUICommonListItemView) v).getText();
                if ("性别".equals(text)) {
                    showSexPickerView();
                } else if ("自我介绍".equals(text)) {
                    FRSCShowFragmentToActivityUtil.showFragmentAddedToBackStack(EditMyDescriptionFragment.newInstance("EditMyDescriptionFragment"));
                }
                XToastUtils.toast(text + " is Clicked");
            }
        };

        int size = DensityUtils.dp2px(getContext(), 20);
        XUIGroupListView.newSection(getContext())
                .setTitle("用户信息")
                .setDescription("点击对应标签修改信息")
                .setLeftIconSize(size, ViewGroup.LayoutParams.WRAP_CONTENT)
                .addItemView(getItem("性别", "男"), onClickListener)
                .addItemView(getItem("自我介绍", "程序员"), onClickListener)

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

    int sexChose;
    /**
     * 性别选择
     */
    private void showSexPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), (v, options1, options2, options3) -> {
            XToastUtils.toast(options1 + " ========");
            sexChose = options1;
            return false;
        })
                .setTitleText("性别选择")
                .setSelectOptions(sexChose)
                .build();
        String[] sex = {"男", "女"};
        pvOptions.setPicker(sex);
        pvOptions.show();
    }

}