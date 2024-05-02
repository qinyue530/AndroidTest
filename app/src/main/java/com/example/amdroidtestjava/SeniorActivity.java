package com.example.amdroidtestjava;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.amdroidtestjava.util.Utils;

public class SeniorActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static final String[] item = new String[]{"AAA","BBB","CCC"};

    Spinner baseSpinner ;

    Spinner spinnerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_senior);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.senior), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        initViewListener();
        //声明下拉列表的数组适配器
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.item_select,item);
        baseSpinner.setAdapter(arrayAdapter);
        //设置默认项
        baseSpinner.setSelection(0);

        spinnerDialog.setPrompt("请选择");
        spinnerDialog.setSelection(0);
        spinnerDialog.setAdapter(arrayAdapter);
    }

    private void initViewListener() {
        baseSpinner.setOnItemSelectedListener(this);
    }

    private void initView() {
        baseSpinner = findViewById(R.id.baseSpinner);
        spinnerDialog = findViewById(R.id.spinnerDialog);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Utils.toastShow(this,"您选择的是"+ item[i]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}