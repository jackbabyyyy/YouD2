package com.ud.share.ui;

import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.base.BaseFragmentActivity;
import com.ud.share.model.BaseJson;
import com.ud.share.model.InfoBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.utils.AppData;
import com.ud.share.utils.SP;

import java.io.IOException;

import okhttp3.Request;

public class MainActivity extends BaseFragmentActivity {


    @Override
    protected BaseFragment getFirstFragment() {
        return new MainFragment2();
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
        getInfo();




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

    private void getInfo(){
        HttpUtil.getInstance(this)
                .postForm(AppUrl.infoGet, null, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {

                        SP.saveInfo(getApplicationContext(), response);

                        InfoBean bean = JSON.parseObject(response, InfoBean.class);
                        AppData.INVITE_LINK=bean.data.invite_link;



                    }
                });
    }
}
