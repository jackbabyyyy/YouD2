package com.ud.share.event;

/**
 * Created by PP on 2019/5/31.
 */
public class WxCodeEvent extends Event {
    public String code;

    public WxCodeEvent(String code) {
        this.code = code;
    }
}
