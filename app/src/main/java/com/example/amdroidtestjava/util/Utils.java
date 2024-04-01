package com.example.amdroidtestjava.util;

import android.content.Context;

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
}
