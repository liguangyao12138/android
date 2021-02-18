package com.example.intermediatecontrol.editBox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.intermediatecontrol.R;

public class AutoCompleteTextViewActivity extends AppCompatActivity {

    private String[] hintArray = {"第一", "第一次", "第一次写代码", "第一次领工资", "第二", "第二个"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_text_view);

        AutoCompleteTextView ac_text = findViewById(R.id.ac_text);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.item_dropdown,hintArray);

        ac_text.setAdapter(adapter);
    }
}