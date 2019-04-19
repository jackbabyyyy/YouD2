package com.yd.youd.ui.home.page;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yd.youd.R;
import com.yd.youd.model.ItemBean;

import java.util.List;

public class BusinessChangeAdapter extends BaseQuickAdapter<ItemBean, BaseViewHolder> {
    public BusinessChangeAdapter( @Nullable List<ItemBean> data) {
        super(R.layout.adapter_business_change, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemBean item) {

    }
}
