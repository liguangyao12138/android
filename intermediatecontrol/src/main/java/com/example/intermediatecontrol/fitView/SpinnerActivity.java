package com.example.intermediatecontrol.fitView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.intermediatecontrol.R;

public class SpinnerActivity extends AppCompatActivity {

    private String[] startArray = {"水星","金星","地球","火星","木星","土星"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        initSpinner();
    }

    private void initSpinner(){

        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(this , R.layout.item_select , startArray);

        starAdapter.setDropDownViewResource(R.layout.item_dropdown);

        Spinner sp = findViewById(R.id.spinner_dialog);

        sp.setPrompt("请选择行星");

        sp.setAdapter(starAdapter);

        sp.setSelection(0);

        sp.setOnItemSelectedListener(new MySelectListener());
    }

    class MySelectListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(SpinnerActivity.this, "你选择的是"+startArray[position], Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

    }
}