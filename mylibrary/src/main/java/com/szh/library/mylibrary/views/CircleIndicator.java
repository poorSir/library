package com.szh.library.mylibrary.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.szh.library.utils.text.DimensionConvert;

/**
 * @Author szh
 * @Date 2018/8/29.
 * @Description 圆点指示器
 */

public class CircleIndicator extends View{
    private Context context;
    //小圆点个数
    private int circleTotal = 3;
    //圆点半径
    private int radius;
    //圆点间距
    private int circleDistance=10;
    //当前所选择的圆点
    private int position;
    private int normalColor;
    private int selectColor;
    private Paint normalPaint;
    private Paint selectPaint;
    public CircleIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        radius = DimensionConvert.dp2px(5,context);
        normalColor = Color.BLACK;
        selectColor = Color.RED;
        normalPaint = new Paint();
        normalPaint.setAntiAlias(false);//设置为无锯齿
        normalPaint.setColor(Color.BLACK);
        normalPaint.setStrokeWidth(5);//线宽
        selectPaint = new Paint();
        selectPaint.setAntiAlias(false);//设置为无锯齿
        selectPaint.setColor(Color.RED);
        selectPaint.setStrokeWidth(5);//线宽
    }

    public void setCircleTotal(int circleTotal) {
        this.circleTotal = circleTotal;
        invalidate();
    }

    public void setRadius(int radius) {
        this.radius = radius;
        invalidate();
    }

    public void setCircleDistance(int circleDistance) {
        this.circleDistance = circleDistance;
        invalidate();
    }

    public void setPosition(int position) {
        this.position = position;
        invalidate();
    }

    public void setNormalColor(int normalColor) {
        this.normalColor = normalColor;
        invalidate();
    }

    public void setSelectColor(int selectColor) {
        this.selectColor = selectColor;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0;i<circleTotal;i++){
            canvas.drawCircle(getPaddingLeft()+radius+(i*(2*radius+circleDistance)),getPaddingTop()+radius,radius,normalPaint);
        }
        canvas.drawCircle(getPaddingLeft()+radius+(position*(2*radius+circleDistance)),getPaddingTop()+radius,radius,selectPaint);
    }
    private int measureWidth(int widthMeasureSpec){
        int result ;
        int specSize =MeasureSpec.getSize(widthMeasureSpec);
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else{
            result = getPaddingLeft()+getPaddingRight()+radius*2*circleTotal+circleDistance*(circleTotal-1);
            if(specMode == MeasureSpec.AT_MOST){
                result = Math.min(result,specSize);
            }
        }
        return result;
    }
    private int measureHeight(int heightMeasureSpec){
        int result ;
        int specSize =MeasureSpec.getSize(heightMeasureSpec);
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else{
            result = getPaddingTop()+getPaddingBottom()+radius*2;
            if(specMode == MeasureSpec.AT_MOST){
                result = Math.min(result,specSize);
            }
        }
        return result;
    }
}
