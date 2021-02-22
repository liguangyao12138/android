package com.example.datastorage.sharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.datastorage.R;

/**
 * @author liguangyao
 * @date 2021/01/19
 * @description： SharedPreferences基本用法 写入数据和读取数据
 */

public class SpBasicActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_sp_basic_name;
    private EditText et_sp_basic_age;
    private EditText et_sp_basic_height;
    private EditText et_sp_basic_weight;

    private boolean bMarried = false;

    private SharedPreferences shared;

    private static String TAG = "SharedPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_basic);

        bindView();
        initTypeSpinner();

        shared = getSharedPreferences("share" , MODE_PRIVATE);
    }

    // 初始化控件
    private void bindView(){

        et_sp_basic_name = findViewById(R.id.et_sp_basic_name);
        et_sp_basic_age = findViewById(R.id.et_sp_basic_age);
        et_sp_basic_height = findViewById(R.id.et_sp_basic_height);
        et_sp_basic_weight = findViewById(R.id.et_sp_basic_weight);
        findViewById(R.id.btn_sp_basic_save).setOnClickListener(this);
        findViewById(R.id.btn_sp_basic_read).setOnClickListener(this);
    }

    // 初始化婚姻状况的下拉框
    private void initTypeSpinner() {
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, typeArray);
        typeAdapter.setDropDownViewResource(R.layout.item_dropdown);
        Spinner sp_married = findViewById(R.id.sp_sp_basic_married);
        sp_married.setPrompt("请选择婚姻状况");
        sp_married.setAdapter(typeAdapter);
        sp_married.setSelection(0);
        sp_married.setOnItemSelectedListener(new TypeSelectedListener());
    }

    private String[] typeArray = {"未婚", "已婚"};
    class TypeSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            bMarried = (arg2==0)?false:true;
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_sp_basic_save:
                writeSp();
                break;

            case R.id.btn_sp_basic_read:
                readSp();
                break;
        }
    }

    //写入数据
    private void writeSp(){

        SharedPreferences.Editor editor = shared.edit();

        String name = et_sp_basic_name.getText().toString();
        String age = et_sp_basic_age.getText().toString();
        String height = et_sp_basic_height.getText().toString();
        String weight = et_sp_basic_weight.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请先填写姓名", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(age)) {
            Toast.makeText(this, "请先填写年龄", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(height)) {
            Toast.makeText(this, "请先填写身高", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(weight)) {
            Toast.makeText(this, "请先填写体重", Toast.LENGTH_SHORT).show();
            return;
        }

        editor.putString("name" , name);
        editor.putInt("age" , Integer.parseInt(age));
        editor.putString("height" , height);
        editor.putString("weight" , weight);
        editor.putBoolean("married" , bMarried);

        editor.commit();
        Toast.makeText(this, "写入数据成功", Toast.LENGTH_SHORT).show();

    }

    //读取数据
    private void readSp(){

        String name = shared.getString("name" , "");
        int age = shared.getInt("age" , 0);
        Boolean married = shared.getBoolean("married" , false);
        String weight = shared.getString("weight" , "");
        String height = shared.getString("height" , "");

        Log.d(TAG, "name = "+name);
        Log.d(TAG, "age = "+age);
        Log.d(TAG, "height = "+height);
        Log.d(TAG, "weight = "+weight);
        Log.d(TAG, "married = "+married);
    }
}