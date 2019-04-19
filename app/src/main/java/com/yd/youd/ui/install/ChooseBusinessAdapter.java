package com.yd.youd.ui.install;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yd.youd.R;

import java.util.List;

/**
 * Created by PP on 2019/4/4.
 */
public class ChooseBusinessAdapter extends BaseQuickAdapter<ChooseBusinessItem, BaseViewHolder> {
    public ChooseBusinessAdapter( @Nullable List<ChooseBusinessItem> data) {
        super(R.layout.adapter_choose_business, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChooseBusinessItem item) {
        helper.setText(R.id.name,item.name);
        Glide.with(mContext).load(item.icon).into((ImageView) helper.getView(R.id.icon));

    }
}
