package com.example.datastorage.sdFile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import com.example.datastorage.R;

public class StorageActivity extends AppCompatActivity {

    private TextView tv_storage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        tv_storage = findViewById(R.id.tv_storage);

        // 获取系统的公共存储路径
        String publicPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        // 获取当前App的私有存储路径
        String privatePath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString();
        String desc = "系统的公共存储路径位于" + publicPath +
                "\n\n当前App的私有存储路径位于" + privatePath +
                "\n\nAndroid7.0之后默认禁止访问公共存储目录";
        tv_storage.setText(desc);
    }
}