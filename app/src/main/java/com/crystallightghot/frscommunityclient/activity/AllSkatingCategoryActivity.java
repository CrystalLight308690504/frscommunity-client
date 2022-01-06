package com.crystallightghot.frscommunityclient.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.flowlayout.BaseTagAdapter;
import com.xuexiang.xui.widget.flowlayout.FlowTagLayout;

public class AllSkatingCategoryActivity extends BaseActivity {

    @BindView(R.id.all_categories_ftl_my)
    FlowTagLayout allCategoriesFtlMy;

    @BindView(R.id.all_categories_ftl_more)
    FlowTagLayout allCategoriesFtlMore;
    @BindView(R.id.titlebar)
    TitleBar titlebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_skating_category);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        String[] strings = ResUtils.getStringArray(R.array.tags_values);

        initMultiFlowTagLayout(allCategoriesFtlMy, strings);
        initMultiFlowTagLayout(allCategoriesFtlMore, strings);
        titlebar.setLeftClickListener(view -> finish());




    }

    private void initMultiFlowTagLayout(FlowTagLayout flowTagLayout, String[] tags) {
        MyAdapter tagAdapter = new MyAdapter(this);
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