package com.szh.library.views.NinePagesPagination;

import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.szh.library.R;

import java.util.List;

/**
 * @Author szh
 * @Date 2018/8/28.
 * @Description
 */

public class MyViewPagerAdapter<T> extends PagerAdapter {
    private Context context;
    private List<List<T>> list;
    private LayoutInflater inflater;
    private MyGridViewAdapter adapter;
    private int currentPosition;
    private RequestDataListener listener;
    public MyViewPagerAdapter(Context context, List<List<T>> list,RequestDataListener listener) {
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
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        GridView view = new GridView(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        view.setTag(position);
        if(listener!=null && listener.gridViewColumn() != 0){
            view.setNumColumns(listener.gridViewColumn());
        }
        adapter = new MyGridViewAdapter(context, list.get(position),listener);
        view.setAdapter(adapter);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        //viewpager立即刷新页面
        if (list != null && list.size() == 0) {
            return POSITION_NONE;
        }
        View view = (View) object;
        if (currentPosition == (Integer) view.getTag()) {
            return POSITION_NONE;
        } else {
            return POSITION_UNCHANGED;
        }
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if(adapter != null){
            adapter.notifyDataSetChanged();
        }
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}
