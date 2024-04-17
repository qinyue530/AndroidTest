package com.example.amdroidtestjava.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class Utils {

    /**
     * 根据手机分辨率从dp单位转为px
     * @param context
     * @param dpValue
     * @return
     */
    public static int dp2px(Context context ,float dpValue){
        //获取当前手机的像素密度(1个dp对应几个px )
        float scale =context.getResources().getDisplayMetrics().density;
        //例如 2*0.9 = 1.8 若取整则为1 , 所以加0.5
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 关闭指定输入框触发弹出的输入法
     * @param activity
     * @param view
     */
    public static void hideOneInputMethod(Activity activity , View view){
        //从系统中获取输入法管理器
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        //关闭屏幕上方的输入法
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    /**
     * 弹出提示信息
     * @param context
     * @param desc
     */
    public static void toastShow(Context context , String desc){
        Toast.makeText(context,desc,Toast.LENGTH_SHORT).show();
    }
}
