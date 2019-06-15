package com.ud.share.ui.home.page;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;

import java.util.List;

public class BusinessDetailAdapter extends BaseQuickAdapter<Item, BaseViewHolder> {
    public BusinessDetailAdapter(@Nullable List<Item> data) {
        super(R.layout.adapter_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Item item) {

    }
}
