package com.example.datastorage.sharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.datastorage.R;

/**
 * @author liguangyao
 * @date 2021/02/22
 * @description： 数据存储的记住密码界面
 */

public class RememberPasswordActivity extends AppCompatActivity  {

    private EditText et_username;
    private EditText et_password;

    private Boolean bRemember = false;

    private CheckBox ck_remember;

    private SharedPreferences sharedLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember_password);

        bindView();
        readContent();
    }

    //初始化控件
    private void bindView(){
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        ck_remember = findViewById(R.id.ck_remember);

        ck_remember.setOnCheckedChangeListener(new CheckListener());

    }

    //账号和密码的显示
    private void readContent(){

        sharedLogin = getSharedPreferences("sharedlogin" , MODE_PRIVATE);

        String name = sharedLogin.getString("username" , "");
        et_username.setText(name);

        String password = sharedLogin.getString("password" , "");
        et_password.setText(password);
    }


    private class CheckListener implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if(buttonView.getId() == R.id.ck_remember){
                bRemember = isChecked;
            }
        }

    }

    @Override
    protected void onDestroy() {
        writeContent();
        super.onDestroy();
    }

    private void writeContent(){

        if(bRemember){

            String username = et_username.getText().toString();
            String password = et_password.getText().toString();

            SharedPreferences.Editor editor = sharedLogin.edit();
            editor.putString("username" , username);
            editor.putString("password" , password);
            editor.commit();

        }else {

            SharedPreferences.Editor editor = sharedLogin.edit();
            editor.putString("username" , "");
            editor.putString("password" , "");
            editor.commit();

        }
    }

}