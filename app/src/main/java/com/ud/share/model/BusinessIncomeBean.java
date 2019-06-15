package com.ud.share.model;

/**
 * Created by PP on 2019/3/22.
 */
public class BusinessIncomeBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"sum_sellers":"2","total_rent":"65.00"}
     */

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * sum_sellers : 2
         * total_rent : 65.00
         */

        public String sum_sellers;
        public String total_rent;
    }
}
