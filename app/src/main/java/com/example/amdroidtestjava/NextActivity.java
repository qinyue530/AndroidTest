package com.example.amdroidtestjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.amdroidtestjava.util.DateUtils;

public class NextActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.next), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViewById(R.id.backButton).setOnClickListener(this);
        findViewById(R.id.backButtonA).setOnClickListener(this);
        TextView textView = findViewById(R.id.nextHelloWorld);
        //接收数据
        Bundle bundle = getIntent().getExtras();
        String text = bundle.getString("text");
        String time = bundle.getString("time");
        textView.setText(time + " " + text);



    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("text",getString(R.string.java_get_xmlString) +":从上一个视图响应的数据");
        bundle.putString("time",DateUtils.getNowTime());
        intent.putExtras(bundle);
        //携带数据返回
        setResult(Activity.RESULT_OK,intent);
        finish();
    }


}
