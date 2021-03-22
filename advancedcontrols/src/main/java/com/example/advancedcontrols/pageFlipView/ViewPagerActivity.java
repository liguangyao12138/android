package com.example.advancedcontrols.pageFlipView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.advancedcontrols.R;
import com.example.advancedcontrols.adapter.ImagePagerAdapter;
import com.example.advancedcontrols.bean.GoodsInfo;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ArrayList<GoodsInfo> goodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        goodsList = GoodsInfo.getDefaultList();
        // 构建一个商品图片的翻页适配器
        ImagePagerAdapter adapter = new ImagePagerAdapter(this, goodsList);
        // 从布局视图中获取名叫vp_content的翻页视图
        ViewPager vp_pageFilpView_content = findViewById(R.id.vp_pageFilpView_content);
        // 给vp_content设置图片翻页适配器
        vp_pageFilpView_content.setAdapter(adapter);
        // 设置vp_content默认显示第一个页面
        vp_pageFilpView_content.setCurrentItem(0);
        // 给vp_content添加页面变化监听器
        vp_pageFilpView_content.addOnPageChangeListener(this);
    }

    // 在翻页过程中触发。该方法的三个参数取值说明为 ：第一个参数表示当前页面的序号
    // 第二个参数表示当前页面偏移的百分比，取值为0到1；第三个参数表示当前页面的偏移距离
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        Toast.makeText(this, "您翻到的手机品牌是：" + goodsList.get(position).name, Toast.LENGTH_SHORT).show();
    }

    // 翻页状态改变时触发。arg0取值说明为：0表示静止，1表示正在滑动，2表示滑动完毕
    // 在翻页过程中，状态值变化依次为：正在滑动→滑动完毕→静止
    @Override
    public void onPageScrollStateChanged(int state) {



    }
}