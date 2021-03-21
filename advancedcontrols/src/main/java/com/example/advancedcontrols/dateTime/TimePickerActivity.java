package com.example.advancedcontrols.dateTime;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.advancedcontrols.R;

import java.util.Calendar;

/**
 * @author liguangyao
 * @date 2021-03-22
 * @description： 时间选择器界面
 */

public class TimePickerActivity extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    private TimePicker tp_timePicker_time;
    private TextView tv_timePicker_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        bindView();

    }

    //初始化控件
    private void bindView() {

        tp_timePicker_time = findViewById(R.id.tp_timePicker_time);
        tv_timePicker_time = findViewById(R.id.tv_timePicker_time);

        findViewById(R.id.btn_timePicker_time).setOnClickListener(this);
        findViewById(R.id.btn_timePicker_ok).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_timePicker_time:
                // 获取日历的一个实例，里面包含了当前的时分秒
                Calendar calendar = Calendar.getInstance();
                // 构建一个时间对话框，该对话框已经集成了时间选择器。
                // TimePickerDialog的第二个构造参数指定了时间监听器
                TimePickerDialog dialog = new TimePickerDialog(this , this,
                        calendar.get(Calendar.HOUR_OF_DAY),//小时
                        calendar.get(Calendar.MINUTE),//分钟
                        true );

                dialog.show();
                break;

            case R.id.btn_timePicker_ok:

                String s = String.format("你选择的时间是%d时%d分" , tp_timePicker_time.getCurrentHour() ,
                        tp_timePicker_time.getCurrentMinute());
                tv_timePicker_time.setText(s);

                break;
        }

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        String s = String.format("你选择的时间是%d时%d分" , hourOfDay , minute);
        tv_timePicker_time.setText(s);
    }
}