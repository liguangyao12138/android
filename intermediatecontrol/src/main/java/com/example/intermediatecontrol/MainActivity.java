package com.example.intermediatecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.intermediatecontrol.otherLayout.OtherLayoutActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();
    }

    private void bindView() {

        findViewById(R.id.btn_main_otherLayout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_main_otherLayout:
                Intent intent_otherLayout = new Intent(this, OtherLayoutActivity.class);
                startActivity(intent_otherLayout);
                break;


        }
    }
}