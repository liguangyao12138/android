package com.example.intermediatecontrol.fitView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.intermediatecontrol.R;

public class FitViewActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit_view);

        bindView();
    }

    //初始化组件
    private void bindView(){

        findViewById(R.id.btn_fitview_spinner).setOnClickListener(this);
        findViewById(R.id.btn_fitview_simpleAdapter).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_fitview_spinner:
                Intent intent_fitview = new Intent(this , SpinnerActivity.class);
                startActivity(intent_fitview);
                break;

            case R.id.btn_fitview_simpleAdapter:
                Intent intent_simpleAdapter = new Intent(this , SimpleAdapterActivity.class);
                startActivity(intent_simpleAdapter);
                break;
        }
    }
}