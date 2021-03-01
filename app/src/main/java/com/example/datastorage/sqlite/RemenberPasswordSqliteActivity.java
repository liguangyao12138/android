package com.example.datastorage.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.datastorage.R;
import com.example.datastorage.bean.UserInfo;
import com.example.datastorage.database.UserDBHelper;
import com.example.datastorage.util.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RemenberPasswordSqliteActivity extends AppCompatActivity implements View.OnFocusChangeListener, View.OnClickListener {

    private EditText et_sql_username;
    private EditText et_sql_password;
    private CheckBox ck_sql_remember;

    private Boolean bRemember = false;

    private UserDBHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remenber_password_sqlite);

        et_sql_username = findViewById(R.id.et_sql_username);
        et_sql_password = findViewById(R.id.et_sql_password);
        ck_sql_remember = findViewById(R.id.ck_sql_remember);

        ck_sql_remember.setOnCheckedChangeListener(new CheckListener());
        et_sql_password.setOnFocusChangeListener(this);

        findViewById(R.id.btn_sql_login).setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();

        //获得用户数据库帮助器的一个实例
        mHelper = UserDBHelper.getInstance(this , 2);
        //恢复页面则打开数据库链接
        mHelper.openWriteLink();
        readContent();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //暂停页面，则关闭数据库链接
        mHelper.closeLink();

    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        String phone = et_sql_username.getText().toString();
        if(v.getId() == R.id.et_sql_username){
            if(phone.length()>0&& hasFocus){
                UserInfo info = mHelper.queryByPhone(phone);
                if(info!=null){
                    et_sql_password.setText(info.password);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_sql_login){
            writeContent();
        }
    }

    private class CheckListener implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if(buttonView.getId() == R.id.ck_sql_remember){
                bRemember = isChecked;
            }
        }

    }

    private void writeContent(){

        if (bRemember){

            //创建一个用户实体类
            UserInfo info = new UserInfo();
            info.phone = et_sql_username.getText().toString();
            info.password = et_sql_password.getText().toString();
            info.update_time = DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss");
            //往用户数据库添加登录成功用户信息
            mHelper.insert(info);
            Toast.makeText(this, "添加数据成功", Toast.LENGTH_SHORT).show();
            Log.d("info", "info.phone = "+info.phone);
            Log.d("info", "info.password = "+info.password);
        }else {

            UserInfo info = new UserInfo();
            info.phone = "";
            info.password = "";
            info.update_time = DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss");
            //往用户数据库添加登录成功用户信息
            mHelper.insert(info);
            Toast.makeText(this, "未选择记住密码", Toast.LENGTH_SHORT).show();
        }
    }

    private void readContent(){

        if (mHelper == null) {
            Toast.makeText(this, "数据库连接为空", Toast.LENGTH_SHORT).show();
            return;
        }
        // 执行数据库帮助器的查询操作
        ArrayList<UserInfo> userArray = mHelper.query("1=1");
        for (int i = 0; i < userArray.size(); i++) {

            if(i>=0 && i+1<userArray.size()){

                Boolean b = compareDate(userArray.get(i).update_time , userArray.get(i+1).update_time);

                if(b){

                    UserInfo info = userArray.get(i+1);


                    String phone = info.phone;
                    et_sql_username.setText(phone);

                    String password = info.password;
                    et_sql_password.setText(password);

                }else {

                    UserInfo info = userArray.get(i);


                    String phone = info.phone;
                    et_sql_username.setText(phone);

                    String password = info.password;
                    et_sql_password.setText(password);

                }
            }


        }



    }

    //比较时间大小
    public boolean compareDate(String nowDate, String compareDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date now = df.parse(nowDate);
            Date compare = df.parse(compareDate);
            if (now.before(compare)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }



}