package com.example.datastorage.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.datastorage.R;

/**
 * @author liguangyao
 * @date 2021/02/28
 * @description： SQLite的基本用法  创建和删除数据库
 */

public class SqliteBasicActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_sql_createDel;
    private String mDatabaseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_basic);

        tv_sql_createDel = findViewById(R.id.tv_sql_createDel);
        findViewById(R.id.btn_sql_create).setOnClickListener(this);
        findViewById(R.id.btn_sql_del).setOnClickListener(this);

        mDatabaseName = getFilesDir() + "/test.db";
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn_sql_create){

            SQLiteDatabase db = openOrCreateDatabase(mDatabaseName , Context.MODE_PRIVATE,null);
            String desc = String.format("数据库%s创建%s", mDatabaseName, (db!=null)?"成功":"失败");
            tv_sql_createDel.setText(desc);

        }else if(v.getId() == R.id.btn_sql_del){

            boolean result = deleteDatabase(mDatabaseName);
            String desc = String.format("数据库%s删除%s", mDatabaseName, result?"成功":"失败");
            tv_sql_createDel.setText(desc);

        }
    }
}