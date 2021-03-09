package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shoppingcart.database.CartDBHelper;
import com.example.shoppingcart.database.GoodsDBHelper;

public class ShoppingChannelActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_title_count;
    private LinearLayout linear_shoppingChannel_channel;

    //购物车中的商品数量
    private int mCount;

    // 声明一个商品数据库的帮助器对象
    private GoodsDBHelper mGoodsHelper;
    // 声明一个商品数据库的帮助器对象
    private CartDBHelper mCartHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_channel);

        TextView tv_title_content = findViewById(R.id.tv_title_content);
        tv_title_count = findViewById(R.id.tv_title_count);
        linear_shoppingChannel_channel = findViewById(R.id.linear_shoppingChannel_channel);

        findViewById(R.id.iv_title_cart).setOnClickListener(this);
        tv_title_content.setText("手机商场");
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.iv_title_cart){

            //跳转到购物车页面
            Intent intent = new Intent(this , MainActivity.class);
            startActivity(intent);
        }
    }
}