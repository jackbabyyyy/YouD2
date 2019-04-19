package com.yd.youd.model;

/**
 * Created by PP on 2019/3/8.
 */
public class LoginBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"realname":"代理3","nickname":"代理3","avatar":"https://ud.slong.xyzimages/a1.png","phone":"18815283197","level_type":"1","card_sn":"320823199005422588","credit_num":"","bank_name":"","belong_bank":"","isset_pwd":"0","userToken":"5ee31c50ed51362d8d9c87c480db268f"}
     */

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * realname : 代理3
         * nickname : 代理3
         * avatar : https://ud.slong.xyzimages/a1.png
         * phone : 18815283197
         * level_type : 1
         * card_sn : 320823199005422588
         * credit_num :
         * bank_name :
         * belong_bank :
         * isset_pwd : 0
         * userToken : 5ee31c50ed51362d8d9c87c480db268f
         */

        public String realname;
        public String nickname;
        public String avatar;
        public String phone;
        public String level_type;
        public String card_sn;
        public String credit_num;
        public String bank_name;
        public String belong_bank;
        public String isset_pwd;
        public String userToken;
    }
}
