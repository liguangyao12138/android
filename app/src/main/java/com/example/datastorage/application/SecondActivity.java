package com.example.datastorage.application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.datastorage.R;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        findViewById(R.id.btn_backLast).setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        Log.e(TAG, "onStart()" );
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.e(TAG, "onStop()" );
        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.e(TAG, "onPause()" );
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.e(TAG, "onRestart()" );
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy()" );
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn_backLast){
            Intent intent = new Intent(); // 创建一个新意图
            intent.putExtra("life", "life"); // 把字符串参数塞给意图
            setResult(Activity.RESULT_OK, intent); // 携带意图返回前一个页面
            finish(); // 关闭当前页面
        }
    }
}