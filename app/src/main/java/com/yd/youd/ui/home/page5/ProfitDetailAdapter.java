package com.yd.youd.ui.home.page5;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yd.youd.R;
import com.yd.youd.model.ProfitListBean;

import java.util.List;

/**
 * Created by PP on 2019/4/2.
 */
public class ProfitDetailAdapter extends BaseQuickAdapter<ProfitListBean.DataBeanX.DataBean, BaseViewHolder> {
    public ProfitDetailAdapter( @Nullable List<ProfitListBean.DataBeanX.DataBean> data) {
        super(R.layout.adapter_profit_detail_item, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, ProfitListBean.DataBeanX.DataBean item) {
        helper.setText(R.id.code,"订单编号："+item.order_no)
                .setText(R.id.profit,"+"+item.profit+"元")
                .setText(R.id.business,item.shop_name)
                .setText(R.id.time,item.starttime)
                .setText(R.id.reback,item.endtime)
                .setText(R.id.type,item.model);
    }



}
