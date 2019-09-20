package com.ud.share.comm;

/**
 * Created by PP on 2019-09-06.
 */
public enum  InstallObjectEnum {

    BUSINESS(1,"商户"),LOWERPROXY(2,"下级代理商");


    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    private int id;
    private String category;
    InstallObjectEnum(int type, String category) {
        this.id=type;
        this.category=category;


    }}
