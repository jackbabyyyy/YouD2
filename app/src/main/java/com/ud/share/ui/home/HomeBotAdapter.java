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
public class HomeBotAdapter extends BaseQuickAdapter<AppData.HomeBotBean, BaseViewHolder> {
    public HomeBotAdapter( @Nullable List<AppData.HomeBotBean> data) {
        super(R.layout.adapter_home_bot
                , data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppData.HomeBotBean item) {
        helper.setText(R.id.name,item.name)
                .setText(R.id.des,item.des)
                .setImageResource(R.id.icon,item.icon);

    }
}
