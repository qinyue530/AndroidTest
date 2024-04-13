package com.example.amdroidtestjava;

import android.content.DialogInterface;
import android.health.connect.datatypes.units.Length;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.amdroidtestjava.util.Utils;

public class LinearActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener, View.OnFocusChangeListener, View.OnClickListener {


    EditText phoneNo;
    EditText password;
    TextView getResult;

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
        password = findViewById(R.id.password);
        //文本输入框 先触发焦点事件,再触发点击事件, 如果只使用点击事件 需要点两次才能触发. 所以要使用焦点事件
        phoneNo.setOnFocusChangeListener(this);
        //文本变化监听
        //学习过程中发现无法在文本变化监听中获取是哪个view Id触发的,多个控件事件无法区分,所以采用这种方式
        phoneNo.addTextChangedListener(new HideTextWatcher(phoneNo,11));
        password.addTextChangedListener(new HideTextWatcher(password,6));
        //弹出对话框
        Button alertDialog = findViewById(R.id.alertDialog);
        alertDialog.setOnClickListener(this);

        getResult = findViewById(R.id.getResult);


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

    private class HideTextWatcher implements TextWatcher {

        private EditText editText;

        private int maxLength;

        public HideTextWatcher(EditText editText, int i) {
            this.editText = editText;
            this.maxLength = i;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //文本改变之前触发
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //文本改变过程中触发
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //文本改变后触发
            if(editable.length() == maxLength){
                //隐藏软键盘
                Utils.hideOneInputMethod(LinearActivity.this,editText);
            }
        }
    }

    @Override
    public void onClick(View view) {
        //创建提醒对话的构造器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //标题文本
        builder.setTitle("尊敬的用户");
        //内容文本
        builder.setMessage("确认登陆吗");
        //肯定的按钮文本及监听器
        builder.setPositiveButton("登陆", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getResult.setText("登陆成功");
            }
        });
        //否定的按钮文本及监听器
        builder.setNegativeButton("再想想",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getResult.setText("我再想想是否登陆");
            }
        });
        //中性的按钮文本及监听器 不常用
        builder.setNeutralButton("中性选择",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getResult.setText("中性选择");
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}