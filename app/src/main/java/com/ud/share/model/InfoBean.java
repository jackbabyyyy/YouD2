package com.ud.share.model;

/**
 * Created by PP on 2019/3/8.
 */
public class InfoBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"realname":"代理3","nickname":"代理3","avatar":"https://ud.slong.xyzimages/a1.png","phone":"18815283197","level_type":"1","card_sn":"320823199005422588","credit_num":"","bank_name":"","belong_bank":"","isset_pwd":"0"}
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
        public String invite_link;
        public String sub_line_rate;
        public String sub_cabinet_rate;
        public String bind_weixin;
    }
}
