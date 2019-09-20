package com.ud.share.ui.install;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;
import com.ud.share.model.ProxyListBean;

import java.util.List;

/**
 * Created by PP on 2019-09-06.
 */
public class ChooseProxyAdapetr extends BaseQuickAdapter<ProxyListBean.DataBeanX.DataBean, BaseViewHolder> {
    public ChooseProxyAdapetr( @Nullable List<ProxyListBean.DataBeanX.DataBean> data) {
        super(R.layout.adapter_choose_proxy, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProxyListBean.DataBeanX.DataBean item) {
        helper.setText(R.id.name,item.agent_name)
                .setText(R.id.level,"["+item.agetn_level+"级]")
                .setText(R.id.extra,item.real_name+"   "+item.phone+"   ");


    }
}
