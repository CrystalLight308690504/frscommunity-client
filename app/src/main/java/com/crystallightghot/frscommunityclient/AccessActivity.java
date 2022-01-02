package com.crystallightghot.frscommunityclient;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.crystallightghot.frscommunityclient.contract.HomeContract;

public class AccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, HomeContract.class);
        startActivity(intent);

    }
}
