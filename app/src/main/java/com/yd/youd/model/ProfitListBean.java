package com.yd.youd.model;

import java.util.List;

/**
 * Created by PP on 2019/4/20.
 */
public class ProfitListBean {

    /**
     * code : 1
     * msg : 操作成功
     * data : {"total":4,"per_page":20,"current_page":1,"last_page":1,"data":[{"profit":"0.002","order_id":"805","order_no":"5011902221845212114","starttime":"2019-02-22 18:45:21","endtime":"2019-02-22 20:45:21","shop_name":"权哥养生馆","model":"XA05"},{"profit":"0.002","order_id":"806","order_no":"5011902231441526781","starttime":"2019-02-23 14:41:52","endtime":"2019-02-23 16:41:52","shop_name":"权哥养生馆","model":"XA05"},{"profit":"0.002","order_id":"808","order_no":"5011902271009466562","starttime":"2019-02-27 10:09:46","endtime":"2019-02-27 12:09:46","shop_name":"权哥养生馆","model":"XA05"},{"profit":"0.002","order_id":"812","order_no":"5011902280851389226","starttime":"2019-02-28 08:51:38","endtime":"2019-02-28 10:51:38","shop_name":"权哥养生馆","model":"XA05"}]}
     */

    public int code;
    public String msg;
    public DataBeanX data;

    public static class DataBeanX {
        /**
         * total : 4
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"profit":"0.002","order_id":"805","order_no":"5011902221845212114","starttime":"2019-02-22 18:45:21","endtime":"2019-02-22 20:45:21","shop_name":"权哥养生馆","model":"XA05"},{"profit":"0.002","order_id":"806","order_no":"5011902231441526781","starttime":"2019-02-23 14:41:52","endtime":"2019-02-23 16:41:52","shop_name":"权哥养生馆","model":"XA05"},{"profit":"0.002","order_id":"808","order_no":"5011902271009466562","starttime":"2019-02-27 10:09:46","endtime":"2019-02-27 12:09:46","shop_name":"权哥养生馆","model":"XA05"},{"profit":"0.002","order_id":"812","order_no":"5011902280851389226","starttime":"2019-02-28 08:51:38","endtime":"2019-02-28 10:51:38","shop_name":"权哥养生馆","model":"XA05"}]
         */

        public int total;
        public int per_page;
        public int current_page;
        public int last_page;
        public List<DataBean> data;

        public static class DataBean {
            /**
             * profit : 0.002
             * order_id : 805
             * order_no : 5011902221845212114
             * starttime : 2019-02-22 18:45:21
             * endtime : 2019-02-22 20:45:21
             * shop_name : 权哥养生馆
             * model : XA05
             */

            public String profit;
            public String order_id;
            public String order_no;
            public String starttime;
            public String endtime;
            public String shop_name;
            public String model;
        }
    }
}
