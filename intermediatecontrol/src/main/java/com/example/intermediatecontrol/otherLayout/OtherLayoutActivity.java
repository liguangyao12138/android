package com.example.intermediatecontrol.otherLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.intermediatecontrol.R;

public class OtherLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_layout);

        bindView();
    }

    private void bindView() {

        findViewById(R.id.btn_otherLayout_relativeLayout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_otherLayout_relativeLayout:
                Intent intent_relativeLayout = new Intent(this,RelativeLayoutActivity.class);
                startActivity(intent_relativeLayout);
                break;


        }
    }
}