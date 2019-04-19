package com.yd.youd.ui.home.page;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yd.youd.R;
import com.yd.youd.model.BusinessListBean;

import java.util.List;

/**
 * Created by PP on 2019/3/15.
 */
public class BusinessAdapter extends BaseQuickAdapter<BusinessListBean.DataBeanX.DataBean, BaseViewHolder> {
    public BusinessAdapter( @Nullable List<BusinessListBean.DataBeanX.DataBean> data) {
        super(R.layout.adapter_business, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BusinessListBean.DataBeanX.DataBean item) {
        helper.setText(R.id.name,item.shop_name)
                .setText(R.id.addr,item.addr)
                .setText(R.id.date,item.business_hours)
                .setText(R.id.rent,item.total_rent);
        Glide.with(mContext).load(item.sid).into((ImageView) helper.getView(R.id.imageView));
    }


}
