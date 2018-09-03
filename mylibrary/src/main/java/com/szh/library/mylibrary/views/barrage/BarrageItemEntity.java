package com.szh.library.mylibrary.views.barrage;

import android.view.View;

/**
 * @Author szh
 * @Date 2018/8/2.
 * @Description
 */

public class BarrageItemEntity {
    //弹幕文字
    private String text;
    //弹幕文字大小
    private int textSize;
    //弹幕文字颜色
    private int textColor;
    //点击事件
    private View.OnClickListener onClickListener;

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}
