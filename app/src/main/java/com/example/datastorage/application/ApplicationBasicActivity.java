package com.example.datastorage.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.datastorage.R;

public class ApplicationBasicActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_basic);

        findViewById(R.id.btn_application_lifeCycle).setOnClickListener(this);
        findViewById(R.id.btn_application_globalMemory).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn_application_lifeCycle){

            Intent intent_lifeCycle = new Intent(this , ApplicationLifeCycleActivity.class);
            startActivity(intent_lifeCycle);

        }else if (v.getId() == R.id.btn_application_globalMemory){

            Intent intent_globalMemory = new Intent(this , GlobalVariableActivity.class);
            startActivity(intent_globalMemory);

        }
    }
}