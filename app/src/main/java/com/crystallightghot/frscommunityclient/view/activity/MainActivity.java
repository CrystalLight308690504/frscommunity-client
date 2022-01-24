package com.crystallightghot.frscommunityclient.view.activity;

import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.broadcast.HomeViewPagerItemScrollChangedReceiver;
import com.crystallightghot.frscommunityclient.view.fragment.*;
import com.crystallightghot.frscommunityclient.view.util.FRSCIntentUtil;
import com.crystallightghot.frscommunityclient.view.util.FRSCShowFragmentToActivityUtil;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView2;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;

/**
 * @author crystallight
 */
public class MainActivity extends BaseFragmentActivity {


    @BindView(R.id.ibtnHome)
    ImageButton ibtnHome;
    @BindView(R.id.tvHome)
    TextView tvHome;
    @BindView(R.id.ibtnBlog)
    ImageButton ibtnBlog;
    @BindView(R.id.tvBlog)
    TextView tvBlog;
    @BindView(R.id.ibtnFounded)
    ImageButton ibtnFounded;
    @BindView(R.id.tvFounded)
    TextView tvFounded;
    @BindView(R.id.ibtnAnswer)
    ImageButton ibtnAnswer;
    @BindView(R.id.tvAnswer)
    TextView tvAnswer;
    @BindView(R.id.ibtnSelf)
    ImageButton ibtnSelf;
    @BindView(R.id.tvSelf)
    TextView tvSelf;
    @BindView(R.id.bottomItem)
    LinearLayout bottomItem;
    @BindView(R.id.btnAdd)
    QMUIRadiusImageView2 btnAdd;

    Unbinder bind;
    String TAG = "调试";
    HomeViewPagerItemScrollChangedReceiver receiver;

    public void allBottomIconBeenDefaultState() {
        ibtnHome.setBackground(getResourceDrawable(R.mipmap.home_home_no_clicked));
        ibtnBlog.setBackground(getResourceDrawable(R.mipmap.home_blog_no_clicked));
        ibtnFounded.setBackground(getResourceDrawable(R.mipmap.home_founded_no_clicked));
        ibtnAnswer.setBackground(getResourceDrawable(R.mipmap.home_answer_no_clicked));
        ibtnSelf.setBackground(getResourceDrawable(R.mipmap.ic_home_self_no_clicked));
        setItemTextColorNoClicked(tvHome);
        setItemTextColorNoClicked(tvAnswer);
        setItemTextColorNoClicked(tvBlog);
        setItemTextColorNoClicked(tvFounded);
        setItemTextColorNoClicked(tvSelf);
    }

    /**
     * 显示添加图标
     */
    public void bottomNavigationAndAddIconState(boolean isShowed) {
        if (isShowed) {
            bottomItemIsVisible(true);
            addIconIsVisible(true);
        } else {
            addIconIsVisible(false);
            bottomItemIsVisible(false);

        }
    }

    private void init() {
        ibtnHome.setBackground(getResourceDrawable(R.mipmap.home_home_clicked));
        setItemTextColorClicked(tvHome);

        // 注册广播
        IntentFilter intentFilter = new IntentFilter("HomeViewPagerItemScrollChangedReceiver");
        receiver = new HomeViewPagerItemScrollChangedReceiver(this);
        registerReceiver(receiver, intentFilter);

    }


    @OnClick({R.id.ibtnHome, R.id.ibtnBlog, R.id.ibtnFounded, R.id.ibtnAnswer, R.id.ibtnSelf})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibtnHome:
                allBottomIconBeenDefaultState();
                ibtnHome.setBackground(getResourceDrawable(R.mipmap.home_home_clicked));
                setItemTextColorClicked(tvHome);
                FRSCShowFragmentToActivityUtil.showFragment(HomeFragment.newInstance("TAG:" + System.currentTimeMillis()), this, false);
                break;
            case R.id.ibtnBlog:
                allBottomIconBeenDefaultState();
                ibtnBlog.setBackground(getResourceDrawable(R.mipmap.home_blog_clicked));
                setItemTextColorClicked(tvBlog);
                FRSCShowFragmentToActivityUtil.showFragment(BlogFragment.newInstance("TAG:" + System.currentTimeMillis()), this, false);
                break;
            case R.id.ibtnFounded:
                addIconIsVisible(false);
                allBottomIconBeenDefaultState();
                ibtnFounded.setBackground(getResourceDrawable(R.mipmap.home_founded_clicked));
                setItemTextColorClicked(tvFounded);
                FRSCShowFragmentToActivityUtil.showFragment(SomethingFoundFragment.newInstance("TAG:" + System.currentTimeMillis()), this, false);
                break;
            case R.id.ibtnAnswer:
                allBottomIconBeenDefaultState();
                ibtnAnswer.setBackground(getResourceDrawable(R.mipmap.home_answer_clicked));
                setItemTextColorClicked(tvAnswer);
                break;
            case R.id.ibtnSelf:
                addIconIsVisible(false);
                allBottomIconBeenDefaultState();
                ibtnSelf.setBackground(getResourceDrawable(R.mipmap.home_self_clicked));
                setItemTextColorClicked(tvSelf);
                FRSCShowFragmentToActivityUtil.showFragment(MyFragment.newInstance("TAG:" + System.currentTimeMillis()), this, false);
                break;
            default:
                break;
        }
    }

    /**
     * 设置底部栏可见
     */

    public void bottomItemIsVisible(boolean isShowed) {
        if (isShowed) {
            bottomItem.setVisibility(View.VISIBLE);
        } else {
            bottomItem.setVisibility(View.GONE);
        }
    }

    /**
     * 添加按钮是否可见
     */

    public void addIconIsVisible(boolean isShowed) {
        if (isShowed) {
            btnAdd.setVisibility(View.VISIBLE);
        } else {
            btnAdd.setVisibility(View.GONE);
        }
    }

    /**
     * 获取
     *
     * @param resourceId
     * @return
     */
    public Drawable getResourceDrawable(int resourceId) {
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), resourceId);
        return drawable;
    }

    public void setItemTextColorClicked(TextView tv) {
        tv.setTextColor(getResources().getColor(R.color.application_color_primary, null));
    }

    public void setItemTextColorNoClicked(TextView tv) {
        tv.setTextColor(getResources().getColor(R.color.color_black, null));
    }

    public void setBottomItemSate(boolean isVisible) {
        bottomItem.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 设置默认fragment
        setDefaultFragment(HomeFragment.newInstance("homeFragment"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bind = ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onRestart: ");
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
        unregisterReceiver(receiver);
    }

    @OnClick(R.id.btnAdd)
    public void onClick() {
        showSimpleBottomSheetList();

    }

    // ================================ 生成不同类型的BottomSheet
    private void showSimpleBottomSheetList() {
        new BottomSheet.BottomListSheetBuilder(this)
                .setTitle("添加")
                .addItem("博客","博客")
                .addItem("求助","求助")
                .setIsCenter(true)
                .setOnSheetItemClickListener((dialog, itemView, position, tag) -> {
//                    XToastUtils.info(""+position);
                    dialog.dismiss();
                    switch (position){
                        case 0:
                            FRSCIntentUtil.IntentToSingleFragmentActivity(PutBlogFragment.newInstance(""));
                            break;
                        case 1 :
                            break;
                    }
                })
                .build()
                .show();
    }
}

