package com.szh.library.views.NinePagesPagination;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @Author szh
 * @Date 2018/8/29.
 * @Description
 */

public interface RequestDataListener<T> {
    List<T> request();
    //gridView列数
    int gridViewColumn();
    View getView(LayoutInflater inflater, int position, View convertView, ViewGroup parent,List<T> list);
}
