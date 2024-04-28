package com.example.amdroidtestjava;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.amdroidtestjava.util.DateUtils;
import com.example.amdroidtestjava.util.Utils;

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> intentActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView helloWorld = findViewById(R.id.helloWorld);
        helloWorld.setText("秦跃,Android学习!");
        //Java设置字体大小默认为sp 会随着系统设置的字体大小改变
        helloWorld.setTextSize(30);
        //设置字体颜色
        helloWorld.setTextColor(Color.WHITE);
        //xml中颜色配置 如果为6位数字 默认前两位为FF可以看见,java中默认为00透明的看不到
        helloWorld.setTextColor(0xFFFFFFFF);
        //设置背景颜色
        helloWorld.setBackgroundColor(Color.RED);
        //设置背景形状
//        helloWorld.setBackgroundResource(R.drawable.shape_rect_gold);
        //获取按钮
        Button btn_next = findViewById(R.id.btn_next);
        //获取视图的参数
        ViewGroup.LayoutParams layoutParams = btn_next.getLayoutParams();
        //重新赋值视图的高度
        layoutParams.height = Utils.dp2px(this,50);
        //重新赋值视图的宽度
        layoutParams.width = Utils.dp2px(this,80);
        //重新赋值视图的参数
        btn_next.setLayoutParams(layoutParams);
        //按钮点击事件
        view_click(btn_next , new NextActivity());
        //文本框点击事件
        view_click(helloWorld,new GridActivity() );
        //线性布局
        Button linearActivityButton = findViewById(R.id.linearActivity);

        view_click(linearActivityButton,new LinearActivity());
        //相对布局
        Button relativeActivityButton = findViewById(R.id.relativeActivity);

        view_click(relativeActivityButton,new RelativeActivity());
        //网格布局
        Button gridActivityButton = findViewById(R.id.gridActivity);

        view_click(gridActivityButton,new GridActivity());
        //垂直滚动布局
        Button scrollViewActivityButton = findViewById(R.id.scrollViewActivity);

        view_click(scrollViewActivityButton,new ScrollViewActivity());
        //长按事件
        Button doClickButton = findViewById(R.id.doClick);

        view_long_click(doClickButton);
        //显示图片
        ImageView imageView = findViewById(R.id.tiggerJava);

        imageView.setImageResource(R.drawable.tigger);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        Button btn_dial = findViewById(R.id.btn_dial);

        view_click(btn_dial,null);

        Button btn_sms = findViewById(R.id.btn_sms);

        view_click(btn_sms,null);

        //获取上一个页面返回的数据
        intentActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if(o.getResultCode()== Activity.RESULT_OK){
                    Bundle bundle = o.getData().getExtras();
                    String text = bundle.getString("text");
                    String time = bundle.getString("time");
                    helloWorld.setTextSize(15);
                    helloWorld.setText(time + " " + text);
//                    helloWorld.setBackgroundResource(R.drawable.shape_oval_ose);
                }else{
                    helloWorld.setText("上一页面数据返回失败");
                }
            }
        });

        Button sharedButton = findViewById(R.id.sharedButton);

        view_click(sharedButton, new SharedActivity());

        Button roomButton = findViewById(R.id.roomButton);

        view_click(roomButton,new JetpackRoomActivity());

    }

    //view 点击事件
    public void view_click(View view , AppCompatActivity appCompatActivity){

        //添加点击事件
        view.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent();
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.btn_dial){
                    //隐视意图
                    intent.setAction(intent.ACTION_DIAL);
                    //声明一个拨号的uri
                    Uri uri = Uri.parse("tel:" + "123456");
                    intent.setData(uri);
                    startActivity(intent);
                }else if(v.getId() == R.id.btn_sms){
                    //隐视意图
                    intent.setAction(intent.ACTION_SENDTO);
                    //声明一个短信发送的目标号码uri
                    Uri uri = Uri.parse("smsto:" + "123456");
                    intent.setData(uri);
                    startActivity(intent);
                }else if(view.getId() == R.id.btn_next){
                    //像下一个界面发送数据
                    Bundle bundle = new Bundle();
                    bundle.putString("text","主视图跳转传送数据");
                    bundle.putString("time",DateUtils.getNowTime());
                    intent.putExtras(bundle);
                    intent.setClass(MainActivity.this,appCompatActivity.getClass());
                    intentActivityResultLauncher.launch(intent);
                }else{
                    //显视意图
                    //由那个页面,跳转到另一个页面
                    //1.在intent构造函数中指定
                    //Intent intent = new Intent(MainActivity.this,appCompatActivity.getClass());
                    //2.调用setClass方法
                    //Intent intent = new Intent();
                    //intent.setClass(MainActivity.this,appCompatActivity.getClass());
                    //3.调用意图对象的setComponent方法
                    ComponentName componentName = new ComponentName(MainActivity.this,appCompatActivity.getClass());
                    intent.setComponent(componentName);
                    //activity 启动模式
                    //FLAG_ACTIVITY_NEW_TASK        开辟一个新的任务栈
                    //FLAG_ACTIVITY_SINGLE_TOP      栈顶为待跳转的活动实例,重用栈顶实例
                    //FLAG_ACTIVITY_CLEAR_TASK      跳转到新页面时,栈中原有实例被清空
                    //FLAG_ACTIVITY_CLEAR_TOP       栈中存在待跳转活动实例时,重新创建一个新实例,并清空实例上方所有实例
                    //FLAG_ACTIVITY_NO_HISTORY      栈中不保存新启动的活动实例
                    //跳转到新页面时清空栈,同时开辟一个新的活动栈
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            }
        });
    }

    //view 长按点击事件
    public void view_long_click(View view){

        //长按点击事件
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Button doClickButton = findViewById(v.getId());
                doClickButton.setText(doClickButton.getText()+"长按事件");
                //有多层view嵌套时,如果为true则使用当前的事件,如果为false 则同时触发点击事件
                //为false时 顺序为长按时触发长按事件,松开触发点击事件
                return false;
            }
        });
    }

    //button 的 onClick的属性 直接调用方法,已过时 但可用方便以后阅读他人代码
    public void doClick(View view) {
        //获取当前view的ID
        Button doClickButton = findViewById(view.getId());

        CountDownTimer countDownTimer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                doClickButton.setEnabled(false);
                doClickButton.setTextColor(Color.WHITE);
                doClickButton.setText("倒计时:" + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                doClickButton.setEnabled(true);
                doClickButton.setText(DateUtils.getNowTime());
            }
        }.start();


    }


}
