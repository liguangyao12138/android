package com.example.intermediatecontrol.editBox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.intermediatecontrol.R;

public class EditBoxActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_box);

        findViewById(R.id.btn_editBox_editText).setOnClickListener(this);
        findViewById(R.id.btn_autoTv).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn_editBox_editText){
            Intent intent = new Intent(this, EditTextActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.btn_autoTv){
            Intent intent = new Intent(this, AutoCompleteTextViewActivity.class);
            startActivity(intent);
        }
    }
}