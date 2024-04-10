package com.example.amdroidtestjava;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LinearActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_linear);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.linear), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //复选框
        CheckBox checkBox = findViewById(R.id.checkBoxTest);
        checkBox.setOnCheckedChangeListener(this);
        //switch开关
        Switch switchTest = findViewById(R.id.switchTest);
        switchTest.setOnCheckedChangeListener(this);
        //单选框
        RadioGroup radioGroup = findViewById(R.id.radioGroupTest);
        radioGroup.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        String str = String.format("您%s了这个checkBox" , b ? "勾选" :"取消勾选");
        compoundButton.setText(str);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        TextView textView = findViewById(R.id.radioGroupText);
        int getid = radioGroup.getCheckedRadioButtonId();
        String str = "";
        if(i == getid){
            str = "代码获取到了";
        }
        if(i==R.id.woman){
            textView.setText(str +"恭喜你是个女人");
        }else if(i==R.id.man){
            textView.setText(str +"恭喜你是个男人");
        }
    }
}