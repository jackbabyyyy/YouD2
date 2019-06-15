package com.ud.share.model;

import java.util.List;

/**
 * Created by PP on 2019/4/18.
 */
public class CashBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"total":1,"per_page":"20","current_page":1,"last_page":1,"total_amount":"220.00","data":[{"order_num":"5201547540459","type":"1","addtime":"2019-01-15 16:20:59","amount":"10.00","status":"1","notes":"姓名与卡号不符"}]}
     */

    public int code;
    public String msg;
    public DataBeanX data;

    public static class DataBeanX {
        /**
         * total : 1
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * total_amount : 220.00
         * data : [{"order_num":"5201547540459","type":"1","addtime":"2019-01-15 16:20:59","amount":"10.00","status":"1","notes":"姓名与卡号不符"}]
         */

        public int total;
        public String per_page;
        public int current_page;
        public int last_page;
        public String total_amount;
        public List<DataBean> data;

        public static class DataBean {
            /**
             * order_num : 5201547540459
             * type : 1
             * addtime : 2019-01-15 16:20:59
             * amount : 10.00
             * status : 1
             * notes : 姓名与卡号不符
             */

            public String order_num;
            public String type;
            public String addtime;
            public String amount;
            public String status;
            public String notes;
        }
    }
}
