package com.ud.share.model;

/**
 * Created by PP on 2019/4/16.
 */
public class CashGetBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"available_amount":"21.190","freezing_amount":"0.000","deposit":"500.00","service_fee":"3.00"}
     */

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * available_amount : 21.190
         * freezing_amount : 0.000
         * deposit : 500.00
         * service_fee : 3.00
         */

        public String available_amount;
        public String freezing_amount;
        public String deposit;
        public String service_fee;
    }
}
