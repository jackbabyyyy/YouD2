package com.ud.share.model;

/**
 * Created by PP on 2019/6/5.
 */
public class RealNameBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"is_realname":"1"}
     */

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * is_realname : 1
         */

        public String is_realname;
    }
}
