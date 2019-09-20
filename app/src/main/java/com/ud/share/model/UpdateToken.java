package com.ud.share.model;

/**
 * Created by PP on 2019-08-31.
 */
public class UpdateToken {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"token":"eyJwaG9uZSI6IjE4ODE1MjgzMTk3IiwicGsiOiIyNzAiLCJfY3JlYXRfdGltZSI6MTU2NzIxODI5NSwiX2xpbWl0X3RpbWUiOjE1NjcyMTg1OTUsIl9zZWNyZXQiOiI4YTE5YTczODY5ZTYyZWEifQ=="}
     */

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * token : eyJwaG9uZSI6IjE4ODE1MjgzMTk3IiwicGsiOiIyNzAiLCJfY3JlYXRfdGltZSI6MTU2NzIxODI5NSwiX2xpbWl0X3RpbWUiOjE1NjcyMTg1OTUsIl9zZWNyZXQiOiI4YTE5YTczODY5ZTYyZWEifQ==
         */

        public String token;
    }
}
