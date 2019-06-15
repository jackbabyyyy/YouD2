package com.ud.share.ui.home.page6;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;
import com.ud.share.model.CashBean;
import com.ud.share.utils.StatusUtil;

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
                .setText(R.id.tvCash,item.amount+"元");

        TextView tvStatus=helper.getView(R.id.tvStatus);
        if (TextUtils.isEmpty(item.notes)){
            tvStatus.setText(StatusUtil.getCashStatus(item.status));
        }else{
            tvStatus.setText(StatusUtil.getCashStatus(item.status)+"("+item.notes+")");
        }


        TextView status=helper.getView(R.id.tvStatus);
        if (item.status.equals("2")){
            status.setTextColor(mContext.getResources().getColor(R.color.theme));
        }else{
            status.setTextColor(mContext.getResources().getColor(R.color.error));
        }



    }
}
