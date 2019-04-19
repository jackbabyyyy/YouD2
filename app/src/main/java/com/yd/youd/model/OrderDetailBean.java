package com.yd.youd.model;

/**
 * Created by PP on 2019/4/12.
 */
public class OrderDetailBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"id":"555","seller_name":"租借Y 悠电充电宝","starttime":"1546687795","endtime":"1546687814","tariff_type":"前1个小时,每1小时/1.00元超过1小时到24时封顶10元","device_sn":"RL1B091810000031","device_type":"RL2B89500770","source_type":"0","Profit":"0"}
     */

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * id : 555
         * seller_name : 租借Y 悠电充电宝
         * starttime : 1546687795
         * endtime : 1546687814
         * tariff_type : 前1个小时,每1小时/1.00元超过1小时到24时封顶10元
         * device_sn : RL1B091810000031
         * device_type : RL2B89500770
         * source_type : 0
         * Profit : 0
         */

        public String id;
        public String seller_name;
        public String starttime;
        public String endtime;
        public String tariff_type;
        public String device_sn;
        public String device_type;
        public String source_type;
        public String Profit;
    }
}
