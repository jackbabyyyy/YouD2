package com.ud.share.ui.home;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;
import com.ud.share.utils.AppData;

import java.util.List;

/**
 * Created by PP on 2019/3/6.
 */
public class HomeMidAdapter extends BaseQuickAdapter<AppData.HomeMidBean, BaseViewHolder> {
    public HomeMidAdapter( @Nullable List<AppData.HomeMidBean> data) {
        super(R.layout.adapter_home_mid, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppData.HomeMidBean item) {
        helper.setText(R.id.name,item.name)
                .setImageResource(R.id.icon,item.icon);

    }
}
