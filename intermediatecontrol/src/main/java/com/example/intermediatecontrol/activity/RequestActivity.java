package com.example.intermediatecontrol.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.intermediatecontrol.R;
import com.example.intermediatecontrol.util.DateUtil;

public class RequestActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_activity_request;
    private TextView tv_activity_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        findViewById(R.id.btn_activity_act_request).setOnClickListener(this);
        et_activity_request = findViewById(R.id.et_activity_request);
        tv_activity_request = findViewById(R.id.tv_activity_request);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_activity_act_request){
            Intent intent = new Intent();

            intent.setClass(this , ResponseActivity.class);
            intent.putExtra("request_time", DateUtil.getNowTime());
            intent.putExtra("request_content",et_activity_request.getText().toString());

            startActivityForResult(intent , 0);
        }
    }

    @SuppressLint("MissingSuperCall")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            String response_time = data.getStringExtra("response_time");

            String response_content = data.getStringExtra("response_content");

            String s = String.format("收到返回消息：\n应答时间为%s\n应答内容为%s", response_time, response_content);

            tv_activity_request.setText(s);
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    // 从后一个页面携带参数返回当前页面时触发
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // 接收返回数据
//        if (data != null) {
//            // 从意图中取出名叫response_time的字符串
//            String response_time = data.getStringExtra("response_time");
//            // 从意图中取出名叫response_content的字符串
//            String response_content = data.getStringExtra("response_content");
//            String desc = String.format("收到返回消息：\n应答时间为%s\n应答内容为%s",
//                    response_time, response_content);
//            // 把返回消息的详情显示在文本视图上
//            tv_request.setText(desc);
//        }
//    }
}