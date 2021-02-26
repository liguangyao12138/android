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

        findViewById(R.id.btn_sql_createDel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_sql_createDel:
                Intent intent_sql_create = new Intent(this , SqlCreateDelActivity.class);
                startActivity(intent_sql_create);
                break;
        }
    }
}