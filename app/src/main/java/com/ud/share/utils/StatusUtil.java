package com.ud.share.utils;

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

    public static String getPayType(String type){
        String string=null;
        switch (type){
            case "0":
                string="余额";
                break;
            case "1":
                string="微信";
                break;
            case "2":
                string="支付宝";
                break;
            case "3":
                string="app";
                break;
            case "4":
                string="wap";
                break;


        }

        return string;
    }

    public static String getCashType(int type){
        String string=null;
        switch (type){
            case 0:
                string="全部";
                break;
            case 1:
                string="提现中";
                break;
            case 2:
                string="提现成功";
                break;
            case 3:
                string="提现失败";
                break;


        }

        return string;
    }

}
