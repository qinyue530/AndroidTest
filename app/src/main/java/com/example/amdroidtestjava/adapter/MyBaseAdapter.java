package com.example.amdroidtestjava.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amdroidtestjava.R;
import com.example.amdroidtestjava.enity.MyBaseEntity;
import com.example.amdroidtestjava.util.Utils;

import java.util.List;

public class MyBaseAdapter extends BaseAdapter {

    private Context context;
    private List<MyBaseEntity> myBaseEntityList;

    public MyBaseAdapter(Context context, List<MyBaseEntity> myBaseEntityList) {
        this.context = context;
        this.myBaseEntityList = myBaseEntityList;
    }

    @Override
    public int getCount() {
        return myBaseEntityList.size();
    }

    @Override
    public Object getItem(int i) {
        return myBaseEntityList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();
        if(view == null){
            //根据布局文件 生成转换的视图
            view =LayoutInflater.from(context).inflate(R.layout.item_base,null);
            holder.icon = view.findViewById(R.id.base_icon);
            holder.name = view.findViewById(R.id.base_name);
            holder.desc = view.findViewById(R.id.base_desc);
            holder.baseButton = view.findViewById(R.id.base_button);
            //将视图持有者保存到转换视图中
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        MyBaseEntity myBaseEntity = myBaseEntityList.get(i);
        holder.icon.setImageResource(myBaseEntity.getIcon());
        holder.name.setText(myBaseEntity.getName());
        holder.desc.setText(myBaseEntity.getDesc());
        holder.baseButton.setOnClickListener(view1 -> {
            Utils.toastShow(context,"列表中的按钮:您选择的是"+ myBaseEntity.getName());
        });
        return  view;
    }

    public final class ViewHolder{
        public ImageView icon;

        public TextView name;
        public TextView desc;

        public Button baseButton;
    }
}
