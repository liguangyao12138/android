package com.example.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.datastorage.sharedPreferences.SharedPreferencesActivity;
import com.example.datastorage.sqlite.SqliteActivity;

/**
 * @author liguangyao
 * @date 2021/01/19
 * @description： 数据存储的主界面
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_main_sharedPreferences).setOnClickListener(this);
        findViewById(R.id.btn_main_sql).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn_main_sharedPreferences){
            Intent intent = new Intent(this, SharedPreferencesActivity.class);
            startActivity(intent);

        }else if(v.getId() == R.id.btn_main_sql){
            Intent intent_sql = new Intent(this , SqliteActivity.class);
            startActivity(intent_sql);
        }
    }
}