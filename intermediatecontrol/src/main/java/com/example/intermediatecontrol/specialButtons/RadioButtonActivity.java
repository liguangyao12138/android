package com.example.intermediatecontrol.specialButtons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.intermediatecontrol.R;

public class RadioButtonActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private TextView tv_radioButton_marry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);

        tv_radioButton_marry = findViewById(R.id.tv_radioButton_marry);

        RadioGroup rg_marry = findViewById(R.id.rg_marry);
        rg_marry.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if(checkedId == R.id.rb_married){
            tv_radioButton_marry.setText("哇哦，祝你早生贵子");
        }else if(checkedId == R.id.rb_unmarried){
            tv_radioButton_marry.setText("哇哦，你的前途不可限量");
        }

    }
}