package com.ud.share.model;

import java.util.List;

/**
 * Created by PP on 2019-06-19.
 */
public class StaticsBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"analysis_data":{"member":[{"date":"2019-06-01","number":"500"},{"date":"2019-06-02","number":"100"},{"date":"2019-06-03","number":"200"},{"date":"2019-06-04","number":"500"},{"date":"2019-06-05","number":"300"},{"date":"2019-06-06","number":"400"},{"date":"2019-06-07","number":"100"},{"date":"2019-06-08","number":"200"},{"date":"2019-06-09","number":"400"},{"date":"2019-06-10","number":"500"},{"date":"2019-06-11","number":"600"},{"date":"2019-06-12","number":"700"},{"date":"2019-06-13","number":"700"},{"date":"2019-06-14","number":"700"},{"date":"2019-06-15","number":"700"},{"date":"2019-06-16","number":"700"},{"date":"2019-06-17","number":"700"},{"date":"2019-06-18","number":"700"},{"date":"2019-06-19","number":"700"},{"date":"2019-06-20","number":"700"},{"date":"2019-06-21","number":"700"},{"date":"2019-06-22","number":"700"},{"date":"2019-06-23","number":"700"},{"date":"2019-06-24","number":"700"},{"date":"2019-06-25","number":"700"},{"date":"2019-06-26","number":"700"},{"date":"2019-06-27","number":"700"},{"date":"2019-06-28","number":"700"},{"date":"2019-06-29","number":"700"},{"date":"2019-06-30","number":"777"}],"seller":[{"date":"2019-06-01","number":"555"},{"date":"2019-06-02","number":"100"},{"date":"2019-06-03","number":"200"},{"date":"2019-06-04","number":"500"},{"date":"2019-06-05","number":"300"},{"date":"2019-06-06","number":"400"},{"date":"2019-06-07","number":"100"},{"date":"2019-06-08","number":"200"},{"date":"2019-06-09","number":"400"},{"date":"2019-06-10","number":"500"},{"date":"2019-06-11","number":"600"},{"date":"2019-06-12","number":"700"},{"date":"2019-06-13","number":"700"},{"date":"2019-06-14","number":"700"},{"date":"2019-06-15","number":"700"},{"date":"2019-06-16","number":"700"},{"date":"2019-06-17","number":"700"},{"date":"2019-06-18","number":"700"},{"date":"2019-06-19","number":"700"},{"date":"2019-06-20","number":"700"},{"date":"2019-06-21","number":"700"},{"date":"2019-06-22","number":"700"},{"date":"2019-06-23","number":"700"},{"date":"2019-06-24","number":"700"},{"date":"2019-06-25","number":"700"},{"date":"2019-06-26","number":"700"},{"date":"2019-06-27","number":"700"},{"date":"2019-06-28","number":"700"},{"date":"2019-06-29","number":"700"},{"date":"2019-06-30","number":"888"}],"agent":[{"date":"2019-06-01","nuber":"333"},{"date":"2019-06-02","nuber":"100"},{"date":"2019-06-03","nuber":"200"},{"date":"2019-06-04","nuber":"500"},{"date":"2019-06-05","nuber":"300"},{"date":"2019-06-06","nuber":"400"},{"date":"2019-06-07","nuber":"100"},{"date":"2019-06-08","nuber":"200"},{"date":"2019-06-09","nuber":"400"},{"date":"2019-06-10","nuber":"500"},{"date":"2019-06-11","nuber":"600"},{"date":"2019-06-12","nuber":"700"},{"date":"2019-06-13","nuber":"700"},{"date":"2019-06-14","nuber":"700"},{"date":"2019-06-15","nuber":"700"},{"date":"2019-06-16","nuber":"700"},{"date":"2019-06-17","nuber":"700"},{"date":"2019-06-18","nuber":"700"},{"date":"2019-06-19","nuber":"700"},{"date":"2019-06-20","nuber":"700"},{"date":"2019-06-21","nuber":"700"},{"date":"2019-06-22","nuber":"700"},{"date":"2019-06-23","nuber":"700"},{"date":"2019-06-24","nuber":"700"},{"date":"2019-06-25","nuber":"700"},{"date":"2019-06-26","nuber":"700"},{"date":"2019-06-27","nuber":"700"},{"date":"2019-06-28","nuber":"700"},{"date":"2019-06-29","nuber":"700"},{"date":"2019-06-30","nuber":"666"}]},"total":{"member":"0","seller":"0","agent":"0"}}
     */

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * analysis_data : {"member":[{"date":"2019-06-01","number":"500"},{"date":"2019-06-02","number":"100"},{"date":"2019-06-03","number":"200"},{"date":"2019-06-04","number":"500"},{"date":"2019-06-05","number":"300"},{"date":"2019-06-06","number":"400"},{"date":"2019-06-07","number":"100"},{"date":"2019-06-08","number":"200"},{"date":"2019-06-09","number":"400"},{"date":"2019-06-10","number":"500"},{"date":"2019-06-11","number":"600"},{"date":"2019-06-12","number":"700"},{"date":"2019-06-13","number":"700"},{"date":"2019-06-14","number":"700"},{"date":"2019-06-15","number":"700"},{"date":"2019-06-16","number":"700"},{"date":"2019-06-17","number":"700"},{"date":"2019-06-18","number":"700"},{"date":"2019-06-19","number":"700"},{"date":"2019-06-20","number":"700"},{"date":"2019-06-21","number":"700"},{"date":"2019-06-22","number":"700"},{"date":"2019-06-23","number":"700"},{"date":"2019-06-24","number":"700"},{"date":"2019-06-25","number":"700"},{"date":"2019-06-26","number":"700"},{"date":"2019-06-27","number":"700"},{"date":"2019-06-28","number":"700"},{"date":"2019-06-29","number":"700"},{"date":"2019-06-30","number":"777"}],"seller":[{"date":"2019-06-01","number":"555"},{"date":"2019-06-02","number":"100"},{"date":"2019-06-03","number":"200"},{"date":"2019-06-04","number":"500"},{"date":"2019-06-05","number":"300"},{"date":"2019-06-06","number":"400"},{"date":"2019-06-07","number":"100"},{"date":"2019-06-08","number":"200"},{"date":"2019-06-09","number":"400"},{"date":"2019-06-10","number":"500"},{"date":"2019-06-11","number":"600"},{"date":"2019-06-12","number":"700"},{"date":"2019-06-13","number":"700"},{"date":"2019-06-14","number":"700"},{"date":"2019-06-15","number":"700"},{"date":"2019-06-16","number":"700"},{"date":"2019-06-17","number":"700"},{"date":"2019-06-18","number":"700"},{"date":"2019-06-19","number":"700"},{"date":"2019-06-20","number":"700"},{"date":"2019-06-21","number":"700"},{"date":"2019-06-22","number":"700"},{"date":"2019-06-23","number":"700"},{"date":"2019-06-24","number":"700"},{"date":"2019-06-25","number":"700"},{"date":"2019-06-26","number":"700"},{"date":"2019-06-27","number":"700"},{"date":"2019-06-28","number":"700"},{"date":"2019-06-29","number":"700"},{"date":"2019-06-30","number":"888"}],"agent":[{"date":"2019-06-01","nuber":"333"},{"date":"2019-06-02","nuber":"100"},{"date":"2019-06-03","nuber":"200"},{"date":"2019-06-04","nuber":"500"},{"date":"2019-06-05","nuber":"300"},{"date":"2019-06-06","nuber":"400"},{"date":"2019-06-07","nuber":"100"},{"date":"2019-06-08","nuber":"200"},{"date":"2019-06-09","nuber":"400"},{"date":"2019-06-10","nuber":"500"},{"date":"2019-06-11","nuber":"600"},{"date":"2019-06-12","nuber":"700"},{"date":"2019-06-13","nuber":"700"},{"date":"2019-06-14","nuber":"700"},{"date":"2019-06-15","nuber":"700"},{"date":"2019-06-16","nuber":"700"},{"date":"2019-06-17","nuber":"700"},{"date":"2019-06-18","nuber":"700"},{"date":"2019-06-19","nuber":"700"},{"date":"2019-06-20","nuber":"700"},{"date":"2019-06-21","nuber":"700"},{"date":"2019-06-22","nuber":"700"},{"date":"2019-06-23","nuber":"700"},{"date":"2019-06-24","nuber":"700"},{"date":"2019-06-25","nuber":"700"},{"date":"2019-06-26","nuber":"700"},{"date":"2019-06-27","nuber":"700"},{"date":"2019-06-28","nuber":"700"},{"date":"2019-06-29","nuber":"700"},{"date":"2019-06-30","nuber":"666"}]}
         * total : {"member":"0","seller":"0","agent":"0"}
         */

        public AnalysisDataBean analysis_data;
        public TotalBean total;

        public static class AnalysisDataBean {
            public List<DotBean> member;
            public List<DotBean> seller;
            public List<DotBean> agent;






        }

        public static class TotalBean {
            /**
             * member : 0
             * seller : 0
             * agent : 0
             */
            public String member;
            public String seller;
            public String agent;
        }
    }
}
