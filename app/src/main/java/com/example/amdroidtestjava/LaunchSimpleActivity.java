package com.example.amdroidtestjava;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.amdroidtestjava.adapter.OpenLaunchAdapter;

public class LaunchSimpleActivity extends AppCompatActivity {

    ViewPager launchViewPiger ;

    private static final String PREFS_FILE = "MyPrefsFile";
    private static final String KEY_FIRST_TIME = "firstTime";

    private int[] launchImageList  ={R.drawable.open1,R.drawable.open2,R.drawable.open3,R.drawable.open4,R.drawable.open5,R.drawable.open6};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 检查是否是第一次启动应用
        SharedPreferences sharedPref = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        boolean isFirstTime = sharedPref.getBoolean(KEY_FIRST_TIME, true);
        if (isFirstTime) {
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_launch_simple);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.myLaunchSimpleActivity), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
            initView();
            OpenLaunchAdapter openLaunchAdapter = new OpenLaunchAdapter(this,launchImageList);
            launchViewPiger.setAdapter(openLaunchAdapter);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(KEY_FIRST_TIME, false);
            editor.apply();
        } else {
            // 直接进入主界面
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


    }

    private void initView() {
        launchViewPiger = findViewById(R.id.launchViewPiger);
    }

    private void initViewOnListener() {

    }
}