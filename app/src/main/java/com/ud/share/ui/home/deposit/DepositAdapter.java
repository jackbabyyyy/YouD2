package com.ud.share.ui.home.deposit;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;
import com.ud.share.model.DepositBean;
import com.ud.share.utils.StatusUtil;

import java.util.List;

/**
 * Created by PP on 2019/4/3.
 */
public class DepositAdapter extends BaseQuickAdapter<DepositBean.DataBean, BaseViewHolder> {
    public DepositAdapter( @Nullable List<DepositBean.DataBean> data) {
        super(R.layout.adapter_deposit, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DepositBean.DataBean item) {

        helper.setText(R.id.code,"押金编号："+item.order_no)
                .setText(R.id.status, StatusUtil.getDepositStatus(item.status))
                .setText(R.id.name,item.get_user.name)
                .setText(R.id.time,item.create_time)
                .setText(R.id.deposit,item.deposit+"元");

    }


}
