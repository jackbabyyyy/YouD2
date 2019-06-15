package com.ud.share.ui.install;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;

import java.util.List;

/**
 * Created by PP on 2019/6/1.
 */
public class SearchAdapter extends BaseQuickAdapter<String , BaseViewHolder> {
    public SearchAdapter( @Nullable List<String> data) {
        super(R.layout.adapter_device_search, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.text,item);

        
    }
}
