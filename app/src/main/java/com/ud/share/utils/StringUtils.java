package com.ud.share.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by PP on 2019/4/26.
 */
public class StringUtils {

    public static String getFileName(String url){

        int start=url.lastIndexOf("/");
        int end=url.lastIndexOf(".");
        if(start!=-1 && end!=-1){
            return url.substring(start+1,url.length());
        }else{
            return null;
        }


    }

    public static boolean matchLuhn(String cardNo) {
        int[] cardNoArr = new int[cardNo.length()];
        for (int i=0; i<cardNo.length(); i++) {
            cardNoArr[i] = Integer.valueOf(String.valueOf(cardNo.charAt(i)));
        }
        for(int i=cardNoArr.length-2;i>=0;i-=2) {
            cardNoArr[i] <<= 1;
            cardNoArr[i] = cardNoArr[i]/10 + cardNoArr[i]%10;
        }
        int sum = 0;
        for(int i=0;i<cardNoArr.length;i++) {
            sum += cardNoArr[i];
        }
        return sum % 10 == 0;
    }

    public static String getPhoneX(String mobile){
        if (mobile.length()!=11){
            return mobile;
        }

        return  mobile.substring(0,3)+"****"+mobile.substring(7,mobile.length());



    }

    public static String trimZero(String s) {
        if (s.indexOf(".") > 0) {
            // 去掉多余的0
            s = s.replaceAll("0+?$", "");
            // 如最后一位是.则去掉
            s = s.replaceAll("[.]$", "");
        }
        return s;
    }



    public static Long date2TimeStamp(String date) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(date).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0l;

    }

    public static String timeStamp2Date(long time) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s=sdf.format(new Date(time*1000));
        int length=s.length();
        return s.substring(5,length);

    }






}
