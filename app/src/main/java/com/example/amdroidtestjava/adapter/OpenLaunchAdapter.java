package com.example.amdroidtestjava.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.amdroidtestjava.LaunchSimpleActivity;
import com.example.amdroidtestjava.MainActivity;
import com.example.amdroidtestjava.R;
import com.example.amdroidtestjava.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class OpenLaunchAdapter extends PagerAdapter {

    List<View> viewList = new ArrayList<>();

    public OpenLaunchAdapter(Context context , int[] launchImageList) {
        for(int i = 0 ; i<launchImageList.length ; i++){
            View view = LayoutInflater.from(context).inflate(R.layout.item_launch,null);
            ImageView openImageView =  view.findViewById(R.id.openImageView);
            RadioGroup openRadioGroup = view.findViewById(R.id.openRadioGroup);
            Button openButton = view.findViewById(R.id.openButton);
            openImageView.setImageResource(launchImageList[i]);
            viewList.add(view);

            //每个页面都分配一组矿应的单选按钮
            for(int j : launchImageList){
                RadioButton radioButton = new RadioButton(context);
                radioButton.setLayoutParams(new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
//                radioButton.setPadding(5,5,5,5);
                openRadioGroup.addView(radioButton);
            }
            //当前位置的单选按钮要高亮显示，比如第二个引导页就高亮第二个单选按钮
            ((RadioButton)openRadioGroup.getChildAt(i)).setChecked(true);
            if(i==launchImageList.length-1){
                openButton.setVisibility(View.VISIBLE);
                openButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        ComponentName componentName = new ComponentName(context,new MainActivity().getClass());
                        intent.setComponent(componentName);
                        context.startActivity(intent);
//                        Utils.toastShow(context,"--------------");
                    }

                });
            }
        }
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View item = viewList.get(position);
        container.addView(item);
        return item;
//        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
         container.removeView(viewList.get(position ));
//        super.destroyItem(container, position, object);
    }
}
