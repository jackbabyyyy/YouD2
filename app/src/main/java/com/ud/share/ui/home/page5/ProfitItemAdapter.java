package com.ud.share.ui.home.page5;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;

import java.util.List;

/**
 * Created by PP on 2019/4/2.
 */
public class ProfitItemAdapter extends BaseQuickAdapter<ProfitItem, BaseViewHolder> {
    public ProfitItemAdapter( @Nullable List<ProfitItem> data) {
        super(R.layout.adapter_profit_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProfitItem item) {
        helper
                .setText(R.id.name,item.name)
                .setText(R.id.profit,item.profit)
                .setText(R.id.phone,"手机号码："+item.phone)
                .setText(R.id.profit,item.order);

        Glide.with(mContext).load(item.img).into((ImageView)helper.getView(R.id.img));



    }
}
