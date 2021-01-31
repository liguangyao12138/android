package com.example.myapplication.simpleControl;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.util.DateUtil;

/**
 * @author liguangyao
 * @date 2021-02-01
 * @description： imageView的相关属性以及截图
 */
public class ImageViewActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private ImageView iv_imageView;

    private TextView tv_imageView_chatContent;
    private ImageView iv_imageView_screenshot;

    private String[] chatContent = {"你吃饭了吗？", "今天天气真好呀。",
            "我中奖啦！", "我们去看电影吧", "晚上干什么好呢？"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        bindView();
    }

    private void bindView(){

        iv_imageView = findViewById(R.id.iv_imageView);

        findViewById(R.id.btn_iv_center).setOnClickListener(this);
        findViewById(R.id.btn_iv_fitCenter).setOnClickListener(this);
        findViewById(R.id.btn_iv_centerCrop).setOnClickListener(this);
        findViewById(R.id.btn_iv_centerInside).setOnClickListener(this);
        findViewById(R.id.btn_iv_fitXY).setOnClickListener(this);
        findViewById(R.id.btn_iv_fitStart).setOnClickListener(this);
        findViewById(R.id.btn_iv_fitEnd).setOnClickListener(this);

        tv_imageView_chatContent = findViewById(R.id.tv_imageView_chatContent);
        iv_imageView_screenshot = findViewById(R.id.iv_imageView_screenshot);
        findViewById(R.id.btn_iv_chat).setOnClickListener(this);
        findViewById(R.id.btn_iv_screenshot).setOnClickListener(this);

        findViewById(R.id.btn_iv_chat).setOnLongClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_iv_center:
                iv_imageView.setScaleType(ImageView.ScaleType.CENTER);
                break;

            case R.id.btn_iv_fitCenter:
                iv_imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                break;

            case R.id.btn_iv_centerCrop:
                iv_imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                break;

            case R.id.btn_iv_centerInside:
                iv_imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                break;

            case R.id.btn_iv_fitXY:
                iv_imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                break;

            case R.id.btn_iv_fitStart:
                iv_imageView.setScaleType(ImageView.ScaleType.FIT_START);
                break;

            case R.id.btn_iv_fitEnd:
                iv_imageView.setScaleType(ImageView.ScaleType.FIT_END);
                break;

            case R.id.btn_iv_chat:

                int random = (int)(Math.random()*10)%5;
                String s = String.format("%s\n%s %s", tv_imageView_chatContent.getText().toString(), DateUtil.getNowTime(), chatContent[random]);
                tv_imageView_chatContent.setText(s);

                break;

            case R.id.btn_iv_screenshot:

                Bitmap bitmap = tv_imageView_chatContent.getDrawingCache();
                iv_imageView_screenshot.setImageBitmap(bitmap);
                mHandler.postDelayed(mResetCache, 200);

                break;
        }

    }

    private Handler mHandler = new Handler();
    private Runnable mResetCache = new Runnable() {
        @Override
        public void run() {
            tv_imageView_chatContent.setDrawingCacheEnabled(false);
            tv_imageView_chatContent.setDrawingCacheEnabled(true);
        }
    };

    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == R.id.btn_iv_chat){
            tv_imageView_chatContent.setText("");
        }
        return false;
    }
}