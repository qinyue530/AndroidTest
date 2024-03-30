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
//        helloWorld.setTextColor(0xFF777777);
        //设置背景颜色
        helloWorld.setBackgroundColor(Color.RED);
        //获取x按钮
        Button button = findViewById(R.id.button);
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