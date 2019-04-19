package com.yd.youd.ui.home.page4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;
import com.yd.youd.model.OrderDetailBean;
import com.yd.youd.net.AppUrl;
import com.yd.youd.net.HttpUtil;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * Created by PP on 2019/4/1.
 */
public class OrderDetailFragment extends BaseFragment {
    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.tvStatus)
    TextView mTvStatus;
    @BindView(R.id.tvBusiness)
    TextView mTvBusiness;
    @BindView(R.id.tvTime)
    TextView mTvTime;
    @BindView(R.id.tvDes)
    TextView mTvDes;
    @BindView(R.id.tvSN)
    TextView mTvSN;
    @BindView(R.id.tvType)
    TextView mTvType;
    @BindView(R.id.tvPayType)
    TextView mTvPayType;
    @BindView(R.id.tvMoney)
    TextView mTvMoney;
    @BindView(R.id.tvProfit)
    TextView mTvProfit;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_detail;
    }

    @Override
    protected void init() {
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
        mBar.setTitle("订单详情");


        getData();
    }

    private void getData() {

        HashMap<String, String> map = new HashMap<>();
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.orderDetail, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {


            }

            @Override
            public void onResponse(String response) throws IOException {

                OrderDetailBean bean = JSON.parseObject(response, OrderDetailBean.class);
                //
                mTvBusiness.setText(bean.data.seller_name);
                mTvTime.setText(bean.data.starttime);
                mTvDes.setText(bean.data.tariff_type);
                mTvSN.setText(bean.data.device_sn);
                mTvType.setText(bean.data.device_type);
                mTvPayType.setText(bean.data.source_type);
                mTvMoney.setText(bean.data.Profit);//todo
                mTvProfit.setText(bean.data.Profit);


            }
        });
    }


}
