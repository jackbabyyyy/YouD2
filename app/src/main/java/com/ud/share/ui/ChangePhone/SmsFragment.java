package com.ud.share.ui.ChangePhone;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.tuo.customview.VerificationCodeView;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.model.BaseJson;
import com.ud.share.model.UpdateToken;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.utils.TimeCount;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * Created by PP on 2019-08-19.
 */
public class SmsFragment extends BaseFragment {
    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.icv)
    VerificationCodeView mIcv;

    @BindView(R.id.btn)
    Button mBtn;



    private String mPhone;
    private String mType;


    @Override
    protected int getLayoutId() {
        return R.layout.sms;
    }



    @Override
    protected void init() {

        mBar.setTitle("验证手机号");
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        mPhone = getArguments().getString("phone");
        mType = getArguments().getString("type");

    }



    public static SmsFragment getInstance(String phone,String type){
        SmsFragment fragment=new SmsFragment();
        Bundle bundle=new Bundle();
        bundle.putString("phone",phone);
        bundle.putString("type",type);
        fragment.setArguments(bundle);
        return fragment;
    }



    @OnClick({R.id.bar, R.id.icv, R.id.btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bar:

                break;
            case R.id.icv:

                break;
            case R.id.btn:
                updateToken(mPhone,mIcv.getInputContent());

                break;
        }
    }



    private void updateToken(String phone,String smsCode){
        HashMap<String,String> map=new HashMap<>();
        map.put("phone",phone);
        map.put("smsCode",smsCode);
        map.put("type",mType);
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.updateToken, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String s) throws IOException {
                UpdateToken token=JSON.parseObject(s,UpdateToken.class);
                startFragmentAndDestroyCurrent(PhoneSmsFragment.getInstance(token.data.token,mType));


            }
        });

    }


}
