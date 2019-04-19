package com.yd.youd.ui.extend;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yd.youd.R;

import java.util.List;

/**
 * Created by PP on 2019/4/10.
 */
public class WordAdapter extends BaseQuickAdapter<WordItem , BaseViewHolder> {
    public WordAdapter(@Nullable List<WordItem> data) {
        super(R.layout.adapter_word, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WordItem item) {
        helper.addOnClickListener(R.id.saveIV)
                .addOnClickListener(R.id.saveText);

    }
}
