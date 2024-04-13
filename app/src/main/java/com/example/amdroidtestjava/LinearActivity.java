package com.example.amdroidtestjava;

import android.health.connect.datatypes.units.Length;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LinearActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener, View.OnFocusChangeListener {


    EditText phoneNo;
    EditText password;

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

        phoneNo = findViewById(R.id.phoneNo);
        //文本输入框 先触发焦点事件,再触发点击事件, 如果只使用点击事件 需要点两次才能触发. 所以要使用焦点事件
        phoneNo.setOnFocusChangeListener(this);
        password = findViewById(R.id.password);
        password.setOnFocusChangeListener(this);

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

    @Override
    public void onFocusChange(View view, boolean b) {
        if(!b){
            if(R.id.phoneNo == view.getId()){
                if(null == phoneNo.getText()||phoneNo.getText().length() < 11){
                    //弹出提示
                    Toast.makeText(this,"请输入11位手机号码",Toast.LENGTH_SHORT).show();
                }
            }
        }


    }
}