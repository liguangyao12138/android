package com.example.intermediatecontrol.otherLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.intermediatecontrol.R;
import com.example.intermediatecontrol.util.Utils;

public class FrameLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout fl_content;

    // 定义一个颜色数组
    private int[] mColorArray = {
            Color.BLACK, Color.WHITE, Color.RED, Color.YELLOW, Color.GREEN,
            Color.BLUE, Color.CYAN, Color.MAGENTA, Color.GRAY, Color.DKGRAY
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);

        fl_content = findViewById(R.id.fl_content);
        findViewById(R.id.btn_frameLayout_addView).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn_frameLayout_addView){

            int random = (int)(Math.random() * 10 % 10);
            View view = new View(this);

            // 把该视图的背景设置为随机颜色
            view.setBackgroundColor(mColorArray[random]);

            // 声明一个布局参数，其中宽度与上级持平，高度为随机高度
            LinearLayout.LayoutParams ll_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(this,(random+1)*30));

            // 给该视图设置布局参数
            view.setLayoutParams(ll_lp);

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    fl_content.removeView(view);
                    return true;
                }
            });

            // 往框架布局中添加该视图
            fl_content.addView(view);

        }
    }
}