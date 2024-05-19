package com.example.amdroidtestjava.adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.amdroidtestjava.ViewPagerActivity;
import com.example.amdroidtestjava.enity.MyBaseEntity;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter  extends PagerAdapter {

    private final Context context;
    private final List<MyBaseEntity> myBaseEntityList;
    private List<ImageView> imageViewList = new ArrayList<>();
    public ViewPagerAdapter(Context context, List<MyBaseEntity> myBaseEntityList) {
        this.context = context;
        this.myBaseEntityList = myBaseEntityList;
        for(MyBaseEntity myBaseEntity : myBaseEntityList){
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(myBaseEntity.getIcon());
            imageView.setLayoutParams(new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            imageViewList.add(imageView);
        }

    }

    @Override
    public int getCount() {
        return imageViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    //实例化指定位置的页面,并将其添加到容器中
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //添加一个view到container中,而后返回一个跟这个view关联起来的对象
        //这个对象可以是view本身,也能过是其余对象
        //关键是isViewFrombject可以将view和这个object关联起来
        ImageView imageView = imageViewList.get(position);
        container.addView(imageView);
        return imageView;
//        return super.instantiateItem(container, position);
    }

    //从容器中销毁指定的页面
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(imageViewList.get(position));
//        super.destroyItem(container, position, object);
    }
}
