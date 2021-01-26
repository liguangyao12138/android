package com.example.myapplication.simpleLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.screen.ScreenActivity;

public class SimpleLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_layout);

        initView();

    }

    private void initView(){

        findViewById(R.id.btn_simple_layout_basicView).setOnClickListener(this);
        findViewById(R.id.btn_linearLayout).setOnClickListener(this);
        findViewById(R.id.btn_scrollView).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_simple_layout_basicView:
                Intent intent_simple_layout_basicView = new Intent(this, BasicViewActivity.class);
                startActivity(intent_simple_layout_basicView);
                break;

            case R.id.btn_linearLayout:
                Intent intent_linearLayout = new Intent(this, LinearLayoutActivity.class);
                startActivity(intent_linearLayout);
                break;

            case R.id.btn_scrollView:
                Intent intent_scrollView = new Intent(this, ScrollViewActivity.class);
                startActivity(intent_scrollView);
                break;
        }
    }
}