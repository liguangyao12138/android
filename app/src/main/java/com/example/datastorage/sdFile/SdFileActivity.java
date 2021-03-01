package com.example.datastorage.sdFile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.datastorage.R;

public class SdFileActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sd_file);

        findViewById(R.id.btn_file_basicOperation).setOnClickListener(this);
        findViewById(R.id.btn_file_storage).setOnClickListener(this);
        findViewById(R.id.btn_file_textFile).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_file_basicOperation:
                Intent intent_file_basicOperation = new Intent(this , SdBasicOperationActivity.class);
                startActivity(intent_file_basicOperation);
                break;

            case R.id.btn_file_storage:
                Intent intent_file_storage = new Intent(this , StorageActivity.class);
                startActivity(intent_file_storage);
                break;

            case R.id.btn_file_textFile:
                Intent intent_file_textFile = new Intent(this , TextFileActivity.class);
                startActivity(intent_file_textFile);
                break;
        }

    }
}