package com.yd.youd.ui.home.page5;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yd.youd.R;

import java.util.List;

/**
 * Created by PP on 2019/4/2.
 */
public class ProfitDetailAdapter extends BaseQuickAdapter<ProfitDetailItem, BaseViewHolder> {
    public ProfitDetailAdapter( @Nullable List<ProfitDetailItem> data) {
        super(R.layout.adapter_profit_detail_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProfitDetailItem item) {
        helper.setText(R.id.code,item.code)
                .setText(R.id.profit,item.profit)
                .setText(R.id.business,item.business)
                .setText(R.id.time,item.time)
                .setText(R.id.reback,item.reback)
                .setText(R.id.type,item.type);
    }



}
