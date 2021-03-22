package com.example.advancedcontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.advancedcontrols.dateTime.DateTimeActivity;
import com.example.advancedcontrols.listTypeView.ListTypeViewActivity;
import com.example.advancedcontrols.pageFlipView.PageFilpViewActivity;

/**
 * @author liguangyao
 * @date 2021-03-22
 * @description： 高级控件主界面
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
    }

    //初始化控件
    private void bindViews() {

        findViewById(R.id.btn_dateTimeDetail).setOnClickListener(this);
        findViewById(R.id.btn_listTypeViewDetail).setOnClickListener(this);
        findViewById(R.id.btn_pageFilpViewDetail).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_dateTimeDetail:
                Intent intent_dateTime = new Intent(this , DateTimeActivity.class);
                startActivity(intent_dateTime);
                break;

            case R.id.btn_listTypeViewDetail:
                Intent intent_listType = new Intent(this , ListTypeViewActivity.class);
                startActivity(intent_listType);
                break;

            case R.id.btn_pageFilpViewDetail:
                Intent intent_pageFilpView = new Intent(this , PageFilpViewActivity.class);
                startActivity(intent_pageFilpView);
                break;

        }
    }
}