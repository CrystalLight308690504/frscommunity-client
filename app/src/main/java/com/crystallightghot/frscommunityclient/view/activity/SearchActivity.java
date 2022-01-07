package com.crystallightghot.frscommunityclient.view.activity;

import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.fragment.SearchFragment;
import com.crystallightghot.frscommunityclient.view.util.ActivityUtile;

public class SearchActivity extends BaseActivity {


    Unbinder bind;

    private void init() {
        setFragmentContainerId(R.id.fragment_container);
        setDefaultFragment( SearchFragment.newInstance("search"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_activities);
        bind = ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ActivityUtile.showFragment(getDefaultFragment(), this, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
