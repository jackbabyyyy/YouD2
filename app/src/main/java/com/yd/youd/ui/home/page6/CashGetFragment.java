package com.yd.youd.ui.home.page6;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;
import com.yd.youd.model.CashGetBean;
import com.yd.youd.net.AppUrl;
import com.yd.youd.net.HttpUtil;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
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


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cash_get
                ;
    }

    @Override
    protected void init() {
        mBar.setTitle("提现");
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
        mName.setText("现金提取");
        getData();

    }

    private void getData() {
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.cashGet, null, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) throws IOException {
                CashGetBean bean= JSON.parseObject(response,CashGetBean.class);
                mTvCash.setText(bean.data.available_amount);
                mTvDeposit.setText(bean.data.deposit);
                mTvFreeze.setText(bean.data.freezing_amount);
                mTvHand.setText(bean.data.service_fee);



            }
        });
    }


    @OnClick({R.id.tvDes, R.id.btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvDes:

                break;
            case R.id.btn:

                String amount=mEdit.getText().toString();
                if (TextUtils.isEmpty(amount)){
                    showToast("请填写提现金额");
                    return;
                }
                showApplyDialog(amount);

                break;
        }
    }

    private void showApplyDialog(String amount){
        BottomSheetDialog dialog=new BottomSheetDialog(getActivity());

        View v=LayoutInflater.from(getActivity()).inflate(R.layout.dialog_cash_apply,null);
        ((TextView)v.findViewById(R.id.amount)).setText("￥"+amount);
        v.findViewById(R.id.root_bank).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasBankCode()){
                    cashApply("1",amount);//1 银行卡
                }else{
                    //去填写银行卡
                }

                dialog.dismiss();
            }
        });

        dialog.setContentView(v);
        dialog.show();

    }

    private boolean hasBankCode() {

        //todo

        return false;
    }

    private void cashApply(String type,String amount){
        HashMap<String,String> map=new HashMap<>();
        map.put("amount",amount);
        map.put("type",type);
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.cashApply, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String s) throws IOException {


            }
        });
    }



}
