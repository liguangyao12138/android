package com.example.intermediatecontrol.specialButtons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.intermediatecontrol.R;

public class CheckBoxActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);

        CheckBox ck_system = findViewById(R.id.ck_system);

        CheckBox ck_custom = findViewById(R.id.ck_custom);

        ck_system.setOnCheckedChangeListener(new CheckListener());

        ck_custom.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String s = String.format("您%s了这个CheckBox",isChecked?"勾选":"取消勾选");
        buttonView.setText(s);
    }

    private class CheckListener implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            String s = String.format("您勾选了控件%d, 状态为%b",buttonView.getId(),isChecked);
            Toast.makeText(CheckBoxActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }
}