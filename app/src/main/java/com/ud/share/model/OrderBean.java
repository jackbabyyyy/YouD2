package com.ud.share.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PP on 2019/4/12.
 */
public class OrderBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"list":[{"id":"1024","order_no":"5011903171505234775","status":"2","starttime":"2019-03-17 15:05:23","endtime":"2019-03-17 15:06:23","equipment_type":"2","seller_name":"dfga","device_code":"XA05","profit":"0"},{"id":"1021","order_no":"5011903171458471691","status":"2","starttime":"2019-03-17 14:58:47","endtime":"2019-03-17 14:59:47","equipment_type":"2","seller_name":"dfga","device_code":"XA05","profit":"0"},{"id":"1019","order_no":"5011903171456191978","status":"2","starttime":"2019-03-17 14:56:19","endtime":"2019-03-17 14:57:19","equipment_type":"2","seller_name":"dfga","device_code":"XA05","profit":"0"},{"id":"1017","order_no":"5011903171452417283","status":"2","starttime":"2019-03-17 14:52:41","endtime":"2019-03-17 14:53:41","equipment_type":"2","seller_name":"dfga","device_code":"XA05","profit":"0"},{"id":"1016","order_no":"5011903161842465886","status":"2","starttime":"2019-03-16 18:42:46","endtime":"2019-03-16 18:43:46","equipment_type":"2","seller_name":"dfga","device_code":"XA05","profit":"0"}],"total":"11","order_num":{"all":"11","loding":"0","complete":"11","overtime":"0","abnormal":"0"},"order_num_1":["11","0","11","0","0"]}
     */

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * list : [{"id":"1024","order_no":"5011903171505234775","status":"2","starttime":"2019-03-17 15:05:23","endtime":"2019-03-17 15:06:23","equipment_type":"2","seller_name":"dfga","device_code":"XA05","profit":"0"},{"id":"1021","order_no":"5011903171458471691","status":"2","starttime":"2019-03-17 14:58:47","endtime":"2019-03-17 14:59:47","equipment_type":"2","seller_name":"dfga","device_code":"XA05","profit":"0"},{"id":"1019","order_no":"5011903171456191978","status":"2","starttime":"2019-03-17 14:56:19","endtime":"2019-03-17 14:57:19","equipment_type":"2","seller_name":"dfga","device_code":"XA05","profit":"0"},{"id":"1017","order_no":"5011903171452417283","status":"2","starttime":"2019-03-17 14:52:41","endtime":"2019-03-17 14:53:41","equipment_type":"2","seller_name":"dfga","device_code":"XA05","profit":"0"},{"id":"1016","order_no":"5011903161842465886","status":"2","starttime":"2019-03-16 18:42:46","endtime":"2019-03-16 18:43:46","equipment_type":"2","seller_name":"dfga","device_code":"XA05","profit":"0"}]
         * total : 11
         * order_num : {"all":"11","loding":"0","complete":"11","overtime":"0","abnormal":"0"}
         * order_num_1 : ["11","0","11","0","0"]
         */

        public String total;
        public OrderNumBean order_num;
        public List<ListBean> list=new ArrayList<>();
        public List<String> order_num_1=new ArrayList<>();

        public static class OrderNumBean {
            /**
             * all : 11
             * loding : 0
             * complete : 11
             * overtime : 0
             * abnormal : 0
             */

            public String all="0";
            public String loding="0";
            public String complete="0";
            public String overtime="0";
            public String abnormal="0";
        }

        public static class ListBean {
            /**
             * id : 1024
             * order_no : 5011903171505234775
             * status : 2
             * starttime : 2019-03-17 15:05:23
             * endtime : 2019-03-17 15:06:23
             * equipment_type : 2
             * seller_name : dfga
             * device_code : XA05
             * profit : 0
             */

            public String id;
            public String order_no;
            public String status;
            public String starttime;
            public String endtime;
            public String equipment_type;
            public String seller_name;
            public String device_code;
            public String profit;
        }
    }
}
