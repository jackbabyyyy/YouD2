package com.yd.youd.utils;

/**
 * Created by PP on 2019/4/12.
 */
public class StatusUtil {

    public static String getDepositStatus(int status){

        String string=null;
        switch (status){
            case 1:
                string="租借中";
                break;
            case 2:
                string="已完成";
                break;
            case 3:
                string="订单超时";
                break;
            case 4:
                string="异常";
                break;

        }

        return string;
    }

    public static String getOrderStatus(String status){

        String string=null;
        switch (status){
            case "1":
                string="租借中";
                break;
            case "2":
                string="已完成";
                break;
            case "3":
                string="订单超时";
                break;
            case "4":
                string="异常";
                break;

        }

        return string;
    }

    public static String getCashStatus(String status){

        String string=null;
        switch (status){
            case "1":
                string="提现中";
                break;
            case "2":
                string="提现成功";
                break;
            case "3":
                string="提现失败";
                break;



        }

        return string;
    }


    public static String getOrderType(String type){
        String string=null;
        switch (type){
            case "1":
                string="充电宝";
                break;
            case "2":
                string="充电线";
                break;


        }

        return string;
    }
}
