package com.example.miuiweather.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.miuiweather.R;

/**
 * Created by kidcats on 2016/6/6.
 */
public class Util {

    /**
     * 判断是否联网
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 根据天气代码选择相应的图片
     */
    public static Drawable SelectIcon(Context context,String code){
        int weathercode=Integer.parseInt(code);
        Drawable drawable=context.getResources().getDrawable(R.drawable.yi100);
        switch (weathercode){
            case 100:
                drawable=context.getResources().getDrawable(R.drawable.yi100);
                if (drawable!=null){
                    return drawable;
                }
            break;
            case 101:
                drawable=context.getResources().getDrawable(R.drawable.yi100);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 102:
                drawable=context.getResources().getDrawable(R.drawable.yi102);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 103:
                drawable=context.getResources().getDrawable(R.drawable.yi103);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 104:
                drawable=context.getResources().getDrawable(R.drawable.yi104);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 200:
                drawable=context.getResources().getDrawable(R.drawable.yi200);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 201:
                drawable=context.getResources().getDrawable(R.drawable.yi201);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 202:
                drawable=context.getResources().getDrawable(R.drawable.yi202);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 203:
                drawable=context.getResources().getDrawable(R.drawable.yi204);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 204:
                drawable=context.getResources().getDrawable(R.drawable.yi204);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 205:
                drawable=context.getResources().getDrawable(R.drawable.yi205);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 206:
                drawable=context.getResources().getDrawable(R.drawable.yi206);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 207:
                drawable=context.getResources().getDrawable(R.drawable.yi207);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 208:
                drawable=context.getResources().getDrawable(R.drawable.yi208);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 209:
                drawable=context.getResources().getDrawable(R.drawable.yi209);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 210:
                drawable=context.getResources().getDrawable(R.drawable.yi210);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 211:
                drawable=context.getResources().getDrawable(R.drawable.yi211);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 212:
                drawable=context.getResources().getDrawable(R.drawable.yi212);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 213:
                drawable=context.getResources().getDrawable(R.drawable.yi213);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 300:
                drawable=context.getResources().getDrawable(R.drawable.yi300);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 301:
                drawable=context.getResources().getDrawable(R.drawable.yi301);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 302:
                drawable=context.getResources().getDrawable(R.drawable.yi302);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 303:
                drawable=context.getResources().getDrawable(R.drawable.yi303);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 304:
                drawable=context.getResources().getDrawable(R.drawable.yi304);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 305:
                drawable=context.getResources().getDrawable(R.drawable.yi305);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 306:
                drawable=context.getResources().getDrawable(R.drawable.yi306);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 307:
                drawable=context.getResources().getDrawable(R.drawable.yi307);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 308:
                drawable=context.getResources().getDrawable(R.drawable.yi308);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 309:
                drawable=context.getResources().getDrawable(R.drawable.yi309);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 310:
                drawable=context.getResources().getDrawable(R.drawable.yi310);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 311:
                drawable=context.getResources().getDrawable(R.drawable.yi311);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 312:
                drawable=context.getResources().getDrawable(R.drawable.yi312);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 313:
                drawable=context.getResources().getDrawable(R.drawable.yi313);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 400:
                drawable=context.getResources().getDrawable(R.drawable.yi400);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 401:
                drawable=context.getResources().getDrawable(R.drawable.yi401);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 402:
                drawable=context.getResources().getDrawable(R.drawable.yi402);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 403:
                drawable=context.getResources().getDrawable(R.drawable.yi403);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 404:
                drawable=context.getResources().getDrawable(R.drawable.yi404);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 405:
                drawable=context.getResources().getDrawable(R.drawable.yi405);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 406:
                drawable=context.getResources().getDrawable(R.drawable.yi406);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 407:
                drawable=context.getResources().getDrawable(R.drawable.yi407);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 500:
                drawable=context.getResources().getDrawable(R.drawable.yi500);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 501:
                drawable=context.getResources().getDrawable(R.drawable.yi501);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 502:
                drawable=context.getResources().getDrawable(R.drawable.yi502);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 503:
                drawable=context.getResources().getDrawable(R.drawable.yi503);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 504:
                drawable=context.getResources().getDrawable(R.drawable.yi504);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 507:
                drawable=context.getResources().getDrawable(R.drawable.yi507);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 508:
                drawable=context.getResources().getDrawable(R.drawable.yi508);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 900:
                drawable=context.getResources().getDrawable(R.drawable.yi900);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 901:
                drawable=context.getResources().getDrawable(R.drawable.yi901);
                if (drawable!=null){
                    return drawable;
                }
                break;
            case 999:
                drawable=context.getResources().getDrawable(R.drawable.yi999);
                if (drawable!=null){
                    return drawable;
                }
                break;
        }
        return drawable;
    }
}
