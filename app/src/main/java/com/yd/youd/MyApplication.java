package com.yd.youd;

import android.app.Application;

import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;

/**
 * Created by PP on 2019/2/18.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        QMUISwipeBackActivityManager.init(this);

    }







}


