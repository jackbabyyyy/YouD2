package com.ud.share.model;

import java.util.List;

/**
 * Created by PP on 2019-07-04.
 */
public class SearchOneBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : [{"id":"6","name":"刘龙杰","user_type":"1"},{"id":"12","name":"曲靖龙讯科技","user_type":"1"},{"id":"206","name":"台州市椒江龙飞航空商务有限公司","user_type":"1"},{"id":"262","name":"厦门海龙达加油站有限公司","user_type":"1"},{"id":"275","name":"润龙珠宝有限公司","user_type":"1"},{"id":"347","name":"宁夏龙盘家具市场有限公司","user_type":"1"},{"id":"47","name":"龙淼连锁小吃店浑南店","user_type":"3"},{"id":"76","name":"龙壹商务酒店","user_type":"3"},{"id":"86","name":"龙涎饭店","user_type":"3"},{"id":"127","name":"龙壹商务宾馆","user_type":"3"},{"id":"226","name":"宁波九龙湖开元度假村","user_type":"3"},{"id":"416","name":"龙腾宾馆","user_type":"3"},{"id":"425","name":"龙都宾馆","user_type":"3"},{"id":"710","name":"黄龙宾馆","user_type":"3"},{"id":"865","name":"龙生宾馆","user_type":"3"},{"id":"896","name":"龙门客栈","user_type":"3"}]
     */

    public int code;
    public String msg;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 6
         * name : 刘龙杰
         * user_type : 1
         */

        public String id;
        public String name;
        public String user_type;
    }
}
