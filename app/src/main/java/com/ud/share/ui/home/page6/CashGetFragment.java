package com.ud.share.ui.home.page6;

import android.provider.Settings;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Space;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.MyApplication;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.event.Event;
import com.ud.share.event.SmsCodeEvent;
import com.ud.share.event.WxCodeEvent;
import com.ud.share.model.BaseJson;
import com.ud.share.model.CashGetBean;
import com.ud.share.model.InfoBean;
import com.ud.share.model.RealNameBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.ui.my.InfoChangeFragment;
import com.ud.share.ui.my.InfoChangeFragment2;
import com.ud.share.utils.AppData;
import com.ud.share.utils.IPutils;
import com.ud.share.utils.SP;
import com.ud.share.utils.StringUtils;
import com.ud.share.web.WebFragment;
import com.ud.share.wxapi.WXTools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * Created by PP on 2019/4/1.
 */
public class CashGetFragment extends BaseFragment {
    @BindView(R.id.tvCash)
    TextView mTvCash;
    @BindView(R.id.tvFreeze)
    TextView mTvFreeze;
    @BindView(R.id.tvDes)
    TextView mTvDes;
    @BindView(R.id.section_name)
    TextView mName;
    @BindView(R.id.edit)
    EditText mEdit;
    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.btn)
    Button mBtn;
    @BindView(R.id.tvDeposit)
    TextView mTvDeposit;
    @BindView(R.id.tvHand)
    TextView mTvHand;
    @BindView(R.id.realName)
    EditText mRealName;
    @BindView(R.id.root_realname)
    View mRoot;
    private CashGetBean mBean;
    private InfoBean mInfoBean;
    private boolean mIsRealName;
    private String mRealname = "";
    private String mSmscode = "";


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cash_get
                ;
    }

    @Override
    protected void init() {

        EventBus.getDefault().register(this);


        //
        mInfoBean = JSON.parseObject(SP.getInfo(getActivity()), InfoBean.class);


        mBar.setTitle("提现");
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
        mName.setText("微信提现");

        //isRealName();


//        mEdit.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (TextUtils.isEmpty(s.toString())) {
//                    return;
//                }
//                double total = Double.valueOf(mBean.data.available_amount);
//                double going = Double.valueOf(s.toString());
//                double hand = Double.valueOf(mBean.data.service_fee);
//                if (total <= hand) {
//                    mEdit.setText("0");
//                }
//                if (going > total) {
//                    mEdit.setText(total + "");
//                }
//
//            }
//        });
    }


    @Override
    public void onResume() {
        super.onResume();

        getData();
        getInfo();
    }

    private void getData() {
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.cashGet, null, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) throws IOException {
                mBean = JSON.parseObject(response, CashGetBean.class);
                mTvCash.setText(mBean.data.available_amount);
                mTvDeposit.setText("冻结金额：" + mBean.data.deposit);
                mTvFreeze.setText("提现中：" + mBean.data.freezing_amount);
                mTvHand.setText(StringUtils.trimZero(mBean.data.service_fee) + "元/笔");


            }
        });
    }


    @OnClick({R.id.tvDes, R.id.btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvDes:
                startFragment(WebFragment.getInstance(AppData.getH5().data.wt));
                break;
            case R.id.btn:

                String amount = mEdit.getText().toString();
                if (TextUtils.isEmpty(amount)) {
                    showToast("请填写提现金额");
                    return;
                }

                if (mInfoBean.data.bind_weixin.equals("0")) {
                    WXTools tools = new WXTools(getActivity());
                    tools.login();
                } else {
                    cashGet(mEdit.getText().toString().trim());
                }



//
//                    if (!TextUtils.isEmpty(mSmscode)) {
//                    cashGet(mEdit.getText().toString().trim());
//                } else {
//                    //wx提现
//                    if (mInfoBean.data.bind_weixin.equals("0")) {
//                        WXTools tools = new WXTools(getActivity());
//                        tools.login();
//                    } else {
//
//
//                        if (!((MyApplication)getActivity().getApplication()).getUse() &&((MyApplication)getActivity().getApplication()).ismHasSmsCode()){
//                            startFragment(CashGetSmsFragment.getInstance(mEdit.getText().toString().trim(), "3", mInfoBean.data.phone, mRealname, mBean.data.available_amount, mBean.data.service_fee));
//                        }else{
//                            getSms();
//                        }
//                    }
//
//                    //showApplyDialog(amount);
//                }


//                if(!mIsRealName){
//                    if (TextUtils.isEmpty(mRealName.getText().toString())){
//                        showToast("请输入真实姓名");
//
//                    }else{
//                        showApplyDialog(amount);
//                    }
//                }else
//                {
//                    showApplyDialog(amount);
//                }


                break;
        }
    }

//    private void showApplyDialog(String amount) {
//        BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
//
//        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_cash_apply, null);
//        ((TextView) v.findViewById(R.id.amount)).setText("￥" + amount);
//
//        ((TextView) v.findViewById(R.id.bank)).setText(getBankCode());
//        v.findViewById(R.id.root_bank).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!TextUtils.isEmpty(getBankCode())) {
//                    //    cashApply("1", amount);//1 银行卡
//                } else {
//                    //去填写银行卡
//                    startFragment(InfoChangeFragment2.getInstance(SP.getInfo(getActivity())));
//                }
//
//                dialog.dismiss();
//            }
//        });
//        v.findViewById(R.id.root_wx).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //wx提现
//
//
//                if (mInfoBean.data.bind_weixin.equals("0")) {
//                    WXTools tools = new WXTools(getActivity());
//                    tools.login();
//                } else {
//
//                    getSms();
//
//                }
//
//
//
//
//                dialog.dismiss();
//
//            }
//        });
//        dialog.setContentView(v);
//        dialog.show();
//
//    }

    private String getBankCode() {
        InfoBean bean = JSON.parseObject(SP.getInfo(getActivity()), InfoBean.class);
        if (TextUtils.isEmpty(bean.data.credit_num))
            return "";
        else
            return bean.data.credit_num;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event event) {
        if (event instanceof WxCodeEvent) {
            bindWx(((WxCodeEvent) event).code);
        } else if (event instanceof SmsCodeEvent) {
            mSmscode = ((SmsCodeEvent) event).code;
        }


    }

    //绑定微信
    private void bindWx(String code) {
        HashMap<String, String> map = new HashMap<>();
        map.put("code", code);
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.wxbind, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String s) throws IOException {
                BaseJson json = JSON.parseObject(s, BaseJson.class);
                showToast(json.msg);
                if (json.code == 1) {
                    getSms();
                }
            }
        });
    }


    private void getSms() {
//        if(!mIsRealName){
//            mRealname = mRealName.getText().toString().trim();
//            if (TextUtils.isEmpty(mRealname)){
//                showToast("请输入真实姓名");
//                return;
//            }
//        }else{
//            mRealname="";
//        }

        String phone = mInfoBean.data.phone;
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("type", "3");

        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.smsGet, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        BaseJson baseJson = JSON.parseObject(response, BaseJson.class);
                        showToast(baseJson.msg);

                        startFragment(CashGetSmsFragment.getInstance(mEdit.getText().toString().trim(), "3", phone, mRealname, mBean.data.available_amount, mBean.data.service_fee));

                    }
                });
    }


    private void isRealName() {
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.isRealName, null, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String s) throws IOException {

                        RealNameBean bean = JSON.parseObject(s, RealNameBean.class);
                        if (bean.data.is_realname.equals("1")) {
                            mRoot.setVisibility(View.GONE);
                            mIsRealName = true;

                        } else {
                            mIsRealName = false;
                            mRoot.setVisibility(View.VISIBLE);

                        }
                    }
                });
    }

    private void getInfo() {
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.infoGet, null, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {

                        SP.saveInfo(getActivity(), response);

                        mInfoBean = JSON.parseObject(response, InfoBean.class);

                    }
                });
    }

    private void cashGet(String amount) {
        HashMap<String, String> map = new HashMap<>();
        map.put("amount", amount);
        map.put("type", "3");
        map.put("device_info", Settings.System.getString(getActivity().getContentResolver(), Settings.System.ANDROID_ID));
        map.put("create_ip", IPutils.getIPAddress(getActivity()));
        map.put("code", mSmscode);

        HttpUtil.getInstance(getActivity()).postForm(AppUrl.cashApply, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {


            }

            @Override
            public void onResponse(String s) throws IOException {
                BaseJson json = JSON.parseObject(s, BaseJson.class);
                showToast(json.msg);
                if (json.code == 1) {
                    startFragmentAndDestroyCurrent(CashGetStatusFragment.getInstance(true, amount, String.valueOf(new BigDecimal(amount).subtract(new BigDecimal(mBean.data.service_fee)).doubleValue()), mBean.data.available_amount));
                } else if (json.code == -20001 ) {
                    //未发送验证码
                    getSms();

                }else if (json.code==-20002){
                    //验证码输入错误 不需要重新发验证码
                    startFragment(CashGetSmsFragment.getInstance(mEdit.getText().toString().trim(), "3", mInfoBean.data.phone, mRealname, mBean.data.available_amount, mBean.data.service_fee));

                }


            }
        });
    }


}
