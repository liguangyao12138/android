package com.example.intermediatecontrol.specialButtons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.intermediatecontrol.R;

public class SpecialButtonActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_button);

        initializeTheControl();
    }

    //初始化控件
    private void initializeTheControl() {

        findViewById(R.id.btn_specialButton_checkBox).setOnClickListener(this);
        findViewById(R.id.btn_specialButton_switch).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        //跳转对应的页面
        switch (v.getId()){

            case R.id.btn_specialButton_checkBox:
                Intent intent_checkBox = new Intent(this,CheckBoxActivity.class);
                startActivity(intent_checkBox);
                break;

            case R.id.btn_specialButton_switch:
                Intent intent_switch = new Intent(this , SwitchActivity.class);
                startActivity(intent_switch);
                break;
        }
    }
}