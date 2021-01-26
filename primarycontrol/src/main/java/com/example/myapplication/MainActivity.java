package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.screen.ScreenActivity;
import com.example.myapplication.simpleControl.SimpleControlActivity;
import com.example.myapplication.simpleLayout.BasicViewActivity;
import com.example.myapplication.simpleLayout.SimpleLayoutActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    //初始化控件
    private void initView() {

        findViewById(R.id.btn_main_screenDisplay).setOnClickListener(this);
        findViewById(R.id.btn_main_simpleLayout).setOnClickListener(this);
        findViewById(R.id.btn_main_simpleControl).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_main_screenDisplay:
                Intent intent_screenDisplay = new Intent(this , ScreenActivity.class);
                startActivity(intent_screenDisplay);
                break;

            case R.id.btn_main_simpleLayout:
                Intent intent_simpleLayout = new Intent(this , SimpleLayoutActivity.class);
                startActivity(intent_simpleLayout);
                break;

            case R.id.btn_main_simpleControl:
                Intent intent_simpleControl = new Intent(this, SimpleControlActivity.class);
                startActivity(intent_simpleControl);
                break;

        }
    }
}