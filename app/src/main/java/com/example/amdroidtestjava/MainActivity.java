package com.example.amdroidtestjava;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView helloWorld = findViewById(R.id.helloWorld);
        helloWorld.setText("你好,世界!");
        //Java设置字体大小默认为sp 会随着系统设置的字体大小改变
        helloWorld.setTextSize(30);
        //设置字体颜色
        helloWorld.setTextColor(Color.WHITE);
        //xml中颜色配置 如果为6位数字 默认前两位为FF可以看见,java中默认为00透明的看不到
        helloWorld.setTextColor(0xFFFFFFFF);
        //设置背景颜色
        helloWorld.setBackgroundColor(Color.RED);
        //获取按钮
        Button button = findViewById(R.id.button);
        //按钮点击事件
        button_click(button);
        //文本框点击事件
        textView_click(helloWorld);
    }

    //textView_点击事件
    public void textView_click(TextView textView){

        //添加文本框点击事件
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //由那个页面,跳转到另一个页面
                intent.setClass(MainActivity.this,NextActivity.class);
                //开始跳转
                startActivity(intent);
            }
        });
    }

    //button 点击事件
    public void button_click(Button button){

        //添加按钮点击事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //由那个页面,跳转到另一个页面
                intent.setClass(MainActivity.this,NextActivity.class);
                //开始跳转
                startActivity(intent);
            }
        });
    }
}
