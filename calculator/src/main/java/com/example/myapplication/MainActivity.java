package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();
    }

    private void bindView() {

        tv_result = findViewById(R.id.tv_result);
        // 设置tv_result内部文本的移动方式为滚动形式
        tv_result.setMovementMethod(new ScrollingMovementMethod());

        // “取消”按钮
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        //"除号"按钮
        findViewById(R.id.btn_divide).setOnClickListener(this);
        //“乘号按钮”
        findViewById(R.id.btn_multiply).setOnClickListener(this);
        //“清除”按钮
        findViewById(R.id.btn_clear).setOnClickListener(this);
        //
        findViewById(R.id.btn_seven).setOnClickListener(this);
        //
        findViewById(R.id.btn_eight).setOnClickListener(this);
        //
        findViewById(R.id.btn_nine).setOnClickListener(this);
        //
        findViewById(R.id.btn_five).setOnClickListener(this);
        //
        findViewById(R.id.btn_four).setOnClickListener(this);
        //
        findViewById(R.id.btn_six).setOnClickListener(this);
        //
        findViewById(R.id.btn_one).setOnClickListener(this);
        //
        findViewById(R.id.btn_two).setOnClickListener(this);
        //
        findViewById(R.id.btn_three).setOnClickListener(this);
        //
        findViewById(R.id.btn_plus).setOnClickListener(this);
        //
        findViewById(R.id.btn_minus).setOnClickListener(this);
        //
        findViewById(R.id.btn_zero).setOnClickListener(this);
        //
        findViewById(R.id.btn_dot).setOnClickListener(this);
        //
        findViewById(R.id.btn_equal).setOnClickListener(this);
        //
        findViewById(R.id.ib_sqrt).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_cancel:

                break;
        }
    }
}