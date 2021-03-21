package com.example.advancedcontrols.dateTime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.advancedcontrols.R;
/**
 * @author liguangyao
 * @date 2021-02-22
 * @description： 5.1 日期时间控件界面
 */
public class DateTimeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);

        bindViews();
    }

    //初始化控件
    private void bindViews() {

        findViewById(R.id.btn_datePickerDetail).setOnClickListener(this);
        findViewById(R.id.btn_timePickerDetail).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            //时间选择器界面
            case R.id.btn_datePickerDetail:
                Intent intent_datePicker = new Intent(this , DatePickerActivity.class);
                startActivity(intent_datePicker);
                break;

            case R.id.btn_timePickerDetail:
                Intent intent_timePicker = new Intent(this , TimePickerActivity.class);
                startActivity(intent_timePicker);
                break;
        }
    }
}