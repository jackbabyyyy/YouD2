package com.ud.share.ui.shop;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;

import java.util.List;

/**
 * Created by PP on 2019/6/12.
 */
public class ShopAdapter extends BaseQuickAdapter<ShopItem, BaseViewHolder> {
    public ShopAdapter( @Nullable List<ShopItem> data) {
        super(R.layout.adapter_shop, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopItem item) {
        helper.setImageResource(R.id.icon,item.icon)
                .setText(R.id.title,item.title);

    }
}
