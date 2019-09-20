package com.ud.share.model;

import java.util.List;

/**
 * Created by PP on 2019-07-30.
 */
public class AllDeviceBean {


    /**
     * code : 1
     * msg : 操作成功
     * total : {"binded":140,"unbind":83}
     * data : [{"sid":"0","charging_id":"X0319B00128","name":""},{"sid":"0","charging_id":"X0319B00524","name":""},{"sid":"0","charging_id":"X0319B02699","name":""},{"sid":"0","charging_id":"X0419A01044","name":""},{"sid":"0","charging_id":"X0419A03154","name":""},{"sid":"0","charging_id":"X0419A03155","name":""},{"sid":"0","charging_id":"X0419A03157","name":""},{"sid":"0","charging_id":"X0419A03158","name":""},{"sid":"0","charging_id":"X0419A03159","name":""},{"sid":"0","charging_id":"X0419A03160","name":""},{"sid":"0","charging_id":"X0419A03161","name":""},{"sid":"0","charging_id":"X0419A03162","name":""},{"sid":"0","charging_id":"X0419A03163","name":""},{"sid":"0","charging_id":"X0419A03166","name":""},{"sid":"0","charging_id":"X0419A03167","name":""},{"sid":"0","charging_id":"X0419A03168","name":""},{"sid":"0","charging_id":"X0419A03169","name":""},{"sid":"0","charging_id":"X0419A04412","name":""},{"sid":"0","charging_id":"X0419A04430","name":""},{"sid":"0","charging_id":"X0419A04548","name":""},{"sid":"0","charging_id":"X0419A09085","name":""},{"sid":"0","charging_id":"X0419A09089","name":""},{"sid":"0","charging_id":"X0419A05491","name":""},{"sid":"0","charging_id":"X0419A05499","name":""},{"sid":"0","charging_id":"X0319B00130","name":""},{"sid":"0","charging_id":"X0419A34738","name":""},{"sid":"0","charging_id":"X0419A34740","name":""},{"sid":"0","charging_id":"X0419A34741","name":""},{"sid":"0","charging_id":"X0419A34742","name":""},{"sid":"0","charging_id":"X0419A34743","name":""},{"sid":"0","charging_id":"X0419A34744","name":""},{"sid":"0","charging_id":"X0419A34745","name":""},{"sid":"0","charging_id":"X0419A34746","name":""},{"sid":"0","charging_id":"X0419A34751","name":""},{"sid":"0","charging_id":"X0419A34752","name":""},{"sid":"0","charging_id":"X0419A34753","name":""},{"sid":"0","charging_id":"X0419A34754","name":""},{"sid":"0","charging_id":"X0419A34755","name":""},{"sid":"0","charging_id":"X0419A34756","name":""},{"sid":"0","charging_id":"X0419A34757","name":""},{"sid":"0","charging_id":"X0419A34758","name":""},{"sid":"0","charging_id":"X0419A34759","name":""},{"sid":"0","charging_id":"X0419A34760","name":""},{"sid":"0","charging_id":"X0419A34761","name":""},{"sid":"0","charging_id":"X0419A34762","name":""},{"sid":"0","charging_id":"X0419A34763","name":""},{"sid":"0","charging_id":"X0419A34764","name":""},{"sid":"0","charging_id":"X0419A34765","name":""},{"sid":"0","charging_id":"X0419A34766","name":""},{"sid":"0","charging_id":"X0419A34767","name":""},{"sid":"0","charging_id":"X0419A34768","name":""},{"sid":"0","charging_id":"X0419A34769","name":""},{"sid":"0","charging_id":"X0419A34770","name":""},{"sid":"0","charging_id":"X0419A34771","name":""},{"sid":"0","charging_id":"X0419A34772","name":""},{"sid":"0","charging_id":"X0419A34773","name":""},{"sid":"0","charging_id":"X0419A34774","name":""},{"sid":"0","charging_id":"X0419A34775","name":""},{"sid":"0","charging_id":"X0419A34776","name":""},{"sid":"0","charging_id":"X0419A34777","name":""},{"sid":"0","charging_id":"X0419A34778","name":""},{"sid":"0","charging_id":"X0419A34779","name":""},{"sid":"0","charging_id":"X0419A34780","name":""},{"sid":"0","charging_id":"X0419A34781","name":""},{"sid":"0","charging_id":"X0419A34782","name":""},{"sid":"0","charging_id":"X0419A34783","name":""},{"sid":"0","charging_id":"X0419A34784","name":""},{"sid":"0","charging_id":"X0419A34785","name":""},{"sid":"0","charging_id":"X0419A34786","name":""},{"sid":"0","charging_id":"X0419A34787","name":""},{"sid":"0","charging_id":"X0419A34788","name":""},{"sid":"0","charging_id":"X0419A34789","name":""},{"sid":"0","charging_id":"X0419A34790","name":""},{"sid":"0","charging_id":"X0419A34791","name":""},{"sid":"0","charging_id":"X0419A34792","name":""},{"sid":"0","charging_id":"X0419A34793","name":""},{"sid":"0","charging_id":"X0419A34794","name":""},{"sid":"0","charging_id":"X0419A34795","name":""},{"sid":"0","charging_id":"X0419A34796","name":""},{"sid":"0","charging_id":"X0419A34797","name":""},{"sid":"0","charging_id":"X0419A34798","name":""},{"sid":"0","charging_id":"X0419A34799","name":""},{"sid":"0","charging_id":"X0419A34800","name":""}]
     */

    public int code;
    public String msg;
    public TotalBean total;
    public List<DataBean> data;

    public static class TotalBean {
        /**
         * binded : 140
         * unbind : 83
         */

        public int binded;
        public int unbind;
    }

    public static class DataBean {
        /**
         * sid : 0
         * charging_id : X0319B00128
         * name :
         */

        public String sid;
        public String charging_id;
        public String name;
    }
}
