package com.yd.youd.ui.home.page;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yd.youd.R;
import com.yd.youd.model.BusinessListBean;

import java.util.List;

public class BusinessDetailAdapter extends BaseQuickAdapter<Item, BaseViewHolder> {
    public BusinessDetailAdapter(@Nullable List<Item> data) {
        super(R.layout.adapter_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Item item) {

    }
}
