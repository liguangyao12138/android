package com.example.intermediatecontrol.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.intermediatecontrol.R;
import com.example.intermediatecontrol.util.DateUtil;

public class ResponseActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_activity_response;
    private TextView tv_activity_response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);

        findViewById(R.id.btn_activity_act_response).setOnClickListener(this);
        et_activity_response = findViewById(R.id.et_activity_response);
        tv_activity_response = findViewById(R.id.tv_activity_response);

        Bundle bundle = getIntent().getExtras();
        String request_time = bundle.getString("request_time");
        String request_content = bundle.getString("request_content");

        String s = String.format("收到请求消息：\n请求时间为%s\n 请求内容为%s",request_time,request_content);
        tv_activity_response.setText(s);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_activity_act_response){

            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("response_time", DateUtil.getNowTime());
            bundle.putString("response_content", et_activity_response.getText().toString());

            intent.putExtras(bundle);
            setResult(Activity.RESULT_OK,intent);
            finish();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}