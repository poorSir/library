package com.szh.library.mylibrary.views.NinePagesPagination;

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
   void request(int position);
    //gridView列数
    int gridViewColumn();
    View getView(LayoutInflater inflater, int position, View convertView, ViewGroup parent, List<T> list);
}
