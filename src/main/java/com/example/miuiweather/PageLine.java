package com.example.miuiweather;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.miuiweather.R;


/**
 * Created by kidcats on 2016/5/10.
 */
public class PageLine extends View {
    private Paint mpaint;
    //画布的宽和高
    private float canvasHei;
    private float canvasWei;
    //用户输入的温度值最高和昨日最高还有明天最高
    private int total;
    private int yestotal;
    private int montotal;

    public PageLine(Context context) {
        super(context);
        init();
    }


    public PageLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.PageLine);
        total=a.getInt(R.styleable.PageLine_total,0);
        yestotal=a.getInteger(R.styleable.PageLine_yestotal,0);
        montotal=a.getInt(R.styleable.PageLine_montotal,0);
    }

    public PageLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mpaint=new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setColor(Color.WHITE);
        mpaint.setStyle(Paint.Style.FILL);
        mpaint.setStrokeWidth(2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w=measureWidth(widthMeasureSpec);
        int h=measureHidth(heightMeasureSpec);
        setMeasuredDimension(w,h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasWei=canvas.getWidth();
        canvasHei=canvas.getHeight();
        float yestotalsize=getnumber(yestotal,canvasHei);
        float totalsize=getnumber(total,canvasHei);
        float montotalsize=getnumber(montotal,canvasHei);
        canvas.drawLine(0,yestotalsize,canvasWei/2,totalsize,mpaint);
        canvas.drawLine(canvasWei/2,totalsize,canvasWei,montotalsize,mpaint);
    }


    /**
     * 重新测量宽度
     * @param measureSpec
     * @return
     */
    private int measureWidth(int measureSpec){
        int result=0;
        int specmode=MeasureSpec.getMode(measureSpec);
        int specsize=MeasureSpec.getSize(measureSpec);
        if (specmode==MeasureSpec.EXACTLY){
            result=specsize;
        }else {
            result=specsize;
            if (specmode==MeasureSpec.AT_MOST){
                result=Math.min(result,specsize);
            }
        }
        return result;
    }

    /**
     * 重新测量高度
     * @param measureSpec
     * @return
     */
    private int measureHidth(int measureSpec){
        int result=0;
        int specmode=MeasureSpec.getMode(measureSpec);
        int specsize=MeasureSpec.getSize(measureSpec);
        if (specmode==MeasureSpec.EXACTLY){
            result=200;
        }else {
            result=specsize;
            if (specmode==MeasureSpec.AT_MOST){
                result=Math.min(result,specsize);
            }
        }
        return result;
    }

    /**
     * 用于计算出弧线的位置
     * @param cnacaslite
     * @param viewlie
     * @return
     */
    private float getnumber(float cnacaslite,float viewlie){
        return (viewlie*cnacaslite)/50;
    }

    /**
     * 设置最高温度
     * @param total
     */
    public void setTotal(int total){
        this.total=total;
    }
    /**
     * 设置最高昨天温度
     * @param total
     */
    public void setYesTotal(int total){
        this.yestotal=total;
    }
    /**
     * 设置明天最高温度
     * @param total
     */
    public void setMonTotal(int total){
        this.montotal=total;
    }

}
