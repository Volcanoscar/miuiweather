package com.example.miuiweather.util;

import com.example.miuiweather.domain.City;
import com.github.stuxuhai.jpinyin.PinyinHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kidcats on 2016/6/4.
 */
public class CityNameUtil {
    private static final String[] AZ = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v",
            "w", "x", "y", "z"};
    private static Map<String, Integer> citynumber = new HashMap<>();
    private static Map<Integer, String> abcinfo = new HashMap<>();

    /**
     * 这个方法用来处理第几个字母是第几个数
     * @return
     */
    public static Integer letterToNum(String input){
        int number=0;
        for (Byte num:input.getBytes()) {
            number=num-64;
        }
        return number;
    }
    public static Integer letterToStr(Integer input){
        StringBuffer str=new StringBuffer();
        for (Byte num:String.valueOf(input).getBytes()) {
            str.append(num+64);
        }
        return Integer.valueOf(str.toString());
    }


    public static List<String> getCityName(List<City> citys){
        List<String> lists=new ArrayList<>();
        for (City c:citys) {
            lists.add(PinyinHelper.getShortPinyin(c.CityName));
        }
        return lists;
    }

    public static Map<String, Integer> findNumber(List<String> citynames) {
        for (int ii = 0; ii < 25; ii++) {
            List<String> list = new ArrayList<>();
            for (String cityname : citynames) {
                if (cityname.startsWith(AZ[ii])) {
                    list.add(cityname);
                }
                if (ii==0){
                    citynumber.put(AZ[ii],list.size());
                }else {
                    citynumber.put(AZ[ii], list.size()+citynumber.get(AZ[ii-1]));
                }
            }
        }

        return citynumber;
    }

}