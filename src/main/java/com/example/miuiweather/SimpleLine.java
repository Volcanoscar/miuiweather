package com.example.miuiweather;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by kidcats on 2016/6/15.
 */
public class SimpleLine extends View {

    private Paint mPointPaint;
    private Paint mLinePaint;
    private int[] paintXY;
    private int mLineColor;
    private int mPointColor;
    private float mPointRadius = 5.0f;
    private float mViewWidth;
    private float mViewHeight;
    private int tempmax;
    private int tempmin;
    private float mx;
    private float my;


    public SimpleLine(Context context) {
        this(context, null);
    }


    public SimpleLine(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.simpleline);
    }

    public SimpleLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SimpleLine, defStyleAttr, 0);
        mPointColor = a.getColor(R.styleable.SimpleLine_PointColor, Color.parseColor("#55e77c"));
        mLineColor = a.getColor(R.styleable.SimpleLine_LineColor, Color.parseColor("#65a9af"));
        a.recycle();
        init();
    }

    private void init() {
        mPointPaint = new Paint();
        mPointPaint.setAntiAlias(true);
        mPointPaint.setColor(mPointColor);
        mPointPaint.setStyle(Paint.Style.FILL);
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(mLineColor);
        mLinePaint.setAlpha(30);
        mLinePaint.setStrokeWidth(4.5f);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (paintXY != null) {
            tempmax = sortCountMax(paintXY);
            tempmin = sortCountMin(paintXY);
        }else {
            return;
        }
        if (paintXY.length != 0) {
            for (int i = 0; i < paintXY.length; i++) {
                float x = i * (mViewWidth )/ paintXY.length + 10;
                float y =mViewHeight - (paintXY[i]-tempmin) *(mViewHeight-10)  / (tempmax  - tempmin )-5;
                canvas.drawCircle(x, y, mPointRadius, mPointPaint);
                if (i!=0){
                    canvas.drawLine(mx,my,x,y,mLinePaint);
                }
                mx=x;my=y;
            }
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec, true), measure(heightMeasureSpec, false));
    }

    private int measure(int measureSpec, boolean isWidth) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int padding = isWidth ? getPaddingLeft() + getPaddingRight() : getPaddingTop() + getPaddingBottom();
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = isWidth ? getSuggestedMinimumWidth() : getSuggestedMinimumHeight();
            result += padding;
            if (mode == MeasureSpec.AT_MOST) {
                if (isWidth) {
                    result = Math.max(result, size);
                } else {
                    result = Math.min(result, size);
                }
            }
        }
        return result;
    }

    public void setPointValue(int[] values) {
        this.paintXY = values;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mViewHeight = h;
        mViewWidth = w;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private int sortCountMax(int[] counts) {
        int max = counts[0];
        for (int i=0 ;i< counts.length;i++) {
            if (max < counts[i]) {
                max = counts[i];
            }
        }
        return max;

    }

    private int sortCountMin(int[] counts) {
        int min = counts[0];
        for (int i=0 ;i< counts.length;i++) {
            if (min > counts[i]) {
                min = counts[i];
            }
        }
        return min;
    }

}
