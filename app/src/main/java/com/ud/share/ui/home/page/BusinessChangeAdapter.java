package com.ud.share.ui.home.page;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;
import com.ud.share.model.ItemBean;

import java.util.List;

public class BusinessChangeAdapter extends BaseQuickAdapter<ItemBean, BaseViewHolder> {
    public BusinessChangeAdapter( @Nullable List<ItemBean> data) {
        super(R.layout.adapter_business_change, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemBean item) {

    }
}
