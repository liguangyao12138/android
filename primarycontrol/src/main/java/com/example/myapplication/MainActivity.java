package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.screen.ScreenActivity;
import com.example.myapplication.simpleLayout.BasicViewActivity;

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

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_main_screenDisplay:
                Intent intent_screenDisplay = new Intent(this , ScreenActivity.class);
                startActivity(intent_screenDisplay);
                break;

            case R.id.btn_main_simpleLayout:
                Intent intent_simpleLayout = new Intent(this , BasicViewActivity.class);
                startActivity(intent_simpleLayout);
                break;
        }
    }
}