package com.example.amdroidtestjava;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.amdroidtestjava.adapter.MyBaseAdapter;
import com.example.amdroidtestjava.enity.MyBaseEntity;
import com.example.amdroidtestjava.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeniorActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private static final String[] item = new String[]{"AAA","BBB","CCC"};

    Spinner baseSpinner ;

    Spinner spinnerDialog;

    Spinner simpleAdapterSpinner;

    Spinner baseAdapterSpinner;

    ListView myListView;

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
        //
        spinnerDialog.setPrompt("请选择");
        spinnerDialog.setSelection(0);
        spinnerDialog.setAdapter(arrayAdapter);
        //SimpleAdapter
        List<MyBaseEntity> myBaseEntityList =new ArrayList<>();
        List<Map<String,Object>> mapList = new ArrayList<>();
        for(String str : item){
            Map<String , Object> map = new HashMap<>();
            map.put("icon", R.drawable.tigger);
            map.put("name",str);
            mapList.add(map);

            MyBaseEntity myBaseEntity = new MyBaseEntity();
            myBaseEntity.setIcon(R.drawable.tigger);
            myBaseEntity.setName(str);
            myBaseEntity.setDesc("获取的是:"+str);
            myBaseEntityList.add(myBaseEntity);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,mapList,R.layout.item_simple
                ,new String[]{"icon","name"},new int[]{R.id.icon,R.id.name});
        simpleAdapterSpinner.setAdapter(simpleAdapter);
        simpleAdapterSpinner.setSelection(0);
        simpleAdapterSpinner.setPrompt("请选择");
        //baseAdapter
        MyBaseAdapter myBaseAdapter = new MyBaseAdapter(this,myBaseEntityList);
        baseAdapterSpinner.setAdapter(myBaseAdapter);
        baseAdapterSpinner.setSelection(0);
        baseAdapterSpinner.setPrompt("请选择");

        myListView.setAdapter(myBaseAdapter);

    }

    private void initViewListener() {
        baseSpinner.setOnItemSelectedListener(this);
        myListView.setOnItemClickListener(this);
    }

    private void initView() {
        baseSpinner = findViewById(R.id.baseSpinner);
        spinnerDialog = findViewById(R.id.spinnerDialog);
        simpleAdapterSpinner = findViewById(R.id.simpleAdapterSpinner);
        baseAdapterSpinner = findViewById(R.id.baseAdapterSpinner);
        myListView = findViewById(R.id.myListView);
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