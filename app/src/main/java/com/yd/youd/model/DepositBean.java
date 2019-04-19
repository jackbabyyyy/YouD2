package com.yd.youd.model;

import java.util.List;

/**
 * Created by PP on 2019/4/12.
 */
public class DepositBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : [{"id":972,"uid":279,"order_no":"5011903151701269993","create_time":1552640486,"deposit":"0.02","status":2,"get_user":{"id":279,"name":"黄辉"}},{"id":973,"uid":293,"order_no":"5011903151702108675","create_time":1552640530,"deposit":"0.03","status":2,"get_user":{"id":293,"name":"伊景环乡"}},{"id":974,"uid":293,"order_no":"5011903151710519090","create_time":1552641051,"deposit":"0.01","status":2,"get_user":{"id":293,"name":"伊景环乡"}},{"id":976,"uid":293,"order_no":"5011903151712399493","create_time":1552641159,"deposit":"0.03","status":2,"get_user":{"id":293,"name":"伊景环乡"}},{"id":1002,"uid":320,"order_no":"5011903152055598906","create_time":1552654559,"deposit":"0.01","status":2,"get_user":{"id":320,"name":"A╭☞杰哥☞"}},{"id":1003,"uid":320,"order_no":"5011903152058537542","create_time":1552654733,"deposit":"0.01","status":2,"get_user":{"id":320,"name":"A╭☞杰哥☞"}},{"id":1004,"uid":320,"order_no":"5011903152101181624","create_time":1552654878,"deposit":"0.01","status":2,"get_user":{"id":320,"name":"A╭☞杰哥☞"}},{"id":1005,"uid":321,"order_no":"5011903152101591162","create_time":1552654919,"deposit":"0.01","status":2,"get_user":{"id":321,"name":"等风"}},{"id":1006,"uid":321,"order_no":"5011903152108077923","create_time":1552655287,"deposit":"0.01","status":2,"get_user":{"id":321,"name":"等风"}},{"id":1007,"uid":293,"order_no":"5011903152116012201","create_time":1552655761,"deposit":"0.01","status":2,"get_user":{"id":293,"name":"伊景环乡"}},{"id":1029,"uid":311,"order_no":"5011903191619282830","create_time":1552983568,"deposit":"2.00","status":2,"get_user":{"id":311,"name":null}}]
     */

    public int code;
    public String msg;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 972
         * uid : 279
         * order_no : 5011903151701269993
         * create_time : 1552640486
         * deposit : 0.02
         * status : 2
         * get_user : {"id":279,"name":"黄辉"}
         */

        public String id;
        public String uid;
        public String order_no;
        public String create_time;
        public String deposit;
        public int status;
        public GetUserBean get_user;

        public static class GetUserBean {
            /**
             * id : 279
             * name : 黄辉
             */

            public String id;
            public String name;
        }
    }
}
