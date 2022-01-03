package com.crystallightghot.frscommunityclient.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.activity.fragment.HomeFragment;
import com.crystallightghot.frscommunityclient.activity.fragment.TabFragment;

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

    List<Fragment> fragments = new LinkedList<>();
    TextView tvClicked;


    Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bind = ButterKnife.bind(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        addFragemntToShow(HomeFragment.newInstant("s"));
    }

    /**
     * 替换中间的fragment
     *
     * @param addedFragment
     */
    void addFragemntToShow(Fragment addedFragment) {
        if (null == addedFragment) {
            return;
        }
        // 隐藏所有fragment
        setAllFragmentToHideen();

        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (addedFragment.isAdded()) { // 已经添加fragment
            transaction.show(addedFragment);
        } else { // 新加入的fragment
            transaction.add(R.id.homeFragment, addedFragment);
            transaction.show(addedFragment);
            fragments.add(addedFragment);
        }


        transaction.commitAllowingStateLoss();
    }

    /**
     * 隐藏所有已加入的fragment
     */
    private void setAllFragmentToHideen() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            Log.d("HomeActivity", "fragment: isAdded： " + i + fragment.isAdded());
            Log.d("HomeActivity", "fragment: isVisible： " + i + fragment.isVisible());
            Log.d("HomeActivity", "fragment: isHidden： " + i + fragment.isHidden());
            transaction.hide(fragment);
        }
        transaction.commitAllowingStateLoss();
    }


    public void toDefaultState() {
        ibtnHome.setBackground(getResourceDrawable(R.drawable.home_home_no_clicked));
        ibtnBlog.setBackground(getResourceDrawable(R.drawable.home_blog_no_clicked));
        ibtnFounded.setBackground(getResourceDrawable(R.drawable.home_founded_no_clicked));
        ibtnAnswer.setBackground(getResourceDrawable(R.drawable.home_answer_no_clicked));
        ibtnSelf.setBackground(getResourceDrawable(R.drawable.home_self_no_clicked));
        if (null != tvClicked) {
            setItemTextColorNoClicked(tvClicked);
        }

    }

    @OnClick({R.id.ibtnHome, R.id.ibtnBlog, R.id.ibtnFounded, R.id.ibtnAnswer, R.id.ibtnSelf})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibtnHome:
                toDefaultState();
                ibtnHome.setBackground(getResourceDrawable(R.drawable.home_home_clicked));
                setItemTextColorClicked(tvHome);
                addFragemntToShow(HomeFragment.newInstant("s"));
                break;
            case R.id.ibtnBlog:
                toDefaultState();
                ibtnBlog.setBackground(getResourceDrawable(R.drawable.home_blog_clicked));
                setItemTextColorClicked(tvBlog);
                tvClicked = tvBlog;
                addFragemntToShow(TabFragment.newinstance("fdfasf"));
                break;
            case R.id.ibtnFounded:
                toDefaultState();
                ibtnFounded.setBackground(getResourceDrawable(R.drawable.home_founded_clicked));
                setItemTextColorClicked(tvFounded);
                tvClicked = tvFounded;
                break;
            case R.id.ibtnAnswer:
                toDefaultState();
                ibtnAnswer.setBackground(getResourceDrawable(R.drawable.home_answer_clicked));
                setItemTextColorClicked(tvAnswer);
                tvClicked = tvAnswer;
                break;
            case R.id.ibtnSelf:
                toDefaultState();
                ibtnSelf.setBackground(getResourceDrawable(R.drawable.home_self_clicked));
                setItemTextColorClicked(tvSelf);
                tvClicked = tvSelf;
                break;
            default:
                break;
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
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
    }
}

