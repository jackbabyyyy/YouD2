package com.ud.share.ui.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.model.BaseJson;
import com.ud.share.model.LoginBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.ui.MainActivity;
import com.ud.share.utils.SP;
import com.ud.share.utils.TimeCount;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * Created by PP on 2019/3/5.
 * 验证码登录
 */
public class LoginSmsFragment extends BaseFragment {
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_pass)
    EditText mEtPass;
    @BindView(R.id.tv_sms)
    TextView mTvSms;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.tv_pass)
    TextView mTvPass;

    private TimeCount mCount;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_sms;
    }

    @Override
    protected void init() {
        mCount = new TimeCount(60000,1000,mTvSms,getActivity());
    }



    @OnClick({R.id.tv_sms, R.id.btn_login, R.id.tv_pass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sms:
                getSms();
                break;
            case R.id.btn_login:
                getLogin();
                break;
            case R.id.tv_pass:
               popBackStack();
                break;
        }
    }

    private void getLogin() {
        String code=mEtPass.getText().toString();
        String phone=mEtPhone.getText().toString();
        if (TextUtils.isEmpty(code)){
            showToast("验证码为空");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("phone",phone);
        map.put("code",code);
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.loginSms, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {

                        LoginBean loginBean=JSON.parseObject(response,LoginBean.class);
                        SP.saveToken(getActivity(),loginBean.data.userToken);
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().finish();


                    }
                });


    }

    private void getSms() {

        String text=mEtPhone.getText().toString();
        if (TextUtils.isEmpty(text)&&text.length()!=11){
            showToast("请填写手机号码");
            return;
        }


        Map<String,String> map=new HashMap<>();
        map.put("phone",text);
        map.put("type","1");
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.smsGet, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {


                    }

                    @Override
                    public void onResponse(String response)  {
                        BaseJson baseJson=JSON.parseObject(response,BaseJson.class);
                        showToast(baseJson.msg);




                    }
                });

        mCount.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCount.cancel();
    }

    @Override
    protected boolean canDragBack() {
        return false;
    }



//    class TimeCount extends CountDownTimer{
//        public TimeCount(long millisInFuture, long countDownInterval) {
//            super(millisInFuture, countDownInterval);
//        }
//
//        @Override
//        public void onTick(long millisUntilFinished) {
//            mTvSms.setClickable(false);
//            mTvSms.setBackground(getResources().getDrawable(R.drawable.btn_selector_unable));
//            mTvSms.setText(millisUntilFinished/1000+"s");
//
//        }
//
//        @Override
//        public void onFinish() {
//            mTvSms.setClickable(true);
//            mTvSms.setBackground(getResources().getDrawable(R.drawable.btn_selector));
//            mTvSms.setText("获取验证码");
//
//
//        }


//
//    }
}
