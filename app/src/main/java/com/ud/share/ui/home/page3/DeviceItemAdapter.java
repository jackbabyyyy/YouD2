package com.ud.share.ui.home.page3;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;
import com.ud.share.model.DeviceListBean;

import java.util.List;

/**
 * Created by PP on 2019/3/26.
 */
public class DeviceItemAdapter extends BaseQuickAdapter<DeviceListBean.DataBeanX.DataBean, BaseViewHolder> {
    public DeviceItemAdapter( @Nullable List<DeviceListBean.DataBeanX.DataBean> data) {
        super(R.layout.adapter_device_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceListBean.DataBeanX.DataBean item) {
        helper.setText(R.id.code,"设备编码："+item.sn)
                .setText(R.id.tvName,item.shop_name);
        TextView status=helper.getView(R.id.tvStatus);
        View business=helper.getView(R.id.business);
        if (item.is_distribution.equals("1")){//已经铺货
            status.setText("");
            business.setVisibility(View.VISIBLE);
        }else{
            status.setText("未铺货");
            business.setVisibility(View.GONE);
        }


    }
}
