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
import com.crystallightghot.frscommunityclient.view.fragment.BlogFragment;
import com.crystallightghot.frscommunityclient.view.fragment.HomeFragment;
import com.crystallightghot.frscommunityclient.view.fragment.MineFragment;
import com.crystallightghot.frscommunityclient.view.fragment.SomethingFoundFragment;
import com.crystallightghot.frscommunityclient.view.util.FRSCActivityUtile;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView2;

/**
 * @author crystallight
 */
public class HomeActivity extends BaseFragmentActivity {


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
    @BindView(R.id.home_iv_add)
    QMUIRadiusImageView2 homeIvAdd;

    Unbinder bind;
    String TAG = "调试";
    HomeViewPagerItemScrollChangedReceiver receiver;

    public void allBottomIconBeenDefaultState() {
        ibtnHome.setBackground(getResourceDrawable(R.drawable.home_home_no_clicked));
        ibtnBlog.setBackground(getResourceDrawable(R.drawable.home_blog_no_clicked));
        ibtnFounded.setBackground(getResourceDrawable(R.drawable.home_founded_no_clicked));
        ibtnAnswer.setBackground(getResourceDrawable(R.drawable.home_answer_no_clicked));
        ibtnSelf.setBackground(getResourceDrawable(R.drawable.ic_home_self_no_clicked));
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
        ibtnHome.setBackground(getResourceDrawable(R.drawable.home_home_clicked));
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
                ibtnHome.setBackground(getResourceDrawable(R.drawable.home_home_clicked));
                setItemTextColorClicked(tvHome);
                FRSCActivityUtile.showFragment(HomeFragment.newInstance("TAG:" + System.currentTimeMillis()),this,false);
                break;
            case R.id.ibtnBlog:
                allBottomIconBeenDefaultState();
                ibtnBlog.setBackground(getResourceDrawable(R.drawable.home_blog_clicked));
                setItemTextColorClicked(tvBlog);
                FRSCActivityUtile.showFragment(BlogFragment.newInstance("TAG:" + System.currentTimeMillis()), this, false);
                break;
            case R.id.ibtnFounded:
                addIconIsVisible(false);
                allBottomIconBeenDefaultState();
                ibtnFounded.setBackground(getResourceDrawable(R.drawable.home_founded_clicked));
                setItemTextColorClicked(tvFounded);
                FRSCActivityUtile.showFragment(SomethingFoundFragment.newInstance("TAG:" + System.currentTimeMillis()), this, false);
                break;
            case R.id.ibtnAnswer:
                allBottomIconBeenDefaultState();
                ibtnAnswer.setBackground(getResourceDrawable(R.drawable.home_answer_clicked));
                setItemTextColorClicked(tvAnswer);
                break;
            case R.id.ibtnSelf:
                addIconIsVisible(false);
                allBottomIconBeenDefaultState();
                ibtnSelf.setBackground(getResourceDrawable(R.drawable.home_self_clicked));
                setItemTextColorClicked(tvSelf);
                FRSCActivityUtile.showFragment(MineFragment.newInstance("TAG:" + System.currentTimeMillis()), this, false);
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
            homeIvAdd.setVisibility(View.VISIBLE);
        } else {
            homeIvAdd.setVisibility(View.GONE);
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
        init();
    }



    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
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
}

