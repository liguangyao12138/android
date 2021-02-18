package com.example.intermediatecontrol.specialButtons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.intermediatecontrol.R;

public class SwitchActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private Switch sw_default_status;
    private TextView tv_default_result;

    private CheckBox sw_cb_ios;
    private TextView tv_ios_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        //系统默认的
        sw_default_status = findViewById(R.id.sw_default_status);
        tv_default_result = findViewById(R.id.tv_default_result);
        sw_default_status.setOnCheckedChangeListener(this);
        refreshResult();

        //模仿IOS
        tv_ios_result = findViewById(R.id.tv_ios_result);
        sw_cb_ios = findViewById(R.id.sw_cb_ios);
        sw_cb_ios.setOnCheckedChangeListener(this);
        refreshIOSResult();
        
    }

    // 刷新仿iOS按钮的开关状态说明
    private void refreshIOSResult() {
        String result = String.format("仿iOS开关的状态是%s",
                (sw_cb_ios.isChecked()) ? "开" : "关");
        tv_ios_result.setText(result);
    }

    // 刷新Switch按钮的开关状态说明
    private void refreshResult() {
        String result = String.format("Switch按钮的状态是%s",
                (sw_default_status.isChecked()) ? "开" : "关");
        tv_default_result.setText(result);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        refreshResult();
        refreshIOSResult();
    }
}