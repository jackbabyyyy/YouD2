package com.yd.youd.ui.home.page6;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yd.youd.R;
import com.yd.youd.model.CashBean;
import com.yd.youd.utils.StatusUtil;

import java.util.List;

/**
 * Created by PP on 2019/4/1.
 */
public class CashItemAdapter extends BaseQuickAdapter<CashBean.DataBeanX.DataBean, BaseViewHolder> {
    public CashItemAdapter( @Nullable List<CashBean.DataBeanX.DataBean> data) {
        super(R.layout.adapter_cash_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CashBean.DataBeanX.DataBean item) {
        helper.setText(R.id.code,"订单编号："+item.order_num)
                .setText(R.id.tvTime,item.addtime)
                .setText(R.id.tvStatus, StatusUtil.getCashStatus(item.status))
                .setText(R.id.tvCash,item.amount+"元");

        TextView status=helper.getView(R.id.tvStatus);
        if (item.status.equals("2")){
            status.setTextColor(mContext.getResources().getColor(R.color.theme));
        }else{
            status.setTextColor(mContext.getResources().getColor(R.color.error));
        }



    }
}
