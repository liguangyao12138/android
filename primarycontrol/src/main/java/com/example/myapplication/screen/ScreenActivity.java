package com.example.myapplication.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.util.Utils;
/**
 * @author liguangyao
 * @date 2021-01-24
 * @description： 显示当前屏幕的宽度，高度，像素密度
 */
public class ScreenActivity extends AppCompatActivity {

    private TextView tv_screen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        tv_screen = findViewById(R.id.tv_screen);

        showScreenInfo();
    }

    // 显示当前手机的屏幕参数信息
    private void showScreenInfo(){

        //获取手机屏幕的宽度
        int width = Utils.getScreenWidth(this);
        //获取手机屏幕的高度
        int height = Utils.getScreenHeight(this);
        //获取手机屏幕的像素密度
        float density = Utils.getScreenDensity(this);

        String info = String.format("当前屏幕的宽度为%dpx , 高度是%dpx , 像素密度是%f" , width , height , density);

        tv_screen.setText(info);


    }

}