package com.yd.youd.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;
import com.yd.youd.base.BaseFragmentActivity;
import com.yd.youd.net.AppUrl;
import com.yd.youd.net.HttpUtil;
import com.yd.youd.utils.AppData;

import java.io.IOException;

import okhttp3.Request;

public class MainActivity extends BaseFragmentActivity {


    @Override
    protected BaseFragment getFirstFragment() {
        return new MainFragment();
    }

    @Override
    protected int getContextViewId() {
        return R.id.main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化配置
        getH5Set();
    }

    private void getH5Set(){
        HttpUtil.getInstance(this).postForm(AppUrl.h5Set, null, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) throws IOException {


                AppData.h5=response;

            }
        });
    }
}
