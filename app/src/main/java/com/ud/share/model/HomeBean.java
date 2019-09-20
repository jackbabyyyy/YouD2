package com.ud.share.model;

/**
 * Created by PP on 2019/4/19.
 */
public class HomeBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"month_income":"0.00","day_income":"0.00","invalid_withdraw":"31.69","day_order_num":"0","using_equipment":"0","standby_equipment":"0","wt":"https://api.ud-share.cn/app/freezingAmount.html"}
     */

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * month_income : 0.00
         * day_income : 0.00
         * invalid_withdraw : 31.69
         * day_order_num : 0
         * using_equipment : 0
         * standby_equipment : 0
         * wt : https://api.ud-share.cn/app/freezingAmount.html
         */

        public String month_income;
        public String day_income;
        public String invalid_withdraw;
        public String day_order_num;
        public String using_equipment;
        public String standby_equipment;
        public String wt;
    }
}



