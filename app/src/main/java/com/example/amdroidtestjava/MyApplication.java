package com.example.amdroidtestjava;

import android.app.Application;

import java.util.HashMap;

/**
 * 全局变量 继承 Application
 */
public class MyApplication extends Application {

    private static MyApplication myApplication = new MyApplication();

    public HashMap<String ,String> infoMap = new HashMap<>();

    public static MyApplication getInstance(){
         return  myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }
}
