package com.ud.share.ui.home.page6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;

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


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cash_get_status;
    }

    @Override
    protected void init() {
        boolean success=getArguments().getBoolean("success");
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
            mTv.setTextColor(getResources().getColor(R.color.theme));
            mIv.setImageResource(R.mipmap.cash_ok);
            mBack.setBackgroundResource(R.mipmap.btn_cash_ok);
        }


    }

    public static CashGetStatusFragment getInstance(boolean success){
        CashGetStatusFragment fragment=new CashGetStatusFragment();
        Bundle bundle=new Bundle();
        bundle.putBoolean("success",success);
        fragment.setArguments(bundle);
        return fragment;


    }



    @OnClick(R.id.back)
    public void onViewClicked() {
        popBackStack();
    }
}
