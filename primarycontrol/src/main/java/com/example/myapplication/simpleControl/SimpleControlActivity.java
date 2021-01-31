package com.example.myapplication.simpleControl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

/**
 * @author liguangyao
 * @date 2021-02-01
 * @description： 基础控件的主界面
 */
public class SimpleControlActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_control);

        initView();
    }

    private void initView() {

        findViewById(R.id.btn_simple_control_textView).setOnClickListener(this);
        findViewById(R.id.btn_simple_control_button).setOnClickListener(this);
        findViewById(R.id.btn_simple_control_imageView).setOnClickListener(this);
        findViewById(R.id.btn_simple_control_imageButton).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_simple_control_textView:
                Intent intent_textView = new Intent(this, TextViewActivity.class);
                startActivity(intent_textView);
                break;

            case R.id.btn_simple_control_button:
                Intent intent_button = new Intent(this, ButtonActivity.class);
                startActivity(intent_button);
                break;

            case R.id.btn_simple_control_imageView:
                Intent intent_imageView = new Intent(this, ImageViewActivity.class);
                startActivity(intent_imageView);
                break;

            case R.id.btn_simple_control_imageButton:
                Intent intent_imageButton = new Intent(this, ImageButtonActivity.class);
                startActivity(intent_imageButton);
                break;
        }

    }
}