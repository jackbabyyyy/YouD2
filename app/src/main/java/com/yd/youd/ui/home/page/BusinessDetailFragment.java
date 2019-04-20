package com.yd.youd.ui.home.page;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;
import com.yd.youd.model.BusinessDetailBean;
import com.yd.youd.net.AppUrl;
import com.yd.youd.net.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * Created by PP on 2019/3/22.
 */
public class BusinessDetailFragment extends BaseFragment {


    @BindView(R.id.bar)
    QMUITopBarLayout mBar;

    @BindView(R.id.head)
    ImageView mHead;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.contacts)
    TextView mContacts;
    @BindView(R.id.mobile)
    TextView mMobile;
    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.addr)
    TextView mAddr;
    @BindView(R.id.rate)
    TextView mRate;
    @BindView(R.id.t1)
    View t1;
    @BindView(R.id.t2)
    View t2;
    @BindView(R.id.t3)
    View t3;
    @BindView(R.id.t4)
    View t4;

    @BindView(R.id.rate1)
    TextView mRate1;
    @BindView(R.id.rate2)
    TextView mRate2;
    @BindView(R.id.rate3)
    TextView mRate3;

    @BindView(R.id.price)
    TextView mPrice;
    @BindView(R.id.priceMax)
    TextView mPriceMax;

    @BindView(R.id.change)
    TextView mChange;
    @BindView(R.id.change2)
    TextView mChange2;
    @BindView(R.id.change3)
    TextView mChange3;







    private String mSid;
    private String mInfoJson;


    public static BusinessDetailFragment getInstance(String sid) {
        Bundle bundle = new Bundle();
        bundle.putString("sid", sid);
        BusinessDetailFragment fragment = new BusinessDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_business_detail;
    }

    @Override
    protected void init() {
        mSid = getArguments().getString("sid");
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
        mBar.setTitle("商户详情");
        mBar.addRightTextButton("解除绑定", R.id.bar_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ((TextView)t1.findViewById(R.id.divider_title)).setText("商户基础信息");
        ((TextView)t2.findViewById(R.id.divider_title)).setText("充电资费标准");
        ((TextView)t3.findViewById(R.id.divider_title)).setText("设备资费类型");
        ((TextView)t4.findViewById(R.id.divider_title)).setText("设备SN码");







        getData();

    }

    private void getData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("sid", mSid);
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.getBusinessDetail, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String s) throws IOException {
                mInfoJson = s;
                BusinessDetailBean bean = JSON.parseObject(s, BusinessDetailBean.class);
                //
                Glide.with(getActivity()).load(bean.data.seller.picture).into(mHead);
                mName.setText(bean.data.seller.name);
                mContacts.setText(bean.data.seller.contacts);
                mMobile.setText(bean.data.seller.mobile);
                mTime.setText(bean.data.seller.shopstart+"-"+bean.data.seller.shopend);
                mAddr.setText(bean.data.seller.addr);
                mRate.setText(bean.data.seller.line_rate+"(数据线)"+"\n"+bean.data.seller.cabinet_rate+"(充电宝)");
                mRate1.setText("1.充电1小时"+bean.data.seller.charge_mode_one);
                mRate2.setText("2.充电6小时"+bean.data.seller.charge_mode_two);
                mRate3.setText("3.充电12小时"+bean.data.seller.charge_mode_three);
                mPrice.setText("￥"+bean.data.seller.per_price);
                mPriceMax.setText("￥"+bean.data.seller.per_ceiling);


            }
        });
    }

    @OnClick({R.id.change,R.id.change2,R.id.change3})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.change:
                startFragment( BusinessChangeFragment.getInstance(mInfoJson));
                break;
            case R.id.change2:

                break;
            case R.id.change3:
                break;
        }
    }

}
