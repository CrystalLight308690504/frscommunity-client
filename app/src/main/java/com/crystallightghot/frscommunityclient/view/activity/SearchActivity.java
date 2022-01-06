package com.crystallightghot.frscommunityclient.view.activity;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.fragment.SearchFragment;
import com.crystallightghot.frscommunityclient.view.util.ActivityUtile;

import java.util.LinkedList;
import java.util.List;

public class SearchActivity extends BaseActivity {



   private List<Fragment> fragments = new LinkedList<>();
    Unbinder bind;

    final int CONTAINERFRAGMENTAID = R.id.fragment_container ;
    private void init() {

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_activities);
        bind =  ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ActivityUtile.showFragment(SearchFragment.newInstance("search"), this,fragments,CONTAINERFRAGMENTAID );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    public List<Fragment> getFragments() {
        return fragments;
    }
    public int getCONTAINERFRAGMENTAID() {
        return CONTAINERFRAGMENTAID;
    }


}
