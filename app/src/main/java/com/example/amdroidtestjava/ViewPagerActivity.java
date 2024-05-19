package com.example.amdroidtestjava;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import com.example.amdroidtestjava.adapter.ViewPagerAdapter;
import com.example.amdroidtestjava.enity.MyBaseEntity;
import com.example.amdroidtestjava.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final String[] item = new String[]{"AAA","BBB","CCC","DDD","EEE","FFF","GGG","HHH","III","JJJ","KKK","LLL"};
    ViewPager myViewPager;
    PagerTabStrip myPagerTabStrip;
    List<MyBaseEntity> myBaseEntityList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_pager);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.myViewPagerActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        initViewOnListener();
        for(String str : item){

            MyBaseEntity myBaseEntity = new MyBaseEntity();
            myBaseEntity.setIcon(R.drawable.tigger);
            myBaseEntity.setName(str);
            myBaseEntity.setDesc("获取的是:"+str);
            myBaseEntityList.add(myBaseEntity);
        }
        ViewPagerAdapter  viewPagerAdapter = new ViewPagerAdapter(this , myBaseEntityList);
        myViewPager.setAdapter(viewPagerAdapter);

        initPagerStrip();
    }

    //初始化翻页标签栏
    private void initPagerStrip() {
        //设置翻页标签栏的文本大小
        myPagerTabStrip.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        myPagerTabStrip.setTextColor(Color.BLACK);
    }

    private void initView() {
        myViewPager = findViewById(R.id.myViewPager);
        myPagerTabStrip = findViewById(R.id.myPagerTabStrip);
    }

    private void initViewOnListener() {
        myViewPager.addOnPageChangeListener(this);

    }

    /**
     *  翻页过程中触发
     * @param position 示当前页面的序号
     * @param positionOffset 页面偏移的百分比，取值为0到1
     * @param positionOffsetPixels 页面的偏移距离
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 在翻页结束后触发。
     * @param position 表示当前滑到了哪一个页画
     */
    @Override
    public void onPageSelected(int position) {
        Utils.toastShow(this,"当前为第"+position+"个图片:"+myBaseEntityList.get(position).getName());
    }

    /**
     *  翻页状态改变时触发  翻页过程中 状态值变化顺序为 正在滑动 ->滑动完毕 ->静止
     * @param state The new scroll state. 0 静止 1 正在滑动 2 滑动完毕
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}