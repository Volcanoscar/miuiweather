package com.example.miuiweather;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kidcats on 2016/6/3.
 */
public class PageView extends LinearLayout {

    @BindView(R.id.page_tv_date)
    TextView tvdate;
    @BindView(R.id.page_image)
    ImageView pageimage;
    @BindView(R.id.page_tv_temp)
    TextView tvtemp;


    public PageView(Context context) {
        super(context);
    }


    public PageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = View.inflate(context, R.layout.lay_pageview, this);
        initView(view);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PageView);
        String date = a.getString(R.styleable.PageView_date);
        String temp = a.getString(R.styleable.PageView_temp);
        Drawable draw = a.getDrawable(R.styleable.PageView_weadraw);
        tvdate.setText(date);
        tvtemp.setText(temp);
        pageimage.setImageDrawable(draw);
        a.recycle();
    }

    @Override
    protected boolean dispatchGenericFocusedEvent(MotionEvent event) {
        return false;
    }

    public PageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initView(View view) {
        ButterKnife.bind(this, view);
    }


    /**
     * 以下三个方法用于代码设置
     *
     * @param temp
     */
    public void setTvtemp(String temp) {
        tvtemp.setText(temp);
    }

    public void setTvdate(String data) {
        tvdate.setText(data);
    }

    public void setPageimage(Drawable drawable) {
        pageimage.setImageDrawable(drawable);
    }
}
