package com.yd.youd.ui.home.deposit;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yd.youd.R;
import com.yd.youd.model.DepositBean;
import com.yd.youd.utils.StatusUtil;

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
