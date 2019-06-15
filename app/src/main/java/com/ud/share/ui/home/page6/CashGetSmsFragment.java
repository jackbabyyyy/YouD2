package com.ud.share.ui.home.page6;

import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.tuo.customview.VerificationCodeView;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.model.BaseJson;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.utils.IPutils;
import com.ud.share.utils.StringUtils;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * Created by PP on 2019/6/4.
 */
public class CashGetSmsFragment extends BaseFragment {


    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.icv)
    VerificationCodeView mSms;
    @BindView(R.id.des)
    TextView mDes;
    private String mAmount;
    private String mType;
    private String content;
    private String mPhone;
    private String mName;

    private boolean hasClicked;

    public static CashGetSmsFragment getInstance(String amount,String type,String phone,String name){
        CashGetSmsFragment cashGetSmsFragment=new CashGetSmsFragment();
        Bundle bundle=new Bundle();
        bundle.putString("amount",amount);
        bundle.putString("type",type);
        bundle.putString("phone",phone);
        bundle.putString("name",name);
        cashGetSmsFragment.setArguments(bundle);
        return cashGetSmsFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cash_get_sms;
    }

    @Override
    protected void init() {


        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
        mBar.setTitle("提现");
        mBar.addRightTextButton("确定",R.id.topbar_right)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(TextUtils.isEmpty(content)){
                            return;
                        }

                        if (hasClicked)return;
                        hasClicked=true;
                        cashGet();
                    }
                });

        mAmount = getArguments().getString("amount");
        mType = getArguments().getString("type");
        mPhone = getArguments().getString("phone");
        mName = getArguments().getString("name")+"";
        String des="我们为手机号<font color=\"#5CB800\">" + StringUtils.getPhoneX(mPhone) + "</font>发送了一条6位数字的验证码,请在下面输入验证码";
        mDes.setText(Html.fromHtml(des));
        mSms.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
              //  Log.i("icv_input", mSms.getInputContent());
                content=mSms.getInputContent();
            }

            @Override
            public void deleteContent() {
              //  Log.i("icv_delete", mSms.getInputContent());
            }
        });


    }

    private void cashGet(){
        HashMap<String ,String> map=new HashMap<>();
        map.put("amount",mAmount);
        map.put("type",mType);
        map.put("device_info", Settings.System.getString(getActivity().getContentResolver(), Settings.System.ANDROID_ID));
        map.put("create_ip", IPutils.getIPAddress(getActivity()));
        map.put("code",content);
        if (!TextUtils.isEmpty(mName)){
            map.put("wx_real_name",mName);

        }



        HttpUtil.getInstance(getActivity()).postForm(AppUrl.cashApply, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {


            }

            @Override
            public void onResponse(String s) throws IOException {
                BaseJson json= JSON.parseObject(s,BaseJson.class);
                showToast(json.msg);
                if (json.code==1){
                    startFragmentAndDestroyCurrent(CashGetStatusFragment.getInstance(true));
                }



            }
        });
    }




}
