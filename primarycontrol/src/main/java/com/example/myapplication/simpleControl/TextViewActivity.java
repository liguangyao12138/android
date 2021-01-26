package com.example.myapplication.simpleControl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.util.DateUtil;

public class TextViewActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private Boolean isPaused = false;
    private TextView tv_marquee;

    private TextView tv_chatRoom;
    private TextView tv_chatRoom_content;
    private String[] mChatStr = {"你吃饭了吗？", "今天天气真好呀。",
            "我中奖啦！", "我们去看电影吧", "晚上干什么好呢？"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        tv_marquee = findViewById(R.id.tv_marquee);
        tv_marquee.setOnClickListener(this);

        tv_chatRoom = findViewById(R.id.tv_chatRoom);
        tv_chatRoom.setOnClickListener(this);
        tv_chatRoom.setOnLongClickListener(this);

        tv_chatRoom_content = findViewById(R.id.tv_chatRoom_content);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            //跑马灯效果
            case R.id.tv_marquee:
                isPaused  = !isPaused;
                if (isPaused){
                    tv_marquee.setFocusable(false);//不允许获得焦点
                    tv_marquee.setFocusableInTouchMode(false);//允许在触摸时获取焦点

                }else {
                    tv_marquee.setFocusable(true);
                    tv_marquee.setFocusableInTouchMode(true);
                    tv_marquee.requestFocus();//强制获取焦点让跑马灯效果运行起来

                }
                break;


            case R.id.tv_chatRoom:
                // 生成一个0到4之间的随机数
                int random  = (int) (Math.random() * 10) % 5;
                // 拼接聊天的文本内容
                String newStr = String.format("%s\n%s %s",
                        tv_chatRoom_content.getText().toString(), DateUtil.getNowTime(), mChatStr[random]);
                // 设置文本视图tv_bbs的文本内容
                tv_chatRoom_content.setText(newStr);
                break;

        }
    }

    @Override
    public boolean onLongClick(View v) {
        //长按删除聊天记录
        if (v.getId() == R.id.tv_chatRoom) {
            tv_chatRoom_content.setText("");

        }
        return false;
    }
}