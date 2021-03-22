package com.example.advancedcontrols.listTypeView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.advancedcontrols.R;

/**
 * @author liguangyao
 * @date 2021-03-22
 * @description： 5.2 列表类视图界面
 */
public class ListTypeViewActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_type_view);

        bindView();
    }

    //初始化控件
    private void bindView() {

        findViewById(R.id.btn_listTypeView_baseAdapterDetail).setOnClickListener(this);
        findViewById(R.id.btn_listTypeView_listViewDetail).setOnClickListener(this);
        findViewById(R.id.btn_listTypeView_gridViewDetail).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_listTypeView_baseAdapterDetail:
                Intent intent_listTypeView_baseAdapterDetail = new Intent(this , BaseAdapterActivity.class);
                startActivity(intent_listTypeView_baseAdapterDetail);
                break;

            case R.id.btn_listTypeView_listViewDetail:
                Intent intent_listTypeView_listViewDetail = new Intent(this , ListViewActivity.class);
                startActivity(intent_listTypeView_listViewDetail);
                break;

            case R.id.btn_listTypeView_gridViewDetail:
                Intent intent_listTypeView_gridViewDetail = new Intent(this , GridViewActivity.class);
                startActivity(intent_listTypeView_gridViewDetail);
                break;

        }
    }
}