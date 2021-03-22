package com.example.advancedcontrols.listTypeView;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.advancedcontrols.R;
import com.example.advancedcontrols.adapter.PlanetListAdapter;
import com.example.advancedcontrols.bean.Planet;

import java.util.ArrayList;

/**
 * @author liguangyao
 * @date 2021-03-23
 * @description： listView界面
 */
public class ListViewActivity extends AppCompatActivity {

    private final static String TAG = "ListViewActivity";
    private ListView lv_listView_planet; // 声明一个列表视图对象
    private Drawable drawable;  // 声明一个图形对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ArrayList<Planet> planetList = Planet.getDefaultList();
        // 构建一个行星队列的列表适配器
        PlanetListAdapter adapter = new PlanetListAdapter(this, planetList);
        // 从布局视图中获取名叫lv_planet的列表视图
        lv_listView_planet = findViewById(R.id.lv_listView_planet);
        // 给lv_planet设置行星列表适配器
        lv_listView_planet.setAdapter(adapter);
        // 给lv_planet设置列表项的点击监听器
        lv_listView_planet.setOnItemClickListener(adapter);
        // 给lv_planet设置列表项的长按监听器
        lv_listView_planet.setOnItemLongClickListener(adapter);
        // 从资源文件中获取分隔线的图形对象
        drawable = getResources().getDrawable(R.drawable.divider_red2);
        // 初始化分隔线下拉框
        initDividerSpinner();
    }

    private void initDividerSpinner() {

        ArrayAdapter<String> dividerAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, dividerArray);
        Spinner sp_list = findViewById(R.id.sp_listView_list);
        sp_list.setPrompt("请选择分隔线显示方式");
        sp_list.setAdapter(dividerAdapter);
        sp_list.setOnItemSelectedListener(new DividerSelectedListener());
        sp_list.setSelection(0);

    }

    private String[] dividerArray = {
            "不显示分隔线(分隔线高度为0)",
            "不显示分隔线(分隔线为null)",
            "只显示内部分隔线(先设置分隔线高度)",
            "只显示内部分隔线(后设置分隔线高度)",
            "显示底部分隔线(高度是wrap_content)",
            "显示底部分隔线(高度是match_parent)",
            "显示顶部分隔线(别瞎折腾了，显示不了)",
            "显示全部分隔线(看我用padding大法)"
    };

    class DividerSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int dividerHeight = 5;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lv_listView_planet.setDivider(drawable);  // 设置lv_planet的分隔线
            lv_listView_planet.setDividerHeight(dividerHeight);  // 设置lv_planet的分隔线高度
            lv_listView_planet.setPadding(0, 0, 0, 0);  // 设置lv_planet的四周空白
            lv_listView_planet.setBackgroundColor(Color.TRANSPARENT);  // 设置lv_planet的背景颜色
            if (position == 0) {  // 不显示分隔线(分隔线高度为0)
                lv_listView_planet.setDividerHeight(0);
            } else if (position == 1) {  // 不显示分隔线(分隔线为null)
                lv_listView_planet.setDivider(null);
                lv_listView_planet.setDividerHeight(dividerHeight);
            } else if (position == 2) {  // 只显示内部分隔线(先设置分隔线高度)
                lv_listView_planet.setDividerHeight(dividerHeight);
                lv_listView_planet.setDivider(drawable);
            } else if (position == 3) {  // 只显示内部分隔线(后设置分隔线高度)
                lv_listView_planet.setDivider(drawable);
                lv_listView_planet.setDividerHeight(dividerHeight);
            } else if (position == 4) {  // 显示底部分隔线(高度是wrap_content)
                lv_listView_planet.setFooterDividersEnabled(true);
            } else if (position == 5) {  // 显示底部分隔线(高度是match_parent)
                params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
                lv_listView_planet.setFooterDividersEnabled(true);
            } else if (position == 6) {  // 显示顶部分隔线(别瞎折腾了，显示不了)
                params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
                lv_listView_planet.setFooterDividersEnabled(true);
                lv_listView_planet.setHeaderDividersEnabled(true);
            } else if (position == 7) {  // 显示全部分隔线(看我用padding大法)
                lv_listView_planet.setDivider(null);
                lv_listView_planet.setDividerHeight(dividerHeight);
                lv_listView_planet.setPadding(0, dividerHeight, 0, dividerHeight);
                lv_listView_planet.setBackgroundDrawable(drawable);
            }
            lv_listView_planet.setLayoutParams(params);  // 设置lv_planet的布局参数
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}