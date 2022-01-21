package com.crystallightghot.frscommunityclient.view.activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.crystallightghot.frscommunityclient.R;
import com.crystallightghot.frscommunityclient.view.util.ActivityUtile;

public class OnlyOneAbstractFragmentShowedActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_one_fragment_showed);
    }

    public  void  setFragmentShowed(Fragment fragment, boolean addToStackBack){
        ActivityUtile.showFragment(fragment,this,addToStackBack);
    }

    @Override
    void setDefaultFragment() {
    }
}