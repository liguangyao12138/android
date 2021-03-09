package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoppingcart.database.CartDBHelper;
import com.example.shoppingcart.database.GoodsDBHelper;
import com.example.shoppingcart.util.SharedUtil;

public class ShoppingDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_title_content;
    private TextView tv_title_count;
    private TextView tv_goods_price;
    private TextView tv_goods_desc;
    private ImageView iv_goods_pic;

    //购物车中的商品数量
    private int mCount;
    //当前商品的商品编号
    private long mGoodsId;
    //声明一个商品数据库的帮助器对象
    private GoodsDBHelper mGoodsHelper;
    //声明一个购物车数据库的帮助器对象
    private CartDBHelper mCartHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_detail);

        tv_title_content = findViewById(R.id.tv_title_content);
        tv_title_count = findViewById(R.id.tv_title_count);
        tv_goods_price = findViewById(R.id.tv_goods_price);
        tv_goods_desc = findViewById(R.id.tv_goods_desc);
        iv_goods_pic = findViewById(R.id.iv_goods_pic);

        findViewById(R.id.iv_title_cart).setOnClickListener(this);
        findViewById(R.id.btn_add_cart).setOnClickListener(this);

        // 获取共享参数保存的购物车中的商品数量
        mCount = Integer.parseInt(SharedUtil.getIntance(this).readShared("count","0"));
        tv_title_count.setText(""+mCount);
    }

    @Override
    public void onClick(View v) {

    }
}