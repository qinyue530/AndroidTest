package com.example.amdroidtestjava;

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

    private int[] launchImageList  ={R.drawable.open1,R.drawable.open2,R.drawable.open3,R.drawable.open4,R.drawable.open5,R.drawable.open6};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }

    private void initView() {
        launchViewPiger = findViewById(R.id.launchViewPiger);
    }

    private void initViewOnListener() {

    }
}