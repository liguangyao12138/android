package com.example.myapplication.simpleControl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

public class SimpleControlActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_control);

        initView();
    }

    private void initView() {

        findViewById(R.id.btn_simple_control_textView).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_simple_control_textView:
                Intent intent_textView = new Intent(this, TextViewActivity.class);
                startActivity(intent_textView);
                break;
        }

    }
}