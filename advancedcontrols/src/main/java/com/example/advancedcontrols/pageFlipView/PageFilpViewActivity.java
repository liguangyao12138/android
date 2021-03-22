package com.example.advancedcontrols.pageFlipView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.example.advancedcontrols.R;

public class PageFilpViewActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_filp_view);

        bindView();
    }

    private void bindView() {

        findViewById(R.id.btn_pageFilpView_viewPager).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_pageFilpView_viewPager:
                Intent intent_pageFilpView_viewPager = new Intent(this , ViewPagerActivity.class);
                startActivity(intent_pageFilpView_viewPager);
                break;

        }
    }
}