package com.example.myapplication.simpleControl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

/**
 * @author liguangyao
 * @date 2021-02-01
 * @description： 按钮的点击事件 长按点击事件
 */
public class ButtonActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private Button btn_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        btn_button = findViewById(R.id.btn_button);

        btn_button.setOnClickListener(this);
        btn_button.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn_button){
            Toast.makeText(this, "你点击了按钮："+((TextView)v).getText(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onLongClick(View v) {

        if(v.getId() == R.id.btn_button){
            Toast.makeText(this, "你长按了按钮"+((TextView)v).getText(), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}