package com.ud.share.ui.my;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;
import com.ud.share.utils.AppData;

import java.util.List;

/**
 * Created by PP on 2019/3/11.
 */
public class InfoChangeAdapter extends BaseQuickAdapter<AppData.InfoBean, BaseViewHolder> {


    public InfoChangeAdapter( @Nullable List<AppData.InfoBean> data) {
        super(R.layout.adapter_info_change, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppData.InfoBean item) {
        helper.setText(R.id.title,item.title).setText(R.id.content,item.name);

    }


}
