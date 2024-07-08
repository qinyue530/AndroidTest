package com.example.amdroidtestjava.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.amdroidtestjava.util.Utils;

public class OrderAReceiver extends BroadcastReceiver {

    //一旦接收到标准广播，马上触发接收器的onRereive方法
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null && intent.getAction().equals(MyBroadcastReceiver.MBR_ORDER_ACTION)){
            Utils.toastShow(context,"接收器A接收到了一个有序广播");
        }
    }
}
