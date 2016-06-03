package com.example.miuiweather;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by kidcats on 2016/6/3.
 */
public class QulikSearchBar extends View {
    private final String[] AZ=new String[]{"A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};
    private Paint mPaint;
    private float cellWidth;
    private float cellHeight;
    private OnNumberClickListener mListener;
    private float width;
    private float height;
    private Context mcontext;

    public void setOnNumberClickListener(OnNumberClickListener listener){
        if (listener!=null){
            this.mListener=listener;
        }else {
            Log.i("tag","listener is null");
        }
    }

    interface OnNumberClickListener{
        void onNumberClick(String number);
    }
    public QulikSearchBar(Context context) {
        super(context);
        init();
    }


    public QulikSearchBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QulikSearchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setTypeface(Typeface.DEFAULT);
        mPaint.setTextSize(25);//这种方法并不是好的解决办法,等Google出来了记得换
        mPaint.setTextAlign(Paint.Align.LEFT);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        cellWidth=measure(widthMeasureSpec,true);
        cellHeight=measure(heightMeasureSpec,false)/AZ.length;
       setMeasuredDimension(measure(widthMeasureSpec,true),measure(heightMeasureSpec,false));
    }

    private int measure(int measureSpec, boolean isWidth) {
        int result = 0;
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



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //for
        for (int i=0;i<AZ.length;i++){
            String text=AZ[i];
            //获取文本的宽度
            int x = (int) (cellWidth /2.0f);
            int textHeight = (int) (cellHeight*3/4.0f);//获取文本高度
            int y = (int) (cellHeight*i+cellHeight/2.0f+textHeight/2.0f);//y是基于baseline
            canvas.drawText(text,x,y,mPaint);
        }
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=getMeasuredWidth();
        height=getMeasuredHeight();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                index= (int) (event.getY()/cellHeight);//这是判断第几个被选中
                if (event.getY()/cellHeight<1.0f&&event.getY()/cellHeight>0.0f){
                    if (mListener!=null){
                        mListener.onNumberClick(AZ[0]);
                    }
                }
                if(index>0&&index<AZ.length){
                    if (mListener!=null){
                        mListener.onNumberClick(AZ[index]);
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                index= (int) (event.getY()/cellHeight);//这是判断第几个被选中
                if (event.getY()/cellHeight<1.0f&&event.getY()/cellHeight>0.0f){
                    if (mListener!=null){
                        mListener.onNumberClick(AZ[0]);
                    }
                }
                if(index>0&&index<AZ.length){
                    if (mListener!=null){
                        mListener.onNumberClick(AZ[index]);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }


}
