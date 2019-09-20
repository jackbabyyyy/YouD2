package com.ud.share.model;

/**
 * Created by PP on 2019-07-25.
 */
public class IsDeviceBindBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"is_bind":""}
     */

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * is_bind :
         */

        public String is_bind;
    }
}
