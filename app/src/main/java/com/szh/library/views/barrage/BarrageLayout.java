package com.szh.library.views.barrage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Author szh
 * @Date 2018/8/2.
 * @Description 弹幕布局
 */

public class BarrageLayout extends RelativeLayout {
    private Context context;
    //滚动方向-从右到左
    private static final int FROM_RIGHT_TO_LEFT = 0;
    //滚动方向-从左到右
    private static final int FROM_LEFT_TO_RIGHT = 1;
    //滚动方向
    private int mDirection =0;
    //控件视图宽度
    private int mLayoutWidth;
    //控件视图高度
    private  int mLayoutHeight;
    //最大显示弹幕行数
    private  int maxLine = 10;
    //每个弹幕的高度
    private int itemHeight;
    //弹幕滚动速度
    private int speed = 5000;
    //是否允许叠加,不允许直接丢弃重叠的弹幕
    private boolean coverEnable = false;

    private Map<String,View> mChildView = new HashMap<>();

    public BarrageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context =context;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mLayoutWidth = getMeasuredWidth();
        mLayoutHeight = getMeasuredHeight();
        itemHeight = mLayoutHeight/maxLine;
        int childCount = this.getChildCount();
        for(int i=0;i<childCount;i++){
            View view = getChildAt(i);
            if(view !=null){
                RelativeLayout.LayoutParams lp = (LayoutParams) view.getLayoutParams();
                if(lp.leftMargin<=0){
                    if(mDirection == FROM_RIGHT_TO_LEFT){
                        view.layout(mLayoutWidth,lp.topMargin,mLayoutWidth+view.getMeasuredWidth(),lp.topMargin+view.getMeasuredHeight());
                    }else if(mDirection == FROM_LEFT_TO_RIGHT){
                        view.layout(-view.getMeasuredWidth(),lp.topMargin,0,lp.topMargin+view.getMeasuredHeight());
                    }
                }
            }

        }
    }
    //创建并发送一条文本弹幕
    public void createBarrageItemView(BarrageItemEntity barrageItemEntity){
        final TextView textView = new TextView(context);
        if(TextUtils.isEmpty(barrageItemEntity.getText())){
            return;
        }else{
            textView.setText(barrageItemEntity.getText());
        }
        if(barrageItemEntity.getTextColor() != 0){
            textView.setTextColor(barrageItemEntity.getTextColor());
        }
        if(barrageItemEntity.getTextSize() != 0){
            textView.setTextSize(barrageItemEntity.getTextSize());
        }
        if(barrageItemEntity.getOnClickListener() !=null){
            textView.setOnClickListener(barrageItemEntity.getOnClickListener());
        }
        textView.setBackgroundColor(Color.TRANSPARENT);
        textView.setSingleLine(true);
        int w = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        textView.measure(w,h);
        int width = textView.getMeasuredWidth();
        int height = textView.getMeasuredHeight();
        RelativeLayout.LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        int randomNum = new Random().nextInt(maxLine);
        //防止数据太长一直循环，导致卡顿
        long currentTime = System.currentTimeMillis();
        while(needResetRow(randomNum)){
            if(System.currentTimeMillis() - currentTime >=100){
                if(coverEnable){
                    break;
                }else{
                    return;
                }
            }
            randomNum = new Random().nextInt(maxLine);
        }
        lp.topMargin =randomNum*itemHeight;
        textView.setLayoutParams(lp);
        addView(textView);
        ObjectAnimator animator =null;
        if(mDirection == FROM_RIGHT_TO_LEFT){
            animator = ObjectAnimator.ofFloat(textView,"translationX",0,-(mLayoutWidth+width));
        }else if(mDirection == FROM_LEFT_TO_RIGHT){
            animator = ObjectAnimator.ofFloat(textView,"translationX",0,mLayoutWidth);
        }
        animator.setDuration(speed);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                removeView(textView);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationPause(Animator animation) {
                super.onAnimationPause(animation);
            }

            @Override
            public void onAnimationResume(Animator animation) {
                super.onAnimationResume(animation);
            }
        });
        mChildView.put(randomNum+"",textView);
    }
    //是否需要重新选择行数
    private boolean needResetRow(int row){
        View view = mChildView.get(row+"");
        if(view != null){
            if(mLayoutWidth - view.getX()<view.getMeasuredWidth()){
                return true;
            }
        }
        return false;
    }

    public int getmDirection() {
        return mDirection;
    }

    public void setmDirection(int mDirection) {
        this.mDirection = mDirection;
    }

    public int getMaxLine() {
        return maxLine;
    }

    public void setMaxLine(int maxLine) {
        this.maxLine = maxLine;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isCoverEnable() {
        return coverEnable;
    }

    public void setCoverEnable(boolean coverEnable) {
        this.coverEnable = coverEnable;
    }
}
