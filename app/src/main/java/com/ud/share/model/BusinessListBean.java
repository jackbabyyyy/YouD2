package com.ud.share.model;

import java.util.List;

/**
 * Created by PP on 2019/3/15.
 */
public class BusinessListBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"total":"37","per_page":"10","current_page":"4","last_page":"4","data":[{"sid":"46","shop_name":"沈御堂","longitude":"121.134223937988280","latitude":"30.190285941371680","mobile":"18203882656","area":"","line_time_list":"2|3|4","addr":"慈溪市周巷周西公路185号","picture":"https://udc-1258818913.cos.ap-shanghai.myqcloud.com/operator/Seller/20190426/681e5c9fa64371c66f1b47bfc62b8d50.JPG","addtime":"1556247223","shopstart":"00:00","shopend":"00:00","contacts":"许强","line_rate":"30","cabinet_rate":"30","total_rent":"436.00","business_hours":"00:00-00:00","ctime":"2019-04-26 10:53","distance":"14公里","cabinet_count":"0","chargingline_count":"20","line_hour_2":"2","line_hour_6":"3","line_hour_12":"4"},{"sid":"45","shop_name":"临时商户","longitude":"121.252069473266600","latitude":"30.234524872909976","mobile":"15857447222","area":"","line_time_list":"2|3|4","addr":"慈溪坎墩","picture":"https://udc-1258818913.cos.ap-shanghai.myqcloud.com/operator/Seller/20190426/4b934ccdb159dda5c82cb2262704a507.JPG","addtime":"1556246169","shopstart":"01:30","shopend":"00:00","contacts":"666","line_rate":"2","cabinet_rate":"2","total_rent":"58.00","business_hours":"01:30-00:00","ctime":"2019-04-26 10:36","distance":"5公里","cabinet_count":"0","chargingline_count":"1155","line_hour_2":"2","line_hour_6":"3","line_hour_12":"4"},{"sid":"44","shop_name":"足御堂","longitude":"121.306550502777100","latitude":"30.174125406972003","mobile":"15381959950","area":"","line_time_list":"2|3|4","addr":"逍林","picture":"https://udc-1258818913.cos.ap-shanghai.myqcloud.com/operator/Seller/20190425/ea7eef9200950d21720dd0141bcec4a5.JPG","addtime":"1556168136","shopstart":"00:00","shopend":"00:00","contacts":"老板","line_rate":"30","cabinet_rate":"30","total_rent":"189.50","business_hours":"00:00-00:00","ctime":"2019-04-25 12:55","distance":"4公里","cabinet_count":"0","chargingline_count":"12","line_hour_2":"2","line_hour_6":"3","line_hour_12":"4"},{"sid":"43","shop_name":"富海足浴店","longitude":"121.229496002197270","latitude":"30.329620019722665","mobile":"18815231851","area":"","line_time_list":"2|3|4","addr":"杭州湾新区","picture":"https://udc-1258818913.cos.ap-shanghai.myqcloud.com/operator/Seller/20190425/2db07b3fb595d51929ebaec0cea95182.JPG","addtime":"1556167939","shopstart":"00:00","shopend":"00:00","contacts":"蒋","line_rate":"35","cabinet_rate":"35","total_rent":"163.00","business_hours":"00:00-00:00","ctime":"2019-04-25 12:52","distance":"16公里","cabinet_count":"0","chargingline_count":"17","line_hour_2":"2","line_hour_6":"3","line_hour_12":"4"},{"sid":"42","shop_name":"七重天孙塘南路店","longitude":"121.256822347640990","latitude":"30.153282056242620","mobile":"13858323988","area":"","line_time_list":"2|3|4","addr":"慈溪市孙塘南路24号","picture":"https://udc-1258818913.cos.ap-shanghai.myqcloud.com/operator/Seller/20190425/0f6d8a4365ecff83385d63caffbb9b84.JPG","addtime":"1556167755","shopstart":"00:00","shopend":"00:00","contacts":"经理","line_rate":"30","cabinet_rate":"30","total_rent":"172.00","business_hours":"00:00-00:00","ctime":"2019-04-25 12:49","distance":"5公里","cabinet_count":"0","chargingline_count":"20","line_hour_2":"2","line_hour_6":"3","line_hour_12":"4"},{"sid":"41","shop_name":"蓝贵人足浴店","longitude":"121.117057800292970","latitude":"30.175131750064350","mobile":"15968021266","area":"","line_time_list":"2|3|4","addr":"慈溪市周巷镇环城西路374号","picture":"https://udc-1258818913.cos.ap-shanghai.myqcloud.com/operator/Seller/20190425/1a9c3b3ca7afde797a5dc4f6974ee45e.JPG","addtime":"1556167563","shopstart":"00:00","shopend":"00:00","contacts":"老板","line_rate":"30","cabinet_rate":"30","total_rent":"56.00","business_hours":"00:00-00:00","ctime":"2019-04-25 12:46","distance":"16公里","cabinet_count":"0","chargingline_count":"10","line_hour_2":"2","line_hour_6":"3","line_hour_12":"4"}]}
     */

    public int code;
    public String msg;
    public DataBeanX data;

    public static class DataBeanX {
        /**
         * total : 37
         * per_page : 10
         * current_page : 4
         * last_page : 4
         * data : [{"sid":"46","shop_name":"沈御堂","longitude":"121.134223937988280","latitude":"30.190285941371680","mobile":"18203882656","area":"","line_time_list":"2|3|4","addr":"慈溪市周巷周西公路185号","picture":"https://udc-1258818913.cos.ap-shanghai.myqcloud.com/operator/Seller/20190426/681e5c9fa64371c66f1b47bfc62b8d50.JPG","addtime":"1556247223","shopstart":"00:00","shopend":"00:00","contacts":"许强","line_rate":"30","cabinet_rate":"30","total_rent":"436.00","business_hours":"00:00-00:00","ctime":"2019-04-26 10:53","distance":"14公里","cabinet_count":"0","chargingline_count":"20","line_hour_2":"2","line_hour_6":"3","line_hour_12":"4"},{"sid":"45","shop_name":"临时商户","longitude":"121.252069473266600","latitude":"30.234524872909976","mobile":"15857447222","area":"","line_time_list":"2|3|4","addr":"慈溪坎墩","picture":"https://udc-1258818913.cos.ap-shanghai.myqcloud.com/operator/Seller/20190426/4b934ccdb159dda5c82cb2262704a507.JPG","addtime":"1556246169","shopstart":"01:30","shopend":"00:00","contacts":"666","line_rate":"2","cabinet_rate":"2","total_rent":"58.00","business_hours":"01:30-00:00","ctime":"2019-04-26 10:36","distance":"5公里","cabinet_count":"0","chargingline_count":"1155","line_hour_2":"2","line_hour_6":"3","line_hour_12":"4"},{"sid":"44","shop_name":"足御堂","longitude":"121.306550502777100","latitude":"30.174125406972003","mobile":"15381959950","area":"","line_time_list":"2|3|4","addr":"逍林","picture":"https://udc-1258818913.cos.ap-shanghai.myqcloud.com/operator/Seller/20190425/ea7eef9200950d21720dd0141bcec4a5.JPG","addtime":"1556168136","shopstart":"00:00","shopend":"00:00","contacts":"老板","line_rate":"30","cabinet_rate":"30","total_rent":"189.50","business_hours":"00:00-00:00","ctime":"2019-04-25 12:55","distance":"4公里","cabinet_count":"0","chargingline_count":"12","line_hour_2":"2","line_hour_6":"3","line_hour_12":"4"},{"sid":"43","shop_name":"富海足浴店","longitude":"121.229496002197270","latitude":"30.329620019722665","mobile":"18815231851","area":"","line_time_list":"2|3|4","addr":"杭州湾新区","picture":"https://udc-1258818913.cos.ap-shanghai.myqcloud.com/operator/Seller/20190425/2db07b3fb595d51929ebaec0cea95182.JPG","addtime":"1556167939","shopstart":"00:00","shopend":"00:00","contacts":"蒋","line_rate":"35","cabinet_rate":"35","total_rent":"163.00","business_hours":"00:00-00:00","ctime":"2019-04-25 12:52","distance":"16公里","cabinet_count":"0","chargingline_count":"17","line_hour_2":"2","line_hour_6":"3","line_hour_12":"4"},{"sid":"42","shop_name":"七重天孙塘南路店","longitude":"121.256822347640990","latitude":"30.153282056242620","mobile":"13858323988","area":"","line_time_list":"2|3|4","addr":"慈溪市孙塘南路24号","picture":"https://udc-1258818913.cos.ap-shanghai.myqcloud.com/operator/Seller/20190425/0f6d8a4365ecff83385d63caffbb9b84.JPG","addtime":"1556167755","shopstart":"00:00","shopend":"00:00","contacts":"经理","line_rate":"30","cabinet_rate":"30","total_rent":"172.00","business_hours":"00:00-00:00","ctime":"2019-04-25 12:49","distance":"5公里","cabinet_count":"0","chargingline_count":"20","line_hour_2":"2","line_hour_6":"3","line_hour_12":"4"},{"sid":"41","shop_name":"蓝贵人足浴店","longitude":"121.117057800292970","latitude":"30.175131750064350","mobile":"15968021266","area":"","line_time_list":"2|3|4","addr":"慈溪市周巷镇环城西路374号","picture":"https://udc-1258818913.cos.ap-shanghai.myqcloud.com/operator/Seller/20190425/1a9c3b3ca7afde797a5dc4f6974ee45e.JPG","addtime":"1556167563","shopstart":"00:00","shopend":"00:00","contacts":"老板","line_rate":"30","cabinet_rate":"30","total_rent":"56.00","business_hours":"00:00-00:00","ctime":"2019-04-25 12:46","distance":"16公里","cabinet_count":"0","chargingline_count":"10","line_hour_2":"2","line_hour_6":"3","line_hour_12":"4"}]
         */

        public String total;
        public String per_page;
        public String current_page;
        public String last_page;
        public List<DataBean> data;

        public static class DataBean {
            /**
             * sid : 46
             * shop_name : 沈御堂
             * longitude : 121.134223937988280
             * latitude : 30.190285941371680
             * mobile : 18203882656
             * area :
             * line_time_list : 2|3|4
             * addr : 慈溪市周巷周西公路185号
             * picture : https://udc-1258818913.cos.ap-shanghai.myqcloud.com/operator/Seller/20190426/681e5c9fa64371c66f1b47bfc62b8d50.JPG
             * addtime : 1556247223
             * shopstart : 00:00
             * shopend : 00:00
             * contacts : 许强
             * line_rate : 30
             * cabinet_rate : 30
             * total_rent : 436.00
             * business_hours : 00:00-00:00
             * ctime : 2019-04-26 10:53
             * distance : 14公里
             * cabinet_count : 0
             * chargingline_count : 20
             * line_hour_2 : 2
             * line_hour_6 : 3
             * line_hour_12 : 4
             */

            public String sid;
            public String shop_name;
            public String longitude;
            public String latitude;
            public String mobile;
            public String area;
            public String line_time_list;
            public String addr;
            public String picture;
            public String addtime;
            public String shopstart;
            public String shopend;
            public String contacts;
            public String line_rate;
            public String cabinet_rate;
            public String total_rent;
            public String business_hours;
            public String ctime;
            public String distance;
            public String cabinet_count;
            public String chargingline_count;
            public String line_hour_2;
            public String line_hour_6;
            public String line_hour_12;
        }
    }
}
