package com.ud.share.ui.home.page;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.model.BusinessDetailBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
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
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;


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
//        mBar.addRightTextButton("解除绑定", R.id.bar_right).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        ((TextView) t1.findViewById(R.id.divider_title)).setText("商户基础信息");
        ((TextView) t2.findViewById(R.id.divider_title)).setText("充电资费标准");
        ((TextView) t3.findViewById(R.id.divider_title)).setText("设备资费类型");
        ((TextView) t4.findViewById(R.id.divider_title)).setText("设备SN码");


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildAdapterPosition(view)==0){
                    outRect.top= QMUIDisplayHelper.dp2px(getActivity(),15);
                }
                if (parent.getChildAdapterPosition(view)==parent.getAdapter().getItemCount()-1){
                    outRect.bottom=QMUIDisplayHelper.dp2px(getActivity(),15);
                }
            }
        });





    }


    @Override
    public void onResume() {
        super.onResume();
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
                mTime.setText(bean.data.seller.shopstart + "-" + bean.data.seller.shopend);
                mAddr.setText(bean.data.seller.addr);
               // mRate.setText(bean.data.seller.line_rate + "(数据线)" + "\n" + bean.data.seller.cabinet_rate + "(充电宝)");
                mRate.setText(bean.data.seller.line_rate+"%");
                mRate1.setText("1.充电2小时" + bean.data.seller.line_hour_2+"元");
                mRate2.setText("2.充电6小时" + bean.data.seller.line_hour_6+"元");
                mRate3.setText("3.充电12小时" + bean.data.seller.line_hour_12+"元");
                mPrice.setText("￥" + bean.data.seller.per_price);
                mPriceMax.setText("￥" + bean.data.seller.per_ceiling);

                Glide.with(getActivity()).load(bean.data.seller.picture).into(mHead);


                SNAdapter adapter=new SNAdapter(bean.data.line_charging);
                mRecyclerView.setAdapter(adapter);


            }
        });
    }

    @OnClick({R.id.change, R.id.change2, R.id.change3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change:
                startFragment(BusinessChangeFragment.getInstance(mInfoJson));
                break;
            case R.id.change2:
                startFragment(BusinessTariffFragment.getInstance(mInfoJson));
                break;
            case R.id.change3:
                startFragment(BusinessChargeFragment.getInstance(mInfoJson));
                break;
        }
    }


    public class SNAdapter extends BaseQuickAdapter<BusinessDetailBean.DataBean.LineChargingBean, BaseViewHolder>{
        public String type;
        public SNAdapter( @Nullable List<BusinessDetailBean.DataBean.LineChargingBean> data) {
            super(R.layout.adapter_sn, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, BusinessDetailBean.DataBean.LineChargingBean item) {


            helper.setText(R.id.sn,item.charging_id);
            if (helper.getAdapterPosition()==0){
                helper.setText(R.id.type,"充电线SN码");
            }



        }
    }
}
