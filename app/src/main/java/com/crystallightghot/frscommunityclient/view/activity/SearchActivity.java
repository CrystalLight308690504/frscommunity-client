package com.crystallightghot.frscommunityclient.view.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.fragment.SearchFragment;
import com.crystallightghot.frscommunityclient.view.util.ActivityUtile;

import java.util.LinkedList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {



   private List<Fragment> fragments = new LinkedList<>();
    Unbinder bind;

    private void init() {

        ActivityUtile.showFragment(SearchFragment.newInstance("search"), this,fragments,R.id.fragment_container );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_activities);
        bind =  ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    public List<Fragment> getFragments() {
        return fragments;
    }

}