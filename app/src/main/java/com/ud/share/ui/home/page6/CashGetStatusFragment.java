package com.ud.share.ui.home.page6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by PP on 2019/4/26.
 */
public class CashGetStatusFragment extends BaseFragment {
    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.back)
    Button mBack;

    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.tv2)
    TextView mTv2;
    @BindView(R.id.tv3)
    TextView mTv3;
    @BindView(R.id.tv4)
    TextView mTv4;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cash_get_status;
    }

    @Override
    protected void init() {
        boolean success=getArguments().getBoolean("success");
        String tixian=getArguments().getString("tixian");
        String shiji=getArguments().getString("shiji");
        String ketixian=getArguments().getString("ketixian");


        mTv1.setText("提现金额        "+tixian+"元");
        mTv2.setText("实际到账        "+shiji+"元");
        mTv3.setText("提现渠道        "+"微信");
        mTv4.setText("可提现金额      "+ketixian+"元");
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
        if (!success){
            mBar.setTitle("提现失败");
            mTv.setText("提现失败！");
            mTv.setTextColor(getResources().getColor(R.color.error));
            mIv.setImageResource(R.mipmap.cash_error);
            mBack.setBackgroundResource(R.mipmap.btn_cash_error);

        }else{
            mBar.setTitle("申请成功");
            mTv.setText("申请成功！");

            mIv.setImageResource(R.mipmap.cash_ok);

        }





    }

    public static CashGetStatusFragment getInstance(boolean success,String tixian,String shiji,String ketixian){
        CashGetStatusFragment fragment=new CashGetStatusFragment();
        Bundle bundle=new Bundle();
        bundle.putBoolean("success",success);
        bundle.putString("tixian",tixian);
        bundle.putString("shiji",shiji);
        bundle.putString("ketixian",ketixian);
        fragment.setArguments(bundle);
        return fragment;


    }



    @OnClick(R.id.back)
    public void onViewClicked() {
        popBackStack();
    }
}
