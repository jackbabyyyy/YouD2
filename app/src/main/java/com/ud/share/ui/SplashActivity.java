package com.ud.share.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.ud.share.R;
import com.ud.share.base.BaseActivity;
import com.ud.share.ui.login.LoginActivity;
import com.ud.share.utils.SP;

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
