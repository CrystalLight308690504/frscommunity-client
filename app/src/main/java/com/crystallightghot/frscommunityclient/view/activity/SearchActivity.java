package com.crystallightghot.frscommunityclient.view.activity;

import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.fragment.SearchFragment;

public class SearchActivity extends FragmentNeededActivity {


    Unbinder bind;

    private void init() {
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
//        ActivityUtile.showFragment(getDefaultFragment(), this, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    void setContainerId() {
        setFragmentContainerId(R.id.fragment_container);
    }

    @Override
    void setDefaultFragment() {
        setDefaultFragment( SearchFragment.newInstance("search"));
    }

}
