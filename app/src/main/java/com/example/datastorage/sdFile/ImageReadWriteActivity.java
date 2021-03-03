package com.example.datastorage.sdFile;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datastorage.R;
import com.example.datastorage.util.DateUtil;
import com.example.datastorage.util.FileUtil;

import java.io.File;
import java.util.ArrayList;

public class ImageReadWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_image_write_name;
    private EditText et_image_write_age;
    private EditText et_image_write_height;
    private EditText et_image_write_weight;

    private TextView tv_image_write_path;

    private LinearLayout linearWrite_info;

    private boolean bMarried = false;
    private String mPath;

    private Spinner sp_image_read_file;
    private ImageView iv_read_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_read_write);

        bindView();

    }

    private void bindView() {

        et_image_write_name = findViewById(R.id.et_image_write_name);
        et_image_write_age = findViewById(R.id.et_image_write_age);
        et_image_write_height = findViewById(R.id.et_image_write_height);
        et_image_write_weight = findViewById(R.id.et_image_write_weight);

        tv_image_write_path = findViewById(R.id.tv_image_write_path);
        linearWrite_info = findViewById(R.id.linearWrite_info);

        findViewById(R.id.btn_image_write_save).setOnClickListener(this);
        // 获取当前App的私有存储目录
        mPath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/";
        initTypeSpinner();

        sp_image_read_file = findViewById(R.id.sp_image_read_file);
        iv_read_image = findViewById(R.id.iv_read_image);
        findViewById(R.id.btn_image_read_delete);

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            refreshSpinner();
        } else {
            showToast("未发现已挂载的SD卡，请检查");
        }

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_image_write_save) {
            String name = et_image_write_name.getText().toString();
            String age = et_image_write_age.getText().toString();
            String height = et_image_write_height.getText().toString();
            String weight = et_image_write_weight.getText().toString();
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

            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                // 获取线性布局ll_info绘图缓存中的位图数据
                Bitmap bitmap = linearWrite_info.getDrawingCache();
                String file_path = mPath + DateUtil.getNowDateTime("") + ".png";
                // 把位图数据保存为图片文件
                FileUtil.saveImage(file_path, bitmap);
                // 回收位图对象
                bitmap.recycle();
                tv_image_write_path.setText("用户注册信息图片的保存路径为：\n" + file_path);
                showToast("图片已存入SD卡文件");
            } else {
                showToast("未发现已挂载的SD卡，请检查");
            }

        }else if(v.getId() == R.id.btn_image_read_delete){

            for (int i = 0; i < fileArray.length; i++) {
                String file_path = mPath + fileArray[i];
                File f = new File(file_path);
                if (!f.delete()) {
                    Log.d("TAG", "file_path=" + file_path + ", delete failed");
                }
            }
            refreshSpinner();
            showToast("已删除临时目录下的所有图片文件");

        }

    }

    // 初始化婚姻状况的下拉框
    private void initTypeSpinner() {
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, typeArray);
        typeAdapter.setDropDownViewResource(R.layout.item_dropdown);
        Spinner sp_married = findViewById(R.id.sp_image_write_married);
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

    private void showToast(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 开启线性布局ll_info的绘图缓存
        linearWrite_info.setDrawingCacheEnabled(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 关闭线性布局ll_info的绘图缓存
        linearWrite_info.setDrawingCacheEnabled(false);
    }

    private void refreshSpinner() {
        // 获得指定目录下面的所有图片文件
        ArrayList<File> fileAlllist = FileUtil.getFileList(mPath, new String[]{".png", ".jpg"});
        if (fileAlllist.size() > 0) {
            fileArray = new String[fileAlllist.size()];
            for (int i = 0; i < fileAlllist.size(); i++) {
                fileArray[i] = fileAlllist.get(i).getName();
            }
            ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
                    R.layout.item_select, fileArray);
            typeAdapter.setDropDownViewResource(R.layout.item_dropdown);
            sp_image_read_file.setPrompt("请选择图片文件");
            sp_image_read_file.setAdapter(typeAdapter);
            sp_image_read_file.setSelection(0);
            sp_image_read_file.setOnItemSelectedListener(new FileSelectedListener());
        } else {
            fileArray = null;
            fileArray = new String[1];
            fileArray[0] = "";
            ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
                    R.layout.item_select, fileArray);
            sp_image_read_file.setPrompt(null);
            sp_image_read_file.setAdapter(typeAdapter);
            sp_image_read_file.setOnItemSelectedListener(null);
            iv_read_image.setImageDrawable(null);
        }
    }

    private String[] fileArray;

    class FileSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            // 打开并显示选中的图片文件位图
            String file_path = mPath + fileArray[arg2];
            Bitmap bitmap = FileUtil.openImage(file_path);
            iv_read_image.setImageBitmap(bitmap);
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}