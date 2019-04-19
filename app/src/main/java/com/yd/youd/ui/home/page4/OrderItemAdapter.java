package com.yd.youd.ui.home.page4;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yd.youd.R;
import com.yd.youd.model.OrderBean;
import com.yd.youd.utils.StatusUtil;

import java.util.List;

/**
 * Created by PP on 2019/4/1.
 */
public class OrderItemAdapter extends BaseQuickAdapter<OrderBean.DataBean.ListBean, BaseViewHolder> {

    public OrderItemAdapter( @Nullable List<OrderBean.DataBean.ListBean> data) {
        super(R.layout.adapter_order_item, data);

    }


    @Override
    protected void convert(BaseViewHolder helper, OrderBean.DataBean.ListBean item) {
        helper.setText(R.id.code,"订单编号："+item.order_no)
                .setText(R.id.tvBusiness,item.seller_name)
                .setText(R.id.tvTime,item.starttime)
                .setText(R.id.tvReback,item.endtime)
                .setText(R.id.tvSim,item.device_code)
                .setText(R.id.tvProfit,item.profit)
                .setText(R.id.tvStatus, StatusUtil.getOrderStatus(item.status))
                .setText(R.id.tvType,"设备类型："+StatusUtil.getOrderType(item.equipment_type));

        TextView status=helper.getView(R.id.tvStatus);
        Log.d(TAG, "convert: "+item.status);
        if (item.status.equals("2")){//已完成
            status.setTextColor(mContext.getResources().getColor(R.color.theme));
        }else{
            status.setTextColor(mContext.getResources().getColor(R.color.error));
        }



    }


}
