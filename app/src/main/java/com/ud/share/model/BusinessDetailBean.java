package com.ud.share.model;

import java.util.List;

/**
 * Created by PP on 2019/4/19.
 */
public class BusinessDetailBean {
    /**
     * code : 1
     * msg : 操作成功
     * data : {"seller":{"sid":"5847","name":"浙江锦徽旅游用品商行","contacts":"汪**","mobile":"182****8918","picture":"http://ap.ud-share.cn:112images/a1.png","shopstart":"00:00","shopend":"00:00","addr":"杭州市余杭区天山路256号","city":"","province":"","line_rate":"","cabinet_rate":"","per_price":"0.00","per_ceiling":"0.00","line_time_list":"","business_hours":"00:00-00:00","line_hour_2":"","line_hour_6":"3.00","line_hour_12":"5.00"},"charecabinet":[{"RL1B091810000043":["RL2B89000907","RL2B89000721","RL2B89000721"]}],"line_charging":[{"charging_id":"X1118A01017"},{"charging_id":"X1118A01011"},{"charging_id":"X1118A01003"},{"charging_id":"X1118A01000"},{"charging_id":"X1118A00994"},{"charging_id":"X1118A00987"}]}
     */

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * seller : {"sid":"5847","name":"浙江锦徽旅游用品商行","contacts":"汪**","mobile":"182****8918","picture":"http://ap.ud-share.cn:112images/a1.png","shopstart":"00:00","shopend":"00:00","addr":"杭州市余杭区天山路256号","city":"","province":"","line_rate":"","cabinet_rate":"","per_price":"0.00","per_ceiling":"0.00","line_time_list":"","business_hours":"00:00-00:00","line_hour_2":"","line_hour_6":"3.00","line_hour_12":"5.00"}
         * charecabinet : [{"RL1B091810000043":["RL2B89000907","RL2B89000721","RL2B89000721"]}]
         * line_charging : [{"charging_id":"X1118A01017"},{"charging_id":"X1118A01011"},{"charging_id":"X1118A01003"},{"charging_id":"X1118A01000"},{"charging_id":"X1118A00994"},{"charging_id":"X1118A00987"}]
         */

        public SellerBean seller;
        public List<CharecabinetBean> charecabinet;
        public List<LineChargingBean> line_charging;

        public static class SellerBean {
            /**
             * sid : 5847
             * name : 浙江锦徽旅游用品商行
             * contacts : 汪**
             * mobile : 182****8918
             * picture : http://ap.ud-share.cn:112images/a1.png
             * shopstart : 00:00
             * shopend : 00:00
             * addr : 杭州市余杭区天山路256号
             * city :
             * province :
             * line_rate :
             * cabinet_rate :
             * per_price : 0.00
             * per_ceiling : 0.00
             * line_time_list :
             * business_hours : 00:00-00:00
             * line_hour_2 :
             * line_hour_6 : 3.00
             * line_hour_12 : 5.00
             */

            public String sid;
            public String name;
            public String contacts;
            public String mobile;
            public String picture;
            public String shopstart;
            public String shopend;
            public String addr;
            public String city;
            public String province;
            public String area;
            public String line_rate;
            public String cabinet_rate;
            public String per_price;
            public String per_ceiling;
            public String line_time_list;
            public String business_hours;
            public String line_hour_2;
            public String line_hour_6;
            public String line_hour_12;
        }

        public static class CharecabinetBean {
            public List<String> RL1B091810000043;
        }

        public static class LineChargingBean {
            /**
             * charging_id : X1118A01017
             */

            public String charging_id;
        }
    }



}
