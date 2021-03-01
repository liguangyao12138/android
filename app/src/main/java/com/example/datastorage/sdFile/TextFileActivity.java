package com.example.datastorage.sdFile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datastorage.R;
import com.example.datastorage.util.DateUtil;
import com.example.datastorage.util.FileUtil;

import java.io.File;
import java.util.ArrayList;

public class TextFileActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_text_name;
    private EditText et_text_age;
    private EditText et_text_height;
    private EditText et_text_weight;
    private boolean bMarried = false;
    private String mPath;
    private TextView tv_text_path;

    private TextView tv_text_textContent;
    private Spinner sp_text_file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_file);

        et_text_name = findViewById(R.id.et_text_name);
        et_text_age = findViewById(R.id.et_text_age);
        et_text_height = findViewById(R.id.et_text_height);
        et_text_weight = findViewById(R.id.et_text_weight);
        tv_text_path = findViewById(R.id.tv_text_path);

        findViewById(R.id.btn_text_save).setOnClickListener(this);

        // 获取当前App的私有存储目录
        mPath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/";
        initTypeSpinner();

        tv_text_textContent = findViewById(R.id.tv_text_textContent);

        findViewById(R.id.btn_text_delete).setOnClickListener(this);
        sp_text_file = findViewById(R.id.sp_text_file);

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            refreshSpinner();
        } else {
            Toast.makeText(this, "未发现已挂载的SD卡，请检查", Toast.LENGTH_SHORT).show();
        }

    }

    private void refreshSpinner() {
        // 获得指定目录下面的所有文本文件
        ArrayList<File> fileAlllist = FileUtil.getFileList(mPath, new String[]{".txt"});
        if (fileAlllist.size() > 0) {
            fileArray = new String[fileAlllist.size()];
            for (int i = 0; i < fileAlllist.size(); i++) {
                fileArray[i] = fileAlllist.get(i).getName();
            }
            ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
                    R.layout.item_select, fileArray);
            typeAdapter.setDropDownViewResource(R.layout.item_dropdown);
            sp_text_file.setPrompt("请选择文本文件");
            sp_text_file.setAdapter(typeAdapter);
            sp_text_file.setSelection(0);
            sp_text_file.setOnItemSelectedListener(new FileSelectedListener());
        } else {
            fileArray = null;
            fileArray = new String[1];
            fileArray[0] = "";
            ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
                    R.layout.item_select, fileArray);
            sp_text_file.setPrompt(null);
            sp_text_file.setAdapter(typeAdapter);
            sp_text_file.setOnItemSelectedListener(null);
            tv_text_textContent.setText("");
        }
    }

    private String[] fileArray;

    class FileSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            // 打开并显示选中的文本文件内容
            String file_path = mPath + fileArray[arg2];
            String content = FileUtil.openText(file_path);
            tv_text_textContent.setText("文件内容如下：\n" + content);
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }



    // 初始化婚姻状况的下拉框
    private void initTypeSpinner() {
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, typeArray);
        typeAdapter.setDropDownViewResource(R.layout.item_dropdown);
        Spinner sp_married = findViewById(R.id.sp_text_married);
        sp_married.setPrompt("请选择婚姻状况");
        sp_married.setAdapter(typeAdapter);
        sp_married.setSelection(0);
        sp_married.setOnItemSelectedListener(new TypeSelectedListener());
    }

    private String[] typeArray = {"未婚", "已婚"};

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_text_save){
            String name = et_text_name.getText().toString();
            String age = et_text_age.getText().toString();
            String height = et_text_height.getText().toString();
            String weight = et_text_weight.getText().toString();
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

            String content = "";
            content = String.format("%s　姓名：%s\n", content, name);
            content = String.format("%s　年龄：%s\n", content, age);
            content = String.format("%s　身高：%scm\n", content, height);
            content = String.format("%s　体重：%skg\n", content, weight);
            content = String.format("%s　婚否：%s\n", content, typeArray[!bMarried ? 0 : 1]);
            content = String.format("%s　注册时间：%s\n", content, DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss"));
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String file_path = mPath + DateUtil.getNowDateTime("") + ".txt";
                // 把文本字符串保存为文本文件
                FileUtil.saveText(file_path, content);
                tv_text_path.setText("用户注册信息文件的保存路径为：\n" + file_path);
                Toast.makeText(this, "数据已写入SD卡文件", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "未发现已挂载的SD卡，请检查", Toast.LENGTH_SHORT).show();
            }

        }else if(v.getId() == R.id.btn_text_delete){

            for (int i = 0; i < fileArray.length; i++) {
                String file_path = mPath + fileArray[i];
                File f = new File(file_path);
                if (!f.delete()) {
                    Log.d("", "file_path=" + file_path + ", delete failed");
                }
            }
            refreshSpinner();
            Toast.makeText(this, "已删除临时目录下的所有文本文件", Toast.LENGTH_SHORT).show();
        }

    }

    class TypeSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            bMarried = (arg2 == 0) ? false : true;
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}