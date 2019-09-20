package com.ud.share.ui.statistics;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;
import com.ud.share.model.SearchOneBean;

import java.util.List;

/**
 * Created by PP on 2019-07-04.
 */
public class SearchOneAdapter extends BaseQuickAdapter<SearchOneBean.DataBean, BaseViewHolder> {
    public SearchOneAdapter( @Nullable List<SearchOneBean.DataBean> data) {
        super(R.layout.adapter_search_one, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchOneBean.DataBean item) {
        String s=item.user_type.equals("1")?"[代理商]  ":"[商户]    ";
        helper.setText(R.id.name,s+item.name);

    }
}
