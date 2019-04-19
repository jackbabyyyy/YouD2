package com.yd.youd.model;

import java.util.List;
/**
 * Created by PP on 2019/3/13.
 */
public class ProxyListBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"total":"3","per_page":"15","current_page":"1","last_page":"1","data":[{"agent_id":"149","level_type":"1","avatar":"http://ap.ud-share.cn:112images/a1.png","real_name":"徐杰","deposit":"1.00","phone":"15057447333","line_rate":"80","cabinet_rate":"90","status":"1","addtime":"1547723205","agent_code":"3eei0m","parent_agent":"145","agent_type":"1","is_onchange":"1","ctime":"2019-01-17 19:06","sum_agents":"0","total_rent":"0","equipment":[{"model":"XA05","total":"1","distribution":"还未铺货"}]},{"agent_id":"147","level_type":"1","avatar":"http://ap.ud-share.cn:112images/a1.png","real_name":"冯骏","deposit":"0.00","phone":"18355556669","line_rate":"11","cabinet_rate":"11","status":"1","addtime":"1547716912","agent_code":"7g5wty","parent_agent":"146","agent_type":"1","is_onchange":"0","ctime":"2019-01-17 17:21","sum_agents":"0","total_rent":"0","equipment":[{"model":"XA05","total":"0","distribution":"还未铺货"}]},{"agent_id":"146","level_type":"1","avatar":"http://ap.ud-share.cn:112images/a1.png","real_name":"王龙","deposit":"600.00","phone":"18358218008","line_rate":"90","cabinet_rate":"90","status":"1","addtime":"1547716221","agent_code":"vennie","parent_agent":"145","agent_type":"1","is_onchange":"1","ctime":"2019-01-17 17:10","sum_agents":"1","total_rent":"0","equipment":[{"model":"XA05","total":"1","distribution":"还未铺货"}]}]}
     */

    public int code;
    public String msg;
    public DataBeanX data;

    public static class DataBeanX {
        /**
         * total : 3
         * per_page : 15
         * current_page : 1
         * last_page : 1
         * data : [{"agent_id":"149","level_type":"1","avatar":"http://ap.ud-share.cn:112images/a1.png","real_name":"徐杰","deposit":"1.00","phone":"15057447333","line_rate":"80","cabinet_rate":"90","status":"1","addtime":"1547723205","agent_code":"3eei0m","parent_agent":"145","agent_type":"1","is_onchange":"1","ctime":"2019-01-17 19:06","sum_agents":"0","total_rent":"0","equipment":[{"model":"XA05","total":"1","distribution":"还未铺货"}]},{"agent_id":"147","level_type":"1","avatar":"http://ap.ud-share.cn:112images/a1.png","real_name":"冯骏","deposit":"0.00","phone":"18355556669","line_rate":"11","cabinet_rate":"11","status":"1","addtime":"1547716912","agent_code":"7g5wty","parent_agent":"146","agent_type":"1","is_onchange":"0","ctime":"2019-01-17 17:21","sum_agents":"0","total_rent":"0","equipment":[{"model":"XA05","total":"0","distribution":"还未铺货"}]},{"agent_id":"146","level_type":"1","avatar":"http://ap.ud-share.cn:112images/a1.png","real_name":"王龙","deposit":"600.00","phone":"18358218008","line_rate":"90","cabinet_rate":"90","status":"1","addtime":"1547716221","agent_code":"vennie","parent_agent":"145","agent_type":"1","is_onchange":"1","ctime":"2019-01-17 17:10","sum_agents":"1","total_rent":"0","equipment":[{"model":"XA05","total":"1","distribution":"还未铺货"}]}]
         */

        public String total;
        public String per_page;
        public String current_page;
        public String last_page;
        public List<DataBean> data;

        public static class DataBean {
            /**
             * agent_id : 149
             * level_type : 1
             * avatar : http://ap.ud-share.cn:112images/a1.png
             * real_name : 徐杰
             * deposit : 1.00
             * phone : 15057447333
             * line_rate : 80
             * cabinet_rate : 90
             * status : 1
             * addtime : 1547723205
             * agent_code : 3eei0m
             * parent_agent : 145
             * agent_type : 1
             * is_onchange : 1
             * ctime : 2019-01-17 19:06
             * sum_agents : 0
             * total_rent : 0
             * equipment : [{"model":"XA05","total":"1","distribution":"还未铺货"}]
             */

            public String agent_id;
            public String level_type;
            public String avatar;
            public String real_name;
            public String deposit;
            public String phone;
            public String line_rate;
            public String cabinet_rate;
            public String status;
            public String addtime;
            public String agent_code;
            public String parent_agent;
            public String agent_type;
            public String is_onchange;
            public String ctime;
            public String sum_agents;
            public String total_rent;
            public List<EquipmentBean> equipment;

            public static class EquipmentBean {
                /**
                 * model : XA05
                 * total : 1
                 * distribution : 还未铺货
                 */

                public String model;
                public String total;
                public String distribution;
            }
        }
    }
}
