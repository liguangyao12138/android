package com.example.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.datastorage.application.ApplicationBasicActivity;
import com.example.datastorage.contentProvider.ContentActivity;
import com.example.datastorage.sdFile.SdFileActivity;
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
        findViewById(R.id.btn_main_SdFile).setOnClickListener(this);
        findViewById(R.id.btn_main_application).setOnClickListener(this);
        findViewById(R.id.btn_main_content).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn_main_sharedPreferences){
            Intent intent_sharedPreferences = new Intent(this, SharedPreferencesActivity.class);
            startActivity(intent_sharedPreferences);

        }else if(v.getId() == R.id.btn_main_sql){
            Intent intent_sql = new Intent(this , SqliteActivity.class);
            startActivity(intent_sql);

        }else if(v.getId() == R.id.btn_main_SdFile){
            Intent intent_SdFile = new Intent(this , SdFileActivity.class);
            startActivity(intent_SdFile);

        }else if(v.getId() == R.id.btn_main_application){
            Intent intent_application = new Intent(this , ApplicationBasicActivity.class);
            startActivity(intent_application);

        }else if(v.getId() == R.id.btn_main_content){
            Intent intent_content = new Intent(this , ContentActivity.class);
            startActivity(intent_content);
        }
    }
}