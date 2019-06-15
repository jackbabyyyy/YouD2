package com.ud.share.ui.home.page;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;

import java.util.List;

/**
 * Created by PP on 2019/3/26.
 */
public class BusinessTariffAdapter extends BaseQuickAdapter<TariffItem, BaseViewHolder> {
    public BusinessTariffAdapter( @Nullable List<TariffItem> data) {
        super(R.layout.adapter_tafiff, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TariffItem item) {
        helper.setText(R.id.time,item.time)
                .setText(R.id.value,item.value);



    }
}
