package com.example.datastorage.sharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.datastorage.R;

public class SharedPreferencesActivity extends AppCompatActivity implements View.OnClickListener {
/**
 * @author liguangyao
 * @date 2021/01/19
 * @description： 4.1共享参数SharedPreferences主页面
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        findViewById(R.id.btn_sp_basic).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_sp_basic){
            Intent intent = new Intent(this,SpBasicActivity.class);
            startActivity(intent);
        }
    }
}