package com.ud.share.event;

/**
 * Created by PP on 2019-07-12.
 */
public class SmsCodeEvent extends Event {
    public String code;

    public SmsCodeEvent(String code) {
        this.code = code;
    }
}
