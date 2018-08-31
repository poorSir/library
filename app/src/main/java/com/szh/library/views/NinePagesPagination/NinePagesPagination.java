package com.szh.library.views.NinePagesPagination;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.szh.library.R;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author szh
 * @Date 2018/8/28.
 * @Description 九宫格视图分页加载
 */

public class NinePagesPagination<T> extends LinearLayout {
    private View mView;
    private ViewPager viewPager;
    private Context context;
    private List<List<T>> list = new ArrayList<>();
    //已经请求过的，不在重复请求
    private List<Integer> hasAdded = new ArrayList<>();
    //是否每次请求
    private boolean isEveryRequest;
    //是否进行分页加载
    private boolean isPagingLoad;
    private RequestDataListener listener;
    private MyViewPagerAdapter adapter;
    private ViewPager.OnPageChangeListener pageChangeListener;
    public NinePagesPagination(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        viewPager = new ViewPager(context);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        viewPager.setLayoutParams(params);
        mView = viewPager;
        addView(mView);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(listener!=null && listener.gridViewColumn() != 0){
            int column = listener.gridViewColumn();
            int row = (int) Math.ceil((double) list.get(0).size()/column);
            int heightMeasureSpec2 = MeasureSpec.makeMeasureSpec(row*getWidth()/column,
                    MeasureSpec.AT_MOST);
            setMeasuredDimension(widthMeasureSpec,heightMeasureSpec2);
        }
    }

    public void setListener(RequestDataListener listener) {
        this.listener = listener;
    }

    public void setPageChangeListener(ViewPager.OnPageChangeListener pageChangeListener) {
        this.pageChangeListener = pageChangeListener;
    }

    public void setEveryRequest(boolean everyRequest) {
        isEveryRequest = everyRequest;
    }

    public void setPagingLoad(boolean pagingLoad) {
        isPagingLoad = pagingLoad;
    }

    public void setList(List<List<T>> list) {
        this.list = list;
        initView();
        invalidate();
    }

    private void initView() {
        adapter = new MyViewPagerAdapter(context, list,listener);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(pageChangeListener != null){
                    pageChangeListener.onPageScrolled(position,positionOffset,positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if(pageChangeListener != null){
                    pageChangeListener.onPageSelected(position);
                }
                if (isPagingLoad) {//分页加载
                    if (isEveryRequest) {//每次都请求
                        adapter.setCurrentPosition(position);
                        listener.request(position);
                    } else {//请求过一次将不在重复请求
                        if (!hasAdded.contains(position)) {
                            hasAdded.add(position);
                            adapter.setCurrentPosition(position);
                            listener.request(position);
                        }
                    }
                }
            }


            @Override
            public void onPageScrollStateChanged(int state) {
                if(pageChangeListener != null){
                    pageChangeListener.onPageScrollStateChanged(state);
                }
            }
        });
    }
    //数据刷新
    public void dataInvalidate(int position,List<T> changeList){
        list.set(position, changeList);
        adapter.notifyDataSetChanged();
    }
}
