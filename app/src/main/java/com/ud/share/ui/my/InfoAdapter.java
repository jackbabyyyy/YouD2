package com.ud.share.ui.my;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;
import com.ud.share.utils.AppData;

import java.util.List;

/**
 * Created by PP on 2019/3/9.
 */
public class InfoAdapter extends BaseQuickAdapter<AppData.InfoBean, BaseViewHolder> {


    public InfoAdapter( @Nullable List<AppData.InfoBean> data) {
        super(R.layout.adapter_info, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppData.InfoBean item) {
        helper.setText(R.id.title,item.title)
                .setText(R.id.content,item.name);

    }




}
