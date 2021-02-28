package com.example.datastorage.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.datastorage.R;

public class SqliteActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        findViewById(R.id.btn_sql_basicUsage).setOnClickListener(this);
        findViewById(R.id.btn_sqliteOpenHelper).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_sql_basicUsage:
                Intent intent_sql_basicUsage = new Intent(this , SqliteBasicActivity.class);
                startActivity(intent_sql_basicUsage);
                break;

            case R.id.btn_sqliteOpenHelper:
                Intent intent_sqliteOpenHelper = new Intent(this , OpenHelperActivity.class);
                startActivity(intent_sqliteOpenHelper);
                break;
        }
    }
}