package com.example.datastorage.application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.datastorage.R;

public class ApplicationLifeCycleActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "ApplicationLifeCycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_life_cycle);

        findViewById(R.id.btn_jumpNext).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        Log.e(TAG, "onStart()" );
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.e(TAG, "onStop()" );
        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.e(TAG, "onPause()" );
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.e(TAG, "onRestart()" );
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy()" );
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn_jumpNext){
            Intent intent_jumpNext = new Intent(this , SecondActivity.class);
            startActivityForResult(intent_jumpNext , 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        String nextLife = data.getStringExtra("life");

        Log.e(TAG, "nextLife === "+nextLife );
        Log.e(TAG, "onActivityResult()");

        super.onActivityResult(requestCode, resultCode, data);
    }
}