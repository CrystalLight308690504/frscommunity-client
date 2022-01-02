package com.crystallightghot.frscommunityclient.contract;

import android.os.Bundle;
import com.crystallightghot.frscommunityclient.R;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

/**
 * @author  crystallight
 */
public class HomeContract extends BaseContract {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
