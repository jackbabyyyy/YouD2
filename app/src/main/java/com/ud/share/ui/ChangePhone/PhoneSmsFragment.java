package com.ud.share.ui.ChangePhone;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.event.ChangePhoneEvent;
import com.ud.share.event.ChangePhoneEvent2;
import com.ud.share.event.Event;
import com.ud.share.model.BaseJson;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.utils.TimeCount;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * Created by PP on 2019-08-20.
 */
public class PhoneSmsFragment extends BaseFragment {
    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_sms)
    EditText mEtSms;
    @BindView(R.id.tv_sms)
    TextView mTvSms;
    @BindView(R.id.btn)
    Button mBtn;

    private TimeCount mCount;
    private String mUpdateTokenOrSid;
    private String mType;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_phone_sms;
    }

    public static PhoneSmsFragment getInstance(String updateTokenOrSid,String type){
        PhoneSmsFragment fragment=new PhoneSmsFragment();
        Bundle bundle=new Bundle();
        bundle.putString("token",updateTokenOrSid);
        bundle.putString("type",type);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    protected void init() {
        mCount = new TimeCount(60000,1000,mTvSms,getActivity());
        mUpdateTokenOrSid = getArguments().getString("token");
        mType = getArguments().getString("type");


        mBar.setTitle("更新手机号");
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        mTvSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getSms();
            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sms=mEtSms.getText().toString();
                if (TextUtils.isEmpty(sms)){
                    showToast("请输入验证码");
                    return;
                }
                update(mEtPhone.getText().toString(),sms);

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
        map.put("type",mType);
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.getDefSms, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {


                    }

                    @Override
                    public void onResponse(String response)  {
                        BaseJson baseJson= JSON.parseObject(response,BaseJson.class);
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

    private void update(String phone,String sms){
        HashMap<String,String> map=new HashMap<>();
        String url=null;
        if (mType.equals("1")){
            map.put("updateToken",mUpdateTokenOrSid);
            map.put("newPhone",phone);
            map.put("smsCode",sms);
            url=AppUrl.updatePhone;
        }else{
            map.put("sid",mUpdateTokenOrSid);
            map.put("newPhone",phone);
            map.put("smsCode",sms);
            url=AppUrl.updatePhone2;
        }


        HttpUtil.getInstance(getActivity()).postForm(url, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String s) throws IOException {
                //todo
                EventBus.getDefault().post(new ChangePhoneEvent2(phone));
                popBackStack();

            }
        });

    }





}
