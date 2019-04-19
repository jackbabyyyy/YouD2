package com.yd.youd.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by PP on 2019/4/18.
 */
public class DeviceTypeBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"1":"GA06","2":"BA03","3":"XA05","4":"GG01"}
     */

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * 1 : GA06
         * 2 : BA03
         * 3 : XA05
         * 4 : GG01
         */

        @JSONField(name = "1")
        public String t1;
        @JSONField(name = "2")
        public String t2;
        @JSONField(name = "3")
        public String t3;
        @JSONField(name = "4")
        public String t4;
    }
}
