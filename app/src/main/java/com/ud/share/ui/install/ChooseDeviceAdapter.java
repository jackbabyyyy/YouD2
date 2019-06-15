package com.ud.share.ui.install;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;
import com.ud.share.model.DeviceListBean;

import java.util.List;

/**
 * Created by PP on 2019/4/4.
 */
public class ChooseDeviceAdapter extends BaseQuickAdapter<DeviceListBean.DataBeanX.DataBean, BaseViewHolder> {

//    root.setBackgroundResource(position==selectedPosition?R.drawable.map_check:R.drawable.map_uncheck);
//            imageCheck.setImageResource(position==selectedPosition?R.mipmap.map_check:R.mipmap.map_uncheck);
//            textTitle.setTextColor(position==selectedPosition?context.getResources().getColor(R.color.theme):context.getResources().getColor(R.color.color333));

    public ChooseDeviceAdapter( @Nullable List<DeviceListBean.DataBeanX.DataBean> data) {
        super(R.layout.adapter_choose_device, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceListBean.DataBeanX.DataBean item) {
        helper.setText(R.id.code,item.sn);
        TextView canChoose=helper.getView(R.id.canChoose);
        ImageView check=helper.getView(R.id.iv);
        if (item.is_distribution.equals("1")){
            canChoose.setVisibility(View.GONE);
            check.setVisibility(View.GONE);
        }else {
            canChoose.setVisibility(View.VISIBLE);
            check.setVisibility(View.VISIBLE);
        }

        if(item.check){
            check.setImageResource(R.mipmap.selected);
        }else{
            check.setImageResource(R.mipmap.unselect);
        }




    }
}
