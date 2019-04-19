package com.yd.youd.model;

/**
 * Created by PP on 2019/4/19.
 */
public class HomeBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"month_income":"3500.82","day_income":"372.00","invalid_withdraw":"7210.00","day_order_num":"102","using_equipment":"23","standby_equipment":"37"}
     */

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * month_income : 3500.82
         * day_income : 372.00
         * invalid_withdraw : 7210.00
         * day_order_num : 102
         * using_equipment : 23
         * standby_equipment : 37
         */

        public String month_income;
        public String day_income;
        public String invalid_withdraw;
        public String day_order_num;
        public String using_equipment;
        public String standby_equipment;
    }
}
