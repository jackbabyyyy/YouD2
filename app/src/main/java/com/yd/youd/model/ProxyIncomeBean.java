package com.yd.youd.model;

/**
 * Created by PP on 2019/3/14.
 */
public class ProxyIncomeBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"sum_agents":"2","total_rent":"0"}
     */

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * sum_agents : 2
         * total_rent : 0
         */

        public String sum_agents;
        public String total_rent;
    }
}
