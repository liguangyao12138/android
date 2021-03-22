package com.example.advancedcontrols.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.advancedcontrols.bean.GoodsInfo;

import java.util.ArrayList;

/**
 * @ProjectName :
 * @Author : liguangyao
 * @Time : 2021/3/23
 * @Description : ViewPager  适配器
 */
public class ImagePagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<ImageView> mViewList = new ArrayList<ImageView>();
    private ArrayList<GoodsInfo> mGoodsList = new ArrayList<GoodsInfo>();

    public ImagePagerAdapter(Context context ,  ArrayList<GoodsInfo> goodsList) {
        mContext = context;
        mGoodsList = goodsList;

        // 给每个商品分配一个专用的图像视图
        for (int i = 0; i < mGoodsList.size(); i++) {
            ImageView view = new ImageView(mContext);
            view.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            view.setImageResource(mGoodsList.get(i).pic);
            view.setScaleType(ImageView.ScaleType.FIT_CENTER);
            // 把该商品的图像视图添加到图像视图队列
            mViewList.add(view);
        }
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViewList.get(position));
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return mViewList.get(position);
    }
}
