package com.example.advancedcontrols.listTypeView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.advancedcontrols.R;
import com.example.advancedcontrols.adapter.PlanetListAdapter;
import com.example.advancedcontrols.bean.Planet;

import java.util.ArrayList;

/**
 * @author liguangyao
 * @date 2021-03-22
 * @description： 基本适配器 BaseAdapter
 */
public class BaseAdapterActivity extends AppCompatActivity {

    private ArrayList<Planet> planetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter);

        initPlanetSpinner();
    }

    private void initPlanetSpinner() {

        planetList = Planet.getDefaultList();

        PlanetListAdapter adapter = new PlanetListAdapter(this , planetList);

        Spinner sp = findViewById(R.id.sp_baseAdapter_planet);

        sp.setPrompt("请选择行星");

        sp.setAdapter(adapter);

        sp.setSelection(0);

        sp.setOnItemSelectedListener(new MySelectListener());
    }

    private class MySelectListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(BaseAdapterActivity.this, "你选择的是"+planetList.get(position).name, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}