package com.ud.share.base;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;
import com.ud.share.model.ItemBean;

import java.util.List;

public class BaseItemAdapter extends BaseQuickAdapter<ItemBean, BaseViewHolder> {
    public BaseItemAdapter( @Nullable List<ItemBean> data) {
        super(R.layout.adapter_base_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemBean item) {

        helper.setText(R.id.title,item.title)
                .setText(R.id.content,item.content)
                .setText(R.id.des,item.des);

    }
}
