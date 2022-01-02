package com.crystallightghot.frscommunityclient.activity;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;

/**
 * @author crystallight
 */
public class HomeActivity extends BaseActivity {
    @BindView(R.id.home_top_bar_background)
    TextView homeTopBarBackground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        final FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.replace(R.id.homeFrameLayout, new HomeFragment());
//        ft.addToBackStack(null);
//        ft.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

