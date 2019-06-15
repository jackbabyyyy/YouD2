package com.ud.share;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by PP on 2019/2/18.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        QMUISwipeBackActivityManager.init(this);



        regToWx();



    }

    private void  regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        IWXAPI  api = WXAPIFactory.createWXAPI(this, BuildConfig.WX_ID, true);

        // 将应用的appId注册到微信
        api.registerApp( BuildConfig.WX_ID);

        //建议动态监听微信启动广播进行注册到微信
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // 将该app注册到微信
                api.registerApp(BuildConfig.WX_ID);
            }
        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));

    }


}


