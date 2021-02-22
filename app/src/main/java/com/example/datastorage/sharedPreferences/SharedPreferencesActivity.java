package com.example.datastorage.sharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.datastorage.R;

/**
 * @author liguangyao
 * @date 2021/01/19
 * @description： 4.1共享参数SharedPreferences主页面
 */

public class SharedPreferencesActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        findViewById(R.id.btn_sp_basic).setOnClickListener(this);
        findViewById(R.id.btn_sp_rememberPassword).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_sp_basic:
                Intent intent_sp_basic = new Intent(this , SpBasicActivity.class);
                startActivity(intent_sp_basic);
                break;

            case R.id.btn_sp_rememberPassword:
                Intent intent_sp_rememberPassword = new Intent(this , RememberPasswordActivity.class);
                startActivity(intent_sp_rememberPassword);
                break;

        }

    }
}