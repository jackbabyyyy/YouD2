package com.yd.youd.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.yd.youd.R;
import com.yd.youd.base.BaseActivity;
import com.yd.youd.model.H5SetBean;
import com.yd.youd.net.AppUrl;
import com.yd.youd.net.HttpUtil;
import com.yd.youd.ui.login.LoginActivity;
import com.yd.youd.utils.AppData;
import com.yd.youd.utils.SP;

import java.io.IOException;

import okhttp3.Request;

/**
 * Created by PP on 2019/3/5.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        QMUIDisplayHelper.setFullScreen(this);


    }
    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jump();
            }
        }, 1000);
    }

    private void jump() {
        if (TextUtils.isEmpty(SP.getToken(this))){
            startActivity(new Intent(this, LoginActivity.class));

        }else{
            startActivity(new Intent(this, MainActivity.class));
        }

    //    startActivity(new Intent(this, MapActivity.class));
        finish();


    }







}
