package com.ud.share.event;

/**
 * Created by PP on 2019-07-17.
 */
public class SearchEvent extends Event {

    public String key;

    public SearchEvent(String key) {
        this.key = key;
    }
}
