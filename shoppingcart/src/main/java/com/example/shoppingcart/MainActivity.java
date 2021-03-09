package com.example.shoppingcart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingcart.bean.CartInfo;
import com.example.shoppingcart.bean.GoodsInfo;
import com.example.shoppingcart.database.CartDBHelper;
import com.example.shoppingcart.database.GoodsDBHelper;
import com.example.shoppingcart.util.SharedUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "shoppingcart";

    private ImageView iv_title_menu;
    private TextView tv_title_count;
    private TextView tv_shoppingCart_total_price;

    private LinearLayout linear_shoppingCart_content;
    private LinearLayout linear_shoppingCart_cart;
    private LinearLayout linear_shoppingCart_empty;

    //购物车中的商品数量
    private int mCount;

    //声明一个商品数据库的帮助器对象
    private GoodsDBHelper mGoodsHelper;
    //声明一个购物车数据库的帮助器对象
    private CartDBHelper mCartHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        bindView();
    }

    private void bindView() {

        iv_title_menu = findViewById(R.id.iv_title_menu);
        TextView tv_title_content = findViewById(R.id.tv_title_content);
        tv_title_count = findViewById(R.id.tv_title_count);
        tv_shoppingCart_total_price = findViewById(R.id.tv_shoppingCart_total_price);
        linear_shoppingCart_content = findViewById(R.id.linear_shoppingCart_content);
        linear_shoppingCart_cart = findViewById(R.id.linear_shoppingCart_cart);
        linear_shoppingCart_empty = findViewById(R.id.linear_shoppingCart_empty);

        iv_title_menu.setOnClickListener(this);
        findViewById(R.id.btn_shoppingCart_shopping_channel).setOnClickListener(this);
        findViewById(R.id.btn_shoppingCart_settle).setOnClickListener(this);
        iv_title_menu.setVisibility(View.VISIBLE);
        tv_title_content.setText("购物车");

    }

    //显示购物车图标中的商品数量
    private void showCount(int count){

        mCount = count;
        tv_title_count.setText("" + mCount);
        if(mCount == 0){
            linear_shoppingCart_content.setVisibility(View.GONE);
            linear_shoppingCart_cart.removeAllViews();
            linear_shoppingCart_empty.setVisibility(View.VISIBLE);
        }else {
            linear_shoppingCart_content.setVisibility(View.VISIBLE);
            linear_shoppingCart_empty.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View v) {

        //点击菜单按钮
        if(v.getId() == R.id.iv_title_menu){
            openOptionsMenu();

            //点击了“商场按钮”
        }else if(v.getId() == R.id.btn_shoppingCart_shopping_channel){

            Intent intent = new Intent(this , ShoppingChannelActivity.class);
            startActivity(intent);

            //点击了结算按钮“”
        }else if(v.getId() == R.id.btn_shoppingCart_settle){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("结算商品");
            builder.setMessage("客官抱歉，支付功能尚未开通，请下次再来");
            builder.setPositiveButton("我知道了",null);
            builder.create().show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //从menu_cart.xml 中构建菜单界面布局
        getMenuInflater().inflate(R.menu.menu_cart , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        //点击了菜单项"去商场购物"
        if(id == R.id.menu_shopping){

            Intent intent = new Intent(this , ShoppingChannelActivity.class);
            startActivity(intent);

        }else if(id == R.id.menu_clear){

            mCartHelper.deleteAll();
            linear_shoppingCart_cart.removeAllViews();
            SharedUtil.getIntance(this).writeShared("count","0");
            showCount(0);
            mCartGoods.clear();
            mGoodsMap.clear();
            Toast.makeText(this, "购物车已清空", Toast.LENGTH_SHORT).show();

        }else if(id == R.id.menu_return){

            finish();
        }
        return true;
    }

    // 声明一个根据视图编号查找商品信息的映射
    private HashMap<Integer, CartInfo> mCartGoods = new HashMap<Integer, CartInfo>();

    // 声明一个根据商品编号查找商品信息的映射
    private HashMap<Long, GoodsInfo> mGoodsMap = new HashMap<Long, GoodsInfo>();

    // 声明一个触发上下文菜单的视图对象
    private View mContextView;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // 保存该商品行的视图，以便删除商品时一块从列表移除该行
        mContextView = v;
        // 从menu_goods.xml中构建菜单界面布局
        getMenuInflater().inflate(R.menu.menu_goods , menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        CartInfo info = mCartGoods.get(mContextView.getId());
        int id = item.getItemId();

        // 点击了菜单项“查看商品详情”
        if(id == R.id.menu_detail){
            // 跳转到查看商品详情页面
            goDetail(info.goods_id);

            // 点击了菜单项“从购物车删除”
        }else if(id == R.id.menu_delete){

            // 从购物车删除商品的数据库操作
            long goods_id = info.goods_id;
            mCartHelper.delete("goods_id=" + goods_id);

            // 从购物车列表中删除该商品行
            linear_shoppingCart_cart.removeView(mContextView);

            // 更新购物车中的商品数量
            int left_count = mCount - info.count;
            for (int i = 0; i < mCartArray.size(); i++) {
                if(goods_id == mCartArray.get(i).goods_id){
                    left_count = mCount - mCartArray.get(i).count;
                    mCartArray.remove(i);
                    break;
                }
            }
            // 把最新的商品数量写入共享参数
            SharedUtil.getIntance(this).writeShared("count" , "" + left_count);
            // 显示最新的商品数量
            showCount(left_count);
            Toast.makeText(this, "已从购物车删除"+mGoodsMap.get(goods_id).name, Toast.LENGTH_SHORT).show();
            mGoodsMap.remove(goods_id);
            refreshTotalPrice();

        }

        return true;
    }

    //跳转到商品详情页面
    private void goDetail(long rowid) {

        Intent intent = new Intent(this , ShoppingDetailActivity.class);
        intent.putExtra("goods_id" , rowid);
        startActivity(intent);
    }

    // 声明一个购物车中的商品信息队列
    private ArrayList<CartInfo> mCartArray = new ArrayList<CartInfo>();

    // 重新计算购物车中的商品总金额
    private void refreshTotalPrice() {
        int total_price = 0;
        for (CartInfo info : mCartArray) {
            GoodsInfo goods = mGoodsMap.get(info.goods_id);
            total_price += goods.price * info.count;
        }
        tv_shoppingCart_total_price.setText("" + total_price);
    }

}