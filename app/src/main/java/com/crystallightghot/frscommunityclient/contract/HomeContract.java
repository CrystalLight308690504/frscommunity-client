package com.crystallightghot.frscommunityclient.contract;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.crystallightghot.frscommunityclient.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

/**
 * @author crystallight
 */
public class HomeContract extends BaseContract {

    @BindView(R.id.home_top_bar_background)
    TextView homeTopBarBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        homeTopBarBackground.setHeight(QMUIStatusBarHelper.getStatusbarHeight(this));
    }
}
