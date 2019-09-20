package com.ud.share.utils;

import com.alibaba.fastjson.JSON;
import com.ud.share.R;
import com.ud.share.model.H5SetBean;
import com.ud.share.model.ProxyListBean;
import com.ud.share.ui.home.ImgTitleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PP on 2019/2/20.
 */
public class AppData {
    public static final int[] HOME_MID_ICON = {R.mipmap.home_merchant_list,
            R.mipmap.home_agent, R.mipmap.home_equipment_list, R.mipmap.home_order_list
            , R.mipmap.home_income, R.mipmap.home_cashout, R.mipmap.home_equipment_test, R.mipmap.home_equipment_repair};
    public static final String[] HOME_MID = {"商户列表", "代理商列表", "设备列表", "订单列表", "收益明细"
            , "提现记录", "设备检查", "设备检修"};

    public static final int[] HOME_BOT_ICON = {R.mipmap.home_today_order, R.mipmap.home_using, R.mipmap.home_standby, R.mipmap.home_deposit};
    public static final String[] HOME_BOT = {"今日订单", "使用中", "待机中", "押金明细"};
    public static final String[] HOME_DES = {" ", " ", " ", "规则及记录"};

    public static final String[] EXTEND = {"二维码图片链接", "宣传物料下载中心", "分享注册邀请链接", "app使用教程"};
    public static final int[] EXTEND_ICON = {R.mipmap.extend_, R.mipmap.extend_2, R.mipmap.extend_3, R.mipmap.extend_4};
    public static final int[] EXTEND_BG = {R.mipmap.extend, R.mipmap.extend2, R.mipmap.extend3, R.mipmap.extend4};

    public static final String[] MY = {"常见问题", "联系客服", "修改密码", "关于我们", "退出登录"};
    public static final int[] MY_ICON = {R.mipmap.icon_mine_help, R.mipmap.icon_me_cs, R.mipmap.icon_mine_pwd,
            R.mipmap.icon_mine_about, R.mipmap.icon_mine_exit};

    public static class HomeMidBean {
        public String name;
        public int icon;
    }

    public static class HomeBotBean {
        public String name;
        public int icon;
        public String des;
    }

    public static class ExtendBean {
        public String name;
        public int icon;
        public int bg;
    }

    public static class MyBean {
        public String name;
        public int icon;
    }

    public static List<HomeMidBean> getHomeMid() {
        List<HomeMidBean> list = new ArrayList<>();

        for (int i = 0; i < HOME_MID.length; i++) {
            HomeMidBean bean = new HomeMidBean();
            bean.name = HOME_MID[i];
            bean.icon = HOME_MID_ICON[i];

            list.add(bean);
        }

        return list;
    }

    public static List<HomeBotBean> getHomeBot() {
        List<HomeBotBean> list = new ArrayList<>();
        for (int i = 0; i < HOME_BOT.length; i++) {
            HomeBotBean bean = new HomeBotBean();
            bean.des = HOME_DES[i];
            bean.name = HOME_BOT[i];
            bean.icon = HOME_BOT_ICON[i];
            list.add(bean);
        }

        return list;
    }


    public static List<ExtendBean> getExtend() {
        List<ExtendBean> list = new ArrayList<>();
        for (int i = 0; i < EXTEND.length; i++) {
            ExtendBean bean = new ExtendBean();

            bean.name = EXTEND[i];
            bean.icon = EXTEND_ICON[i];
            bean.bg = EXTEND_BG[i];
            list.add(bean);
        }

        return list;
    }

    public static List<MyBean> getMy() {
        List<MyBean> list = new ArrayList<>();
        for (int i = 0; i < MY.length; i++) {
            MyBean bean = new MyBean();

            bean.name = MY[i];
            bean.icon = MY_ICON[i];

            list.add(bean);
        }

        return list;
    }


    public static final String[] INFO = {"姓名：", "手机号：", "身份证号：", "银行卡号：", "所属银行：", "所属分行："};

    public static List<String> getInfo() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < INFO.length; i++) {

            list.add(INFO[i]);

        }

        return list;
    }

    public static class InfoBean {
        public String title = "";
        public String name = "";
    }

    public static class AgentInfoBean {
        public String title = "";
        public String content = "";
        public String des = "";
        public String hint = "";
    }

    public static final String[] agent_title = {"代理商名称:", "姓  名:", "联系方式:", "设置线分润:", "设置冻结金额:"};
    public static final String[] agent_hint = {"请输入下级代理姓名", "请输入联系方式", "设置下级代理的分润比例", "设置下级代理的分润比例", "输入下级冻结金额(元)"};
    public static final String[] agent_des = {"", "", "注：联系方式需要是有效电话，并且用于代理商登录APP后台账户。", "注：例如自己分润为80%，设置下级分润可输入80%以下的数值", "注：下级用户为自我提现，设置下级用户冻结金额，下级代理只能提现超出冻结金额以外的部分。冻结金额后期可以修改。"};

//    public static List<AgentInfoBean> getInitAgentInfo() {
//        List<AgentInfoBean> ls = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            AgentInfoBean bean = new AgentInfoBean();
//            bean.title = agent_title[i];
//            bean.hint = agent_hint[i];
//            bean.des = agent_des[i];
//            ls.add(bean);
//        }
//
//        return ls;
//    }


    public static List<AgentInfoBean> getAgentInfo(String json) {
        ProxyListBean.DataBeanX.DataBean dataBean = JSON.parseObject(json, ProxyListBean.DataBeanX.DataBean.class);

        List<AgentInfoBean> ls = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AgentInfoBean bean = new AgentInfoBean();
            bean.title = agent_title[i];
            bean.des = agent_des[i];
            ls.add(bean);
        }
        ls.get(0).content = dataBean.agent_name;
        ls.get(1).content = dataBean.real_name;
        ls.get(2).content = dataBean.phone;
        ls.get(3).content = dataBean.line_rate;
//        ls.get(4).content = dataBean.cabinet_rate;
        ls.get(4).content = dataBean.deposit;

        return ls;

    }


    public static String h5 = "";
    public static String INVITE_LINK = "";

    public static H5SetBean getH5() {
        return JSON.parseObject(h5, H5SetBean.class);
    }

    public static final String MODEL_LINE = "XA05";


    public static final int[] HOME_MID2_ICON = {R.mipmap.home_use, R.mipmap.home_unuse, R.mipmap.home_today_order, R.mipmap.home_today_profit};
    public static final String[] HOME_MID2 = {"使用中", "未使用", "今日订单", "今日收益"};


    public static final int[] HOME_BOT2_ICON = {R.mipmap.home_1, R.mipmap.home_2, R.mipmap.home_3, R.mipmap.home_4, R.mipmap.home_5, R.mipmap.home_6,
            R.mipmap.home_8, R.mipmap.home_10};
    public static final String[] HOME_BOT2 = {"商户列表", "代理商列表", "设备列表", "订单列表", "收益明细"
            , "收益提取", "分润设置", "幽电分享"};


    public static List<ImgTitleBean> getHomeMid2() {
        List<ImgTitleBean> list = new ArrayList<>();

        for (int i = 0; i < HOME_MID2.length; i++) {
            ImgTitleBean bean = new ImgTitleBean();
            bean.title = HOME_MID2[i];
            bean.icon = HOME_MID2_ICON[i];

            list.add(bean);
        }

        return list;
    }

    public static List<ImgTitleBean> getHomeBot2() {
        List<ImgTitleBean> list = new ArrayList<>();
        for (int i = 0; i < HOME_BOT2.length; i++) {
            ImgTitleBean bean = new ImgTitleBean();
            bean.title = HOME_BOT2[i];
            bean.icon = HOME_BOT2_ICON[i];
            list.add(bean);
        }
        return list;
    }


}
