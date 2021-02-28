package com.example.datastorage.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datastorage.R;
import com.example.datastorage.bean.UserInfo;
import com.example.datastorage.database.UserDBHelper;
import com.example.datastorage.util.DateUtil;

import java.util.ArrayList;

public class OpenHelperActivity extends AppCompatActivity implements View.OnClickListener {

    private UserDBHelper mHelper; // 声明一个用户数据库帮助器的对象
    private EditText et_openHelperName;
    private EditText et_openHelperAge;
    private EditText et_openHelperHeight;
    private EditText et_openHelperWeight;
    private boolean bMarried = false;

    private TextView tv_openHelperSqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_helper);

        et_openHelperName = findViewById(R.id.et_openHelperName);
        et_openHelperAge = findViewById(R.id.et_openHelperAge);
        et_openHelperHeight = findViewById(R.id.et_openHelperHeight);
        et_openHelperWeight = findViewById(R.id.et_openHelperWeight);

        findViewById(R.id.btn_openHelperSave).setOnClickListener(this);

        initTypeSpinner();

        tv_openHelperSqlite = findViewById(R.id.tv_openHelperSqlite);
        findViewById(R.id.btn_openHelperDelete).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 获得数据库帮助器的实例
        mHelper = UserDBHelper.getInstance(this, 2);
        // 打开数据库帮助器的写连接
        mHelper.openWriteLink();

        readSQLite();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 关闭数据库连接
        mHelper.closeLink();
    }

    // 初始化婚姻状况的下拉框
    private void initTypeSpinner() {
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, typeArray);
        typeAdapter.setDropDownViewResource(R.layout.item_dropdown);
        Spinner sp_married = findViewById(R.id.sp_openHelperMarried);
        sp_married.setPrompt("请选择婚姻状况");
        sp_married.setAdapter(typeAdapter);
        sp_married.setSelection(0);
        sp_married.setOnItemSelectedListener(new TypeSelectedListener());
    }

    private String[] typeArray = {"未婚", "已婚"};

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_openHelperSave){
            String name = et_openHelperName.getText().toString();
            String age = et_openHelperAge.getText().toString();
            String height = et_openHelperHeight.getText().toString();
            String weight = et_openHelperWeight.getText().toString();

            if(TextUtils.isEmpty(name)){
                Toast.makeText(this, "请填写姓名", Toast.LENGTH_SHORT).show();
                return;
            }else if(TextUtils.isEmpty(age)){
                Toast.makeText(this, "请填写年龄", Toast.LENGTH_SHORT).show();
                return;
            }else if(TextUtils.isEmpty(height)){
                Toast.makeText(this, "请填写身高", Toast.LENGTH_SHORT).show();
                return;
            }else if(TextUtils.isEmpty(weight)){
                Toast.makeText(this, "请填写体重", Toast.LENGTH_SHORT).show();
                return;
            }

            // 以下声明一个用户信息对象，并填写它的各字段值
            UserInfo info = new UserInfo();
            info.name = name;
            info.age = Integer.parseInt(age);
            info.height = Long.parseLong(height);
            info.weight = Float.parseFloat(weight);
            info.married = bMarried;
            info.update_time = DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss");
            // 执行数据库帮助器的插入操作
            mHelper.insert(info);
            Toast.makeText(this, "数据已写入SQLite数据库", Toast.LENGTH_SHORT).show();
            
        }else if(v.getId()==R.id.btn_openHelperDelete){
            // 关闭数据库连接
            mHelper.closeLink();
            // 打开数据库帮助器的写连接
            mHelper.openWriteLink();
            // 删除所有记录
            mHelper.deleteAll();
            // 关闭数据库连接
            mHelper.closeLink();
            // 打开数据库帮助器的读连接
            mHelper.openReadLink();
            readSQLite();
        }

    }

    // 读取数据库中保存的所有用户记录
    private void readSQLite() {
        if (mHelper == null) {
            Toast.makeText(this, "数据库连接为空", Toast.LENGTH_SHORT).show();
            return;
        }
        // 执行数据库帮助器的查询操作
        ArrayList<UserInfo> userArray = mHelper.query("1=1");
        String desc = String.format("数据库查询到%d条记录，详情如下：", userArray.size());
        for (int i = 0; i < userArray.size(); i++) {
            UserInfo info = userArray.get(i);
            desc = String.format("%s\n第%d条记录信息如下：", desc, i + 1);
            desc = String.format("%s\n　姓名为%s", desc, info.name);
            desc = String.format("%s\n　年龄为%d", desc, info.age);
            desc = String.format("%s\n　身高为%d", desc, info.height);
            desc = String.format("%s\n　体重为%f", desc, info.weight);
            desc = String.format("%s\n　婚否为%b", desc, info.married);
            desc = String.format("%s\n　更新时间为%s", desc, info.update_time);
        }
        if (userArray.size() <= 0) {
            desc = "数据库查询到的记录为空";
        }
        tv_openHelperSqlite.setText(desc);
    }

    class TypeSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            bMarried = (arg2 == 0) ? false : true;
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}