package com.example.datastorage.contentProvider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.datastorage.R;

public class ContentActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        findViewById(R.id.btn_content_contentProvider).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn_content_contentProvider){

            Intent intent_contentProvider = new Intent(this , ContentProviderActivity.class);
            startActivity(intent_contentProvider);

        }
    }
}