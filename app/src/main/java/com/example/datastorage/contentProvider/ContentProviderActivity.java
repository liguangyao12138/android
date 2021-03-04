package com.example.datastorage.contentProvider;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datastorage.R;
import com.example.datastorage.bean.UserInfo;
import com.example.datastorage.util.DateUtil;

import java.util.ArrayList;

public class ContentProviderActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_contentProvider_name;
    private EditText et_contentProvider_age;
    private EditText et_contentProvider_height;
    private EditText et_contentProvider_weight;

    private TextView tv_contentProvider_read_user;

    private static final String TAG = "ContentProviderActivity";

    private String mUserCount = "";
    private String mUserResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        bindView();
    }

    private void bindView() {

        et_contentProvider_name = findViewById(R.id.et_contentProvider_name);
        et_contentProvider_age = findViewById(R.id.et_contentProvider_age);
        et_contentProvider_height = findViewById(R.id.et_contentProvider_height);
        et_contentProvider_weight = findViewById(R.id.et_contentProvider_weight);

        tv_contentProvider_read_user = findViewById(R.id.tv_contentProvider_read_user);
        tv_contentProvider_read_user.setOnClickListener(this);

        findViewById(R.id.btn_contentProvider_add_user).setOnClickListener(this);

        showUserInfo();
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn_contentProvider_add_user){

            UserInfo user = new UserInfo();

            String name = et_contentProvider_name.getText().toString();
            String age = et_contentProvider_age.getText().toString();
            String height = et_contentProvider_height.getText().toString();
            String weight = et_contentProvider_weight.getText().toString();

            if (TextUtils.isEmpty(name)){

                Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                return;

            }else if(TextUtils.isEmpty(age)){

                Toast.makeText(this, "年龄不能为空", Toast.LENGTH_SHORT).show();
                return;

            }else if(TextUtils.isEmpty(height)){

                Toast.makeText(this, "身高不能为空", Toast.LENGTH_SHORT).show();
                return;

            }else if(TextUtils.isEmpty(weight)){

                Toast.makeText(this, "体重不能为空", Toast.LENGTH_SHORT).show();
                return;
            }

            user.name = name.trim();
            user.age = Integer.parseInt(age.trim());
            user.height = Integer.parseInt(height.trim());
            user.weight = Float.parseFloat(weight.trim());

            addUser(getContentResolver(), user);
            showUserInfo();

        }else if(v.getId() == R.id.tv_contentProvider_read_user){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(mUserCount);
            builder.setMessage(mUserResult);
            builder.setPositiveButton("确定", null);
            builder.create().show();

        }

    }

    // 显示所有用户信息
    private void showUserInfo() {
        mUserResult = readAllUser(getContentResolver());
        String[] split = mUserResult.split("\n");
        int count = (!mUserResult.contains("\n")) ? 0 : split.length;
        mUserCount = String.format("当前共找到%d位用户信息", count);
        tv_contentProvider_read_user.setText(mUserCount);
    }

    // 添加一条用户记录
    private void addUser(ContentResolver resolver, UserInfo user) {
        ContentValues name = new ContentValues();
        name.put("name", user.name);
        name.put("age", user.age);
        name.put("height", user.height);
        name.put("weight", user.weight);
        name.put("married", false);
        name.put("update_time", DateUtil.getNowDateTime(""));
        // 通过内容解析器往指定Uri中添加用户信息
        resolver.insert(UserInfoContent.CONTENT_URI, name);
    }

    // 读取所有的用户记录
    private String readAllUser(ContentResolver resolver) {
        ArrayList<UserInfo> userArray = new ArrayList<UserInfo>();
        // 通过内容解析器从指定Uri中获取用户记录的游标
        Cursor cursor = resolver.query(UserInfoContent.CONTENT_URI, null, null, null, null);
        // 循环取出游标指向的每条用户记录
        while (cursor.moveToNext()) {
            UserInfo user = new UserInfo();
            user.name = cursor.getString(cursor.getColumnIndex(UserInfoContent.USER_NAME));
            user.age = cursor.getInt(cursor.getColumnIndex(UserInfoContent.USER_AGE));
            user.height = cursor.getInt(cursor.getColumnIndex(UserInfoContent.USER_HEIGHT));
            user.weight = cursor.getFloat(cursor.getColumnIndex(UserInfoContent.USER_WEIGHT));
            userArray.add(user); // 添加到用户信息队列
        }
        cursor.close(); // 关闭数据库游标

        String result = "";
        for (UserInfo user : userArray) {
            // 遍历用户信息队列，逐个拼接到结果字符串
            result = String.format("%s%s	年龄%d	身高%d	体重%f\n", result,
                    user.name, user.age, user.height, user.weight);
        }
        Log.d(TAG, "result=" + result);
        return result;
    }
}