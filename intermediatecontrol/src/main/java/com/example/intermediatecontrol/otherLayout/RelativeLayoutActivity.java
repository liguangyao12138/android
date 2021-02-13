package com.example.intermediatecontrol.otherLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.intermediatecontrol.R;
import com.example.intermediatecontrol.util.Utils;

public class RelativeLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout rl_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //普通RelativeLayout界面
        //setContentView(R.layout.activity_relative_layout);

        //动态添加视图
        setContentView(R.layout.activity_relative_addview);

        initializeTheControl();
    }

    //初始化控件
    private void initializeTheControl() {

        rl_content = findViewById(R.id.rl_content);

        findViewById(R.id.btn_relative_add_left).setOnClickListener(this);
        findViewById(R.id.btn_relative_add_above).setOnClickListener(this);
        findViewById(R.id.btn_relative_add_right).setOnClickListener(this);
        findViewById(R.id.btn_relative_add_below).setOnClickListener(this);
        findViewById(R.id.btn_relative_add_center).setOnClickListener(this);
        findViewById(R.id.btn_relative_add_parent_left).setOnClickListener(this);
        findViewById(R.id.btn_relative_add_parent_top).setOnClickListener(this);
        findViewById(R.id.btn_relative_add_parent_right).setOnClickListener(this);
        findViewById(R.id.btn_relative_add_parent_bottom).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_relative_add_left:
                addView(RelativeLayout.LEFT_OF,RelativeLayout.ALIGN_TOP,v.getId());
                break;

            case R.id.btn_relative_add_above:
                addView(RelativeLayout.ABOVE,RelativeLayout.ALIGN_LEFT,v.getId());
                break;

            case R.id.btn_relative_add_right:
                addView(RelativeLayout.RIGHT_OF,RelativeLayout.ALIGN_BOTTOM,v.getId());
                break;

            case R.id.btn_relative_add_below:
                addView(RelativeLayout.BELOW,RelativeLayout.ALIGN_PARENT_RIGHT,v.getId());
                break;

            case R.id.btn_relative_add_center:
                addView(RelativeLayout.CENTER_IN_PARENT,-1,rl_content.getId());
                break;

            case R.id.btn_relative_add_parent_left:
                addView(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.CENTER_VERTICAL,rl_content.getId());
                break;

            case R.id.btn_relative_add_parent_top:
                addView(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.CENTER_HORIZONTAL,rl_content.getId());
                break;

            case R.id.btn_relative_add_parent_right:
                addView(RelativeLayout.ALIGN_PARENT_RIGHT,-1,rl_content.getId());
                break;

            case R.id.btn_relative_add_parent_bottom:
                addView(RelativeLayout.ALIGN_PARENT_BOTTOM,-1,rl_content.getId());
                break;


        }
    }

    private void addView(int firstAlign, int secondAlign, int referId){

        View v = new View(this);

        v.setBackgroundColor(0xaa66ff66);

        RelativeLayout.LayoutParams rl_params = new RelativeLayout.LayoutParams(Utils.dip2px(this,100),Utils.dip2px(this,100));

        rl_params.addRule(firstAlign,referId);

        if(secondAlign>=0){

            rl_params.addRule(secondAlign,referId);
        }

        v.setLayoutParams(rl_params);

        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                rl_content.removeView(v);
                return true;
            }
        });

        rl_content.addView(v);

    }
}