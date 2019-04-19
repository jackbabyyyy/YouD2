package com.yd.youd.ui.install;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yd.youd.R;

import java.util.List;

/**
 * Created by PP on 2019/4/4.
 */
public class ChooseDeviceAdapter extends BaseQuickAdapter<ChooseDeviceItem, BaseViewHolder> {
    public ChooseDeviceAdapter( @Nullable List<ChooseDeviceItem> data) {
        super(R.layout.adapter_choose_device, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChooseDeviceItem item) {
        helper.setText(R.id.code,item.code);


    }
}
