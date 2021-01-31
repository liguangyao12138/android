package com.example.myapplication.simpleControl;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

/**
 * @author liguangyao
 * @date 2021-02-01
 * @description： imagebutton的相关属性
 */
public class ImageButtonActivity extends AppCompatActivity implements View.OnClickListener {

    private Button ib_imageButton;
    private Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_button);

        bindView();
    }

    private void bindView(){

        ib_imageButton = findViewById(R.id.ib_imageButton);
        drawable = getResources().getDrawable(R.drawable.apple);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

        findViewById(R.id.btn_ib_left).setOnClickListener(this);
        findViewById(R.id.btn_ib_top).setOnClickListener(this);
        findViewById(R.id.btn_ib_right).setOnClickListener(this);
        findViewById(R.id.btn_ib_bottom).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_ib_left:
                ib_imageButton.setCompoundDrawables(drawable,null,null,null);
                break;

            case R.id.btn_ib_top:
                ib_imageButton.setCompoundDrawables(null,drawable,null,null);
                break;

            case R.id.btn_ib_right:
                ib_imageButton.setCompoundDrawables(null,null,drawable,null);
                break;

            case R.id.btn_ib_bottom:
                ib_imageButton.setCompoundDrawables(null,null,null,drawable);
                break;

        }

    }
}