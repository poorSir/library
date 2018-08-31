package com.szh.library.views.NinePagesPagination;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.TextView;

import com.szh.library.R;

import java.util.List;
import java.util.Observable;

/**
 * @Author szh
 * @Date 2018/8/28.
 * @Description
 */

public class MyGridViewAdapter<T> extends BaseAdapter{
    private Context context;
    private List<T> list;
    private LayoutInflater inflater;
    private RequestDataListener listener;
    public MyGridViewAdapter(Context context, List<T> list,RequestDataListener listener){
        this.context = context;
        this.list = list;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = listener.getView(inflater,position,convertView,parent,list);
        int width;
        //计算item的宽
        if(listener != null &listener.gridViewColumn() != 0){
             width = parent.getWidth()/listener.gridViewColumn();
        }else{
             width = parent.getWidth()/3;
        }
        //设置item的宽高相等
        convertView.setLayoutParams(new AbsListView.LayoutParams(width,width));
        return convertView;
    }
}
