package com.yd.youd.model;

import java.util.List;

/**
 * Created by PP on 2019/3/15.
 */
public class BusinessListBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"total":"2","per_page":"15","current_page":"1","last_page":"1","data":[{"sid":"85","shop_name":"测试商户","mobile":"13003940560","addr":"思明区思明南路软件园望海路47号楼之二 202-9","contacts":"童章舫","line_rate":"0%","cabinet_rate":"0%","total_rent":"0.00","business_hours":"00:00-20:00"},{"sid":"82","shop_name":"无运营","mobile":"15960899934","addr":"23","contacts":"无运营","line_rate":"0%","cabinet_rate":"0%","total_rent":"65.00","business_hours":"00:30-24:00"}]}
     */

    public int code;
    public String msg;
    public DataBeanX data;

    public static class DataBeanX {
        /**
         * total : 2
         * per_page : 15
         * current_page : 1
         * last_page : 1
         * data : [{"sid":"85","shop_name":"测试商户","mobile":"13003940560","addr":"思明区思明南路软件园望海路47号楼之二 202-9","contacts":"童章舫","line_rate":"0%","cabinet_rate":"0%","total_rent":"0.00","business_hours":"00:00-20:00"},{"sid":"82","shop_name":"无运营","mobile":"15960899934","addr":"23","contacts":"无运营","line_rate":"0%","cabinet_rate":"0%","total_rent":"65.00","business_hours":"00:30-24:00"}]
         */

        public String total;
        public String per_page;
        public String current_page;
        public String last_page;
        public List<DataBean> data;

        public static class DataBean {
            /**
             * sid : 85
             * shop_name : 测试商户
             * mobile : 13003940560
             * addr : 思明区思明南路软件园望海路47号楼之二 202-9
             * contacts : 童章舫
             * line_rate : 0%
             * cabinet_rate : 0%
             * total_rent : 0.00
             * business_hours : 00:00-20:00
             */

            public String sid;
            public String shop_name;
            public String mobile;
            public String addr;
            public String contacts;
            public String line_rate;
            public String cabinet_rate;
            public String total_rent;
            public String business_hours;
        }
    }
}
