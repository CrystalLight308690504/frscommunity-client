package com.crystallightghot.frscommunityclient.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.activity.BaseActivity;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.flowlayout.BaseTagAdapter;
import com.xuexiang.xui.widget.flowlayout.FlowTagLayout;

public class HomeSkatingTypeFragment extends BaseFragment {

    BaseActivity activity;
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.all_categories_ly_my)
    LinearLayout allCategoriesLyMy;
    @BindView(R.id.all_categories_ftl_my)
    FlowTagLayout allCategoriesFtlMy;
    @BindView(R.id.all_categories_ly_more)
    LinearLayout allCategoriesLyMore;
    @BindView(R.id.all_categories_ftl_more)
    FlowTagLayout allCategoriesFtlMore;

    public static Fragment newInstance(String login) {
        return new HomeSkatingTypeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_all_skating_category, container, false);
        ButterKnife.bind(this, view);
        initView();

        return view;
    }

    private void initView() {
        activity = (BaseActivity) getActivity();
        String[] strings = ResUtils.getStringArray(R.array.tags_values);
        initMultiFlowTagLayout(allCategoriesFtlMy, strings);
        initMultiFlowTagLayout(allCategoriesFtlMore, strings);
        titlebar.setLeftClickListener(view -> activity.finish());

    }

    private void initMultiFlowTagLayout(FlowTagLayout flowTagLayout, String[] tags) {
        MyAdapter tagAdapter = new MyAdapter(activity);
        flowTagLayout.setAdapter(tagAdapter);
        flowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        tagAdapter.addTags(tags);
        tagAdapter.setSelectedPositions(2, 3, 4);
    }


    class MyAdapter extends BaseTagAdapter<String, TextView> {

        public MyAdapter(Context context) {
            super(context);
        }

        @Override
        protected TextView newViewHolder(View convertView) {
            return (TextView) convertView.findViewById(R.id.tv_tag);
        }

        @Override
        protected int getLayoutId() {
            return R.layout.adapter_item_tag;
        }

        @Override
        protected void convert(TextView textView, String item, int position) {
            textView.setText(item);
        }
    }
}