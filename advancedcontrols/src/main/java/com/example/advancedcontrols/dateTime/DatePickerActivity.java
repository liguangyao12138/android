package com.example.advancedcontrols.dateTime;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.advancedcontrols.R;

import java.util.Calendar;

/**
 * @author liguangyao
 * @date 2021-03-22
 * @description： 5.1.1 日期选择器 DatePicker
 */
public class DatePickerActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private DatePicker dp_datePicker_date;
    private TextView tv_datePicker_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        bindView();
    }

    //初始化控件
    private void bindView() {

        findViewById(R.id.btn_datePicker_date).setOnClickListener(this);
        findViewById(R.id.btn_datePicker_ok).setOnClickListener(this);

        tv_datePicker_date = findViewById(R.id.tv_datePicker_date);
        dp_datePicker_date = findViewById(R.id.dp_datePicker_date);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_datePicker_date:

                //获取日历的一个实例，里面包含了当前的年月日
                Calendar calendar = Calendar.getInstance();
                //构建一个日期对话框，该对话框已经集成了日期选择器。
                // DatePickerDialog的第二个构造参数指定了日期监听器
                DatePickerDialog dialog = new DatePickerDialog(this,this,
                        calendar.get(Calendar.YEAR),//年份
                        calendar.get(Calendar.MONTH),//月份
                        calendar.get(Calendar.DAY_OF_MONTH) );//日子
                // 把日期对话框显示在界面上
                dialog.show();
                break;

            case R.id.btn_datePicker_ok:

                String s = String.format("你选择的日期是%d年%d月%d日",dp_datePicker_date.getYear(),
                        dp_datePicker_date.getMonth()+1,dp_datePicker_date.getDayOfMonth());
                tv_datePicker_date.setText(s);

                break;
        }

    }

    // 一旦点击日期对话框上的确定按钮，就会触发监听器的onDateSet方法
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        String s = String.format("你选择的日期是%d年%d月%d日", year , month , dayOfMonth);
        tv_datePicker_date.setText(s);
    }
}