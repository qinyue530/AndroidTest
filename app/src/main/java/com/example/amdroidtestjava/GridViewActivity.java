package com.example.amdroidtestjava;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.amdroidtestjava.adapter.MyBaseAdapter;
import com.example.amdroidtestjava.adapter.MyGridBaseAdapter;
import com.example.amdroidtestjava.enity.MyBaseEntity;
import com.example.amdroidtestjava.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridViewActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private static final String[] item = new String[]{"AAA","BBB","CCC","DDD","EEE","FFF","GGG","HHH","III","JJJ","KKK","LLL"};
    GridView myGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_grid_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.myGridViewActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        initViewListener();
        //SimpleAdapter
        List<MyBaseEntity> myBaseEntityList =new ArrayList<>();
        for(String str : item){

            MyBaseEntity myBaseEntity = new MyBaseEntity();
            myBaseEntity.setIcon(R.drawable.tigger);
            myBaseEntity.setName(str);
            myBaseEntity.setDesc("获取的是:"+str);
            myBaseEntityList.add(myBaseEntity);
        }
        //GridView
        MyGridBaseAdapter myGridBaseAdapter = new MyGridBaseAdapter(this,myBaseEntityList);
        myGridView.setAdapter(myGridBaseAdapter);

    }

    private void initViewListener() {
        myGridView.setOnItemClickListener(this);
    }

    private void initView() {
        myGridView = findViewById(R.id.myGridView);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Utils.toastShow(this,"您选择的是"+ item[i]);
    }
}