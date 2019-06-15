package com.ud.share.model;

import java.util.List;

/**
 * Created by PP on 2019/4/18.
 */
public class DeviceListBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"total":5,"per_page":20,"current_page":1,"last_page":1,"data":[{"sn":"X2019D00096","is_distribution":"0","shop_name":""},{"sn":"X2019D00098","is_distribution":"1","shop_name":"测试商户"},{"sn":"X2019D00099","is_distribution":"0","shop_name":""},{"sn":"X2019D00100","is_distribution":"1","shop_name":"测试商户"}]}
     */

    public int code;
    public String msg;
    public DataBeanX data;

    public static class DataBeanX {
        /**
         * total : 5
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"sn":"X2019D00096","is_distribution":"0","shop_name":""},{"sn":"X2019D00098","is_distribution":"1","shop_name":"测试商户"},{"sn":"X2019D00099","is_distribution":"0","shop_name":""},{"sn":"X2019D00100","is_distribution":"1","shop_name":"测试商户"}]
         */

        public String total;
        public String per_page;
        public String current_page;
        public String last_page;
        public List<DataBean> data;

        public static class DataBean {
            /**
             * sn : X2019D00096
             * is_distribution : 0
             * shop_name :
             */

            public String sn;
            public String is_distribution;
            public String shop_name;
            public boolean check;//附加
        }
    }
}
