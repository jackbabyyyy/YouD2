package com.yd.youd.model;

/**
 * Created by PP on 2019/4/18.
 */
public class H5SetBean

{


    /**
     * code : 1
     * msg : 操作成功
     * data : {"faq":"http://ap.ud-share.cn:112/app/about.html#/help","about":"http://ap.ud-share.cn:112/app/about.html#/about","tutorial":"www.udshare.cn"}
     */

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * faq : http://ap.ud-share.cn:112/app/about.html#/help
         * about : http://ap.ud-share.cn:112/app/about.html#/about
         * tutorial : www.udshare.cn
         */

        public String faq;
        public String about;
        public String tutorial;
    }
}
