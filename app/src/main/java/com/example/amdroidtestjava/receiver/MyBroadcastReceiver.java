package com.example.amdroidtestjava.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.VibrationEffect;
import android.os.Vibrator;

import com.example.amdroidtestjava.util.Utils;

public class MyBroadcastReceiver extends BroadcastReceiver {

    public static String MBR_ACTION = "com.example.amdroidtestjava.ViewPagerActivity";
    public static String MBR_ORDER_ACTION = "OrderBroadcastReceiver";
    //一旦接收到标准广播，马上触发接收器的onRereive方法
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null && intent.getAction().equals(MBR_ACTION)){
            Utils.toastShow(context,"接收到了一个广播");
            //从系统服务中获取震动管理器
            //红米手机震动不生效, 打开系统触感后生效
            Vibrator vb = (Vibrator) context.getSystemService (Context. VIBRATOR_SERVICE) ;
            long[] pattern = {0, 2000}; // 强度为0的2000毫秒
            vb.vibrate(VibrationEffect.createWaveform(pattern, -1));
        }
    }
}
