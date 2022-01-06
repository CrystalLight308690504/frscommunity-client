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
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.broadcast.HomeViewPagerItemScrollChangedReceiver;
import com.crystallightghot.frscommunityclient.view.fragment.BlogFragment;
import com.crystallightghot.frscommunityclient.view.fragment.HomeFragment;
import com.crystallightghot.frscommunityclient.view.util.ActivityUtile;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView2;

import java.util.LinkedList;
import java.util.List;

/**
 * @author crystallight
 */
public class HomeActivity extends BaseActivity {


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

    List<Fragment> fragments = new LinkedList<>();
    HomeFragment fragmentLater;
    Unbinder bind;
    String TAG = "调试";
    HomeViewPagerItemScrollChangedReceiver receiver;
    final  int FRAGMENTCONTAINERID = R.id.homeFragment;


    public void allBottomIconBeenDefaultState() {
        ibtnHome.setBackground(getResourceDrawable(R.drawable.home_home_no_clicked));
        ibtnBlog.setBackground(getResourceDrawable(R.drawable.home_blog_no_clicked));
        ibtnFounded.setBackground(getResourceDrawable(R.drawable.home_founded_no_clicked));
        ibtnAnswer.setBackground(getResourceDrawable(R.drawable.home_answer_no_clicked));
        ibtnSelf.setBackground(getResourceDrawable(R.drawable.home_self_no_clicked));
        setItemTextColorNoClicked(tvHome);
        setItemTextColorNoClicked(tvAnswer);
        setItemTextColorNoClicked(tvBlog);
        setItemTextColorNoClicked(tvFounded);
        setItemTextColorNoClicked(tvSelf);
    }

    @OnClick({R.id.ibtnHome, R.id.ibtnBlog, R.id.ibtnFounded, R.id.ibtnAnswer, R.id.ibtnSelf})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibtnHome:
                allBottomIconBeenDefaultState();
                ibtnHome.setBackground(getResourceDrawable(R.drawable.home_home_clicked));
                setItemTextColorClicked(tvHome);
                ActivityUtile.showFragment(HomeFragment.newInstant("TAG:" + System.currentTimeMillis()),this,fragments, FRAGMENTCONTAINERID);
                break;
            case R.id.ibtnBlog:
                allBottomIconBeenDefaultState();
                ibtnBlog.setBackground(getResourceDrawable(R.drawable.home_blog_clicked));
                setItemTextColorClicked(tvBlog);
                ActivityUtile.showFragment(BlogFragment.newInstance("TAG:" + System.currentTimeMillis()), this,fragments, FRAGMENTCONTAINERID);
                break;
            case R.id.ibtnFounded:
                allBottomIconBeenDefaultState();
                ibtnFounded.setBackground(getResourceDrawable(R.drawable.home_founded_clicked));
                setItemTextColorClicked(tvFounded);
                break;
            case R.id.ibtnAnswer:
                allBottomIconBeenDefaultState();
                ibtnAnswer.setBackground(getResourceDrawable(R.drawable.home_answer_clicked));
                setItemTextColorClicked(tvAnswer);
                break;
            case R.id.ibtnSelf:
                allBottomIconBeenDefaultState();
                ibtnSelf.setBackground(getResourceDrawable(R.drawable.home_self_clicked));
                setItemTextColorClicked(tvSelf);
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


    private void init() {
        ibtnHome.setBackground(getResourceDrawable(R.drawable.home_home_clicked));
        setItemTextColorClicked(tvHome);

        // 添加fragment
       fragmentLater =  HomeFragment.newInstant("homeFragment");
        ActivityUtile.showFragment(fragmentLater, this, fragments, FRAGMENTCONTAINERID);

        // 注册广播
        IntentFilter intentFilter = new IntentFilter("HomeViewPagerItemScrollChangedReceiver");
        receiver = new HomeViewPagerItemScrollChangedReceiver(this);
        registerReceiver(receiver, intentFilter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bind = ButterKnife.bind(this);
        init();
    }

    /**
     * 显示添加图标
     */
    public void addIconIsShowed(boolean isShowed) {
        if (isShowed) {
            homeIvAdd.setVisibility(View.VISIBLE);
        } else {
            homeIvAdd.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");

    }

    @Override
    protected void onStart() {
        super.onStart();
        ActivityUtile.showFragment(fragmentLater,this,fragments, FRAGMENTCONTAINERID);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");

    }

    @Override
    protected void onStop() {
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

