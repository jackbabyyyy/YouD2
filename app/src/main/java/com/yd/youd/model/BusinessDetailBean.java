package com.yd.youd.model;

import java.util.List;

/**
 * Created by PP on 2019/4/19.
 */
public class BusinessDetailBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"seller":{"sid":"84","name":"权哥养生馆","contacts":"炳哥","mobile":"13124042212","picture":"https://gxcdb-1258415411.cos.ap-guangzhou.myqcloud.com/operator/Seller/20190112/1710f1e4f0c2ba4d08ed1c468008af03.jpg","shopstart":"00:30","shopend":"22:30","addr":"123","city":"210100","province":"210000","line_rate":"0","cabinet_rate":"0","per_price":"1.00","per_ceiling":"10.00","business_hours":"00:30-22:30","charge_mode_one":"1","charge_mode_two":"3","charge_mode_three":"5"},"charecabinet":{"充电宝所在机柜号":"充电宝ID","RL1B091810000043":["RL2B89000907","RL2B89000721","RL2B89000721"]},"line_charging":{"charging_id":"RL12345000005669003"}}
     */

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * seller : {"sid":"84","name":"权哥养生馆","contacts":"炳哥","mobile":"13124042212","picture":"https://gxcdb-1258415411.cos.ap-guangzhou.myqcloud.com/operator/Seller/20190112/1710f1e4f0c2ba4d08ed1c468008af03.jpg","shopstart":"00:30","shopend":"22:30","addr":"123","city":"210100","province":"210000","line_rate":"0","cabinet_rate":"0","per_price":"1.00","per_ceiling":"10.00","business_hours":"00:30-22:30","charge_mode_one":"1","charge_mode_two":"3","charge_mode_three":"5"}
         * charecabinet : {"充电宝所在机柜号":"充电宝ID","RL1B091810000043":["RL2B89000907","RL2B89000721","RL2B89000721"]}
         * line_charging : {"charging_id":"RL12345000005669003"}
         */

        public SellerBean seller;
        public CharecabinetBean charecabinet;
        public LineChargingBean line_charging;

        public static class SellerBean {
            /**
             * sid : 84
             * name : 权哥养生馆
             * contacts : 炳哥
             * mobile : 13124042212
             * picture : https://gxcdb-1258415411.cos.ap-guangzhou.myqcloud.com/operator/Seller/20190112/1710f1e4f0c2ba4d08ed1c468008af03.jpg
             * shopstart : 00:30
             * shopend : 22:30
             * addr : 123
             * city : 210100
             * province : 210000
             * line_rate : 0
             * cabinet_rate : 0
             * per_price : 1.00
             * per_ceiling : 10.00
             * business_hours : 00:30-22:30
             * charge_mode_one : 1
             * charge_mode_two : 3
             * charge_mode_three : 5
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
            public String line_rate;
            public String cabinet_rate;
            public String per_price;
            public String per_ceiling;
            public String business_hours;
            public String charge_mode_one;
            public String charge_mode_two;
            public String charge_mode_three;
        }

        public static class CharecabinetBean {
            /**
             * 充电宝所在机柜号 : 充电宝ID
             * RL1B091810000043 : ["RL2B89000907","RL2B89000721","RL2B89000721"]
             */

            public String 充电宝所在机柜号;
            public List<String> RL1B091810000043;
        }

        public static class LineChargingBean {
            /**
             * charging_id : RL12345000005669003
             */

            public String charging_id;
        }
    }
}
