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
import com.yd.youd.utils.StatusUtil;

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
    @BindView(R.id.tvCode)
    TextView mTvCode;
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
    private String mOrder_id;
    private String mOrder_no;


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


        mOrder_id = getArguments().getString("order_id");
        mOrder_no = getArguments().getString("order_no");
        getData();
    }

    public static OrderDetailFragment getInstance(String order_id,String order_no){
        Bundle bundle=new Bundle();
        bundle.putString("order_id",order_id);bundle.putString("order_no",order_no);
        OrderDetailFragment fragment=new OrderDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private void getData() {


        HashMap<String, String> map = new HashMap<>();
        map.put("order_id",mOrder_id);
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.orderDetail, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {


            }

            @Override
            public void onResponse(String response) throws IOException {

                OrderDetailBean bean = JSON.parseObject(response, OrderDetailBean.class);
                //
                mTvCode.setText("订单编号："+mOrder_no);
                mTvBusiness.setText(bean.data.seller_name);
                mTvTime.setText(bean.data.starttime);
                mTvDes.setText(bean.data.tariff_type);
                mTvSN.setText(bean.data.device_sn);
                mTvType.setText(bean.data.device_type);
                mTvPayType.setText(StatusUtil.getPayType(bean.data.source_type));
                mTvMoney.setText(bean.data.Profit+"元");//todo 支付金额
                mTvProfit.setText(bean.data.Profit+"元");


            }
        });
    }


}
