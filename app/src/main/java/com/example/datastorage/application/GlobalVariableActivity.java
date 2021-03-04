package com.example.datastorage.application;

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
import com.example.datastorage.util.DateUtil;

import java.util.Map;

public class GlobalVariableActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_globalVariable_name;
    private EditText et_globalVariable_age;
    private EditText et_globalVariable_height;
    private EditText et_globalVariable_weight;

    private boolean bMarried = false;

    private TextView tv_application_readContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_variable);

        bindView();

    }

    private void bindView() {

        et_globalVariable_name = findViewById(R.id.et_globalVariable_name);
        et_globalVariable_age = findViewById(R.id.et_globalVariable_age);
        et_globalVariable_height = findViewById(R.id.et_globalVariable_height);
        et_globalVariable_weight = findViewById(R.id.et_globalVariable_weight);


        findViewById(R.id.btn_globalVariable_save).setOnClickListener(this);

        initTypeSpinner();

        tv_application_readContent = findViewById(R.id.tv_application_readContent);

        readAppMemory();
    }

    // 初始化婚姻状况的下拉框
    private void initTypeSpinner() {
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, typeArray);
        typeAdapter.setDropDownViewResource(R.layout.item_dropdown);
        Spinner sp_married = findViewById(R.id.sp_globalVariable_married);
        sp_married.setPrompt("请选择婚姻状况");
        sp_married.setAdapter(typeAdapter);
        sp_married.setSelection(0);
        sp_married.setOnItemSelectedListener(new TypeSelectedListener());
    }

    private String[] typeArray = {"未婚", "已婚"};
    class TypeSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            bMarried = (arg2 == 0) ? false : true;
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn_globalVariable_save){

            String name = et_globalVariable_name.getText().toString();
            String age = et_globalVariable_age.getText().toString();
            String height = et_globalVariable_height.getText().toString();
            String weight = et_globalVariable_weight.getText().toString();
            if (TextUtils.isEmpty(name)) {
                showToast("请先填写姓名");
                return;
            } else if (TextUtils.isEmpty(age)) {
                showToast("请先填写年龄");
                return;
            } else if (TextUtils.isEmpty(height)) {
                showToast("请先填写身高");
                return;
            } else if (TextUtils.isEmpty(weight)) {
                showToast("请先填写体重");
                return;
            }
            // 获取当前应用的Application实例
            MainApplication app = MainApplication.getInstance();
            // 以下直接修改Application实例中保存的映射全局变量
            app.mInfoMap.put("name", name);
            app.mInfoMap.put("age", age);
            app.mInfoMap.put("height", height);
            app.mInfoMap.put("weight", weight);
            app.mInfoMap.put("married", typeArray[!bMarried ? 0 : 1]);
            app.mInfoMap.put("update_time", DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss"));
            showToast("数据已写入全局内存");
        }

    }

    private void showToast(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    // 读取全局内存中保存的变量信息
    private void readAppMemory() {
        String desc = "全局内存中保存的信息如下：";
        // 获取当前应用的Application实例
        MainApplication app = MainApplication.getInstance();
        // 获取Application实例中保存的映射全局变量
        Map<String, String> mapParam = app.mInfoMap;
        // 遍历映射全局变量内部的键值对信息
        for (Map.Entry<String, String> item_map : mapParam.entrySet()) {
            desc = String.format("%s\n　%s的取值为%s",
                    desc, item_map.getKey(), item_map.getValue());
        }
        if (mapParam.size() <= 0) {
            desc = "全局内存中保存的信息为空";
        }
        tv_application_readContent.setText(desc);
    }
}