package com.ud.share.ui.home.page3;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;
import com.ud.share.model.AllDeviceBean;
import com.ud.share.model.DeviceListBean;

import java.util.List;

/**
 * Created by PP on 2019/3/26.
 */
public class DeviceItemAdapter extends BaseQuickAdapter<AllDeviceBean.DataBean, BaseViewHolder> {

    private int type=-1;

    public DeviceItemAdapter( @Nullable List<AllDeviceBean.DataBean> data,int type) {
        super(R.layout.adapter_device_item, data);
        this.type=type;
    }

    @Override
    protected void convert(BaseViewHolder helper, AllDeviceBean.DataBean item) {
        helper.setText(R.id.code,item.charging_id);

        TextView status=helper.getView(R.id.tvStatus);


        if (type==1){
            status.setText(item.name);
        }else{
            status.setVisibility(View.GONE);
        }



    }
}
