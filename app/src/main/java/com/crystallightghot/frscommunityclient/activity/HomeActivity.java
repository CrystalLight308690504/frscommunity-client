package com.crystallightghot.frscommunityclient.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.activity.adapter.HomeViewpageAdapter;
import com.qmuiteam.qmui.widget.QMUIViewPager;

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
    TextView tvOlder;
    @BindView(R.id.home_vp)
    QMUIViewPager homeVp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setViewPages(null);
    }



    void replaceFragment(Fragment addedFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.homeFrameLayout, addedFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    /**
     *
     *
     * @param views
     */
    private void setViewPages(List<View> views) {


        views = new LinkedList<>();

        int i = 0;
        int j = 0;
        while (j++ < 8) {
            TextView textView = new TextView(this);
            textView.setHeight(60);
            textView.setWidth(100);
            textView.setText("dfsad" + i++);
            Drawable a = getDrawable(R.color.applicationMainTheme);
            textView.setBackground(a);
            views.add(textView);
        }


        HomeViewpageAdapter adapter = new HomeViewpageAdapter(this, views);
        homeVp.setAdapter(adapter);

    }

    public void toDefaultState() {
        ibtnHome.setBackground(getResourceDrawable(R.drawable.home_home_no_clicked));
        ibtnBlog.setBackground(getResourceDrawable(R.drawable.home_blog_no_clicked));
        ibtnFounded.setBackground(getResourceDrawable(R.drawable.home_founded_no_clicked));
        ibtnAnswer.setBackground(getResourceDrawable(R.drawable.home_answer_no_clicked));
        ibtnSelf.setBackground(getResourceDrawable(R.drawable.home_self_no_clicked));
        if (null != tvOlder) {
            setItemTextColorNoClicked(tvOlder);
        }

    }

    @OnClick({R.id.ibtnHome, R.id.ibtnBlog, R.id.ibtnFounded, R.id.ibtnAnswer, R.id.ibtnSelf})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibtnHome:
                toDefaultState();
                ibtnHome.setBackground(getResourceDrawable(R.drawable.home_home_clicked));
                setItemTextColorClicked(tvHome);
                tvOlder = tvHome;
                break;
            case R.id.ibtnBlog:
                toDefaultState();
                ibtnBlog.setBackground(getResourceDrawable(R.drawable.home_blog_clicked));
                setItemTextColorClicked(tvBlog);
                tvOlder = tvBlog;
                break;
            case R.id.ibtnFounded:
                toDefaultState();
                ibtnFounded.setBackground(getResourceDrawable(R.drawable.home_founded_clicked));
                setItemTextColorClicked(tvFounded);
                tvOlder = tvFounded;
                break;
            case R.id.ibtnAnswer:
                toDefaultState();
                ibtnAnswer.setBackground(getResourceDrawable(R.drawable.home_answer_clicked));
                setItemTextColorClicked(tvAnswer);
                tvOlder = tvAnswer;
                break;
            case R.id.ibtnSelf:
                toDefaultState();
                ibtnSelf.setBackground(getResourceDrawable(R.drawable.home_self_clicked));
                setItemTextColorClicked(tvSelf);
                tvOlder = tvSelf;
                break;
        }
    }

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
    }
}

