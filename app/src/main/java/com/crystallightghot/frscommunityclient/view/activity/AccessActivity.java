package com.crystallightghot.frscommunityclient.view.activity;

import android.content.Intent;
import android.os.Bundle;
import com.crystallightghot.frscommunityclient.R;

public class AccessActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

}
