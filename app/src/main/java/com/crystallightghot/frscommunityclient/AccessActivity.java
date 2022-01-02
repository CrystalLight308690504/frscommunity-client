package com.crystallightghot.frscommunityclient;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.crystallightghot.frscommunityclient.activity.HomeActivity;

public class AccessActivity extends AppCompatActivity {

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
