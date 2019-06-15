package com.ud.share.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.model.BaseJson;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.ui.login.LoginActivity;
import com.ud.share.utils.TimeCount;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;


/**
 * Created by PP on 2019/3/8.
 */
public class PassChangeFragment extends BaseFragment {
    @BindView(R.id.bar)
    QMUITopBar mBar;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_pass)
    EditText mEtPass;
    @BindView(R.id.tv_sms)
    TextView mTvSms;
    @BindView(R.id.et_passFirst)
    EditText mEtPassFirst;
    @BindView(R.id.et_passAgain)
    EditText mEtPassAgain;
    @BindView(R.id.btn_confirm)
    Button mBtnConfirm;
    @BindView(R.id.viewIsFirst)
    View mViewIsFirst;
    private boolean mIsFirst;
    private TimeCount mCount;

    public static PassChangeFragment getInstance(boolean isFirst){
        Bundle bundle=new Bundle();
        bundle.putBoolean("isFirst",isFirst);
        PassChangeFragment fragment=new PassChangeFragment();
        fragment.setArguments(bundle);
        return  fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pass_change;
    }

    @Override
    protected void init() {

        mCount = new TimeCount(60000,1000,mTvSms,getActivity());
        mIsFirst = getArguments().getBoolean("isFirst");
        if (mIsFirst){
           mViewIsFirst.setVisibility(View.GONE);

        }else{
            mViewIsFirst.setVisibility(View.VISIBLE);

        }

        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });






    }


    @OnClick({R.id.tv_sms, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sms:

                getSms();

                break;
            case R.id.btn_confirm:

                if(mIsFirst){
                    getPassSet();
                }else{
                    getPassChange();
                }



                break;
        }
    }

    private void getSms() {
        String phone=mEtPhone.getText().toString();
        if (TextUtils.isEmpty(phone)){
            showToast("请填写手机号");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("phone",phone);
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.passChangeSms, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {
                        BaseJson json=JSON.parseObject(response,BaseJson.class);
                        showToast(json.msg);

                    }
                });

        mCount.start();
    }


    private void getPassChange() {
        String phone=mEtPhone.getText().toString();
        String code=mEtPass.getText().toString();
        String pass=mEtPassFirst.getText().toString();
        String pass2=mEtPassAgain.getText().toString();
        if (TextUtils.isEmpty(code)){
            showToast("请填写验证码");
            return;
        }
        if (!pass.equals(pass2)) {
            showToast("两次密码不一致");
            return;
        }
        if (TextUtils.isEmpty(pass)||TextUtils.isEmpty(pass2)){
            showToast("请填写完整");
            return;
        }
        Map<String,String > map=new HashMap<>();
        map.put("phone",phone);
        map.put("code",code);
        map.put("new_pwd",pass);
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.passChange, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {
                        BaseJson baseJson=JSON.parseObject(response,BaseJson.class);
                        showToast(baseJson.msg);
                        if (baseJson.code==HttpUtil.NORMAL){


                            startActivity(new Intent(getActivity(), LoginActivity.class));
                            getActivity().finish();


                        }

                    }
                });




    }

    private void getPassSet() {
        String pass=mEtPassFirst.getText().toString();
        String pass2=mEtPassAgain.getText().toString();
        if (!pass.equals(pass2)) {
            showToast("两次密码不一致");
            return;
        }
        if (TextUtils.isEmpty(pass)||TextUtils.isEmpty(pass2)){
            showToast("请填写完整");
            return;
        }


        Map<String,String> map=new HashMap<>();
        map.put("password",pass);

        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.passSet, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {


                    }

                    @Override
                    public void onResponse(String response) throws IOException {
                        BaseJson json= JSON.parseObject(response,BaseJson.class);
                        showToast(json.msg);
                        popBackStack();

                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCount.cancel();
    }
}
