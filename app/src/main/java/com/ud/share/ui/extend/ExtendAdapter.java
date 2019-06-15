package com.ud.share.ui.extend;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;
import com.ud.share.utils.AppData;

import java.util.List;

/**
 * Created by PP on 2019/3/6.
 */
public class ExtendAdapter extends BaseQuickAdapter<AppData.ExtendBean, BaseViewHolder> {
    public ExtendAdapter( @Nullable List<AppData.ExtendBean> data) {
        super(R.layout.adapter_extend, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppData.ExtendBean item) {
        helper.setBackgroundRes(R.id.bg,item.bg)
                .setText(R.id.name,item.name)
                .setImageResource(R.id.icon,item.icon);


    }
}
