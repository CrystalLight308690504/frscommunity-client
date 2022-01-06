package com.crystallightghot.frscommunityclient.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.activity.util.ActivityUtile;

import java.util.LinkedList;
import java.util.List;

public class FullFragmentActivities extends AppCompatActivity {


    List<Fragment> fragments = new LinkedList<>();
    Unbinder bind;

    /**
     * 替换中间的fragment
     *
     * @param addedFragment
     */
    void addFragmentToShow(Fragment addedFragment, AppCompatActivity activity) {
        if (null == addedFragment) {
            return;
        }
        // 隐藏所有fragment
        ActivityUtile.setAllFragmentsToHiden(this,fragments);

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 已经添加fragment
        if (addedFragment.isAdded()) {
            transaction.show(addedFragment);
        } else { // 新加入的fragment
            transaction.add(R.id.fragment_container, addedFragment);
            fragments.add(addedFragment);
        }
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_activities);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}