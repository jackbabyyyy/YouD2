package com.ud.share.model;

/**
 * Created by PP on 2019/4/19.
 */
public class ImgBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"url":"https://gxcdb-1258415411.cos.ap-guangzhou.myqcloud.com/operator/Seller/20190319/8dd1ac739d051805d40c982c577ff369.png","picture":"/operator/Seller/20190319/8dd1ac739d051805d40c982c577ff369.png"}
     */

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * url : https://gxcdb-1258415411.cos.ap-guangzhou.myqcloud.com/operator/Seller/20190319/8dd1ac739d051805d40c982c577ff369.png
         * picture : /operator/Seller/20190319/8dd1ac739d051805d40c982c577ff369.png
         */

        public String url;
        public String picture;
    }
}
