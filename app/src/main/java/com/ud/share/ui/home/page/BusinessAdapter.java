package com.ud.share.ui.home.page;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;
import com.ud.share.model.BusinessListBean;

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
                .setText(R.id.addr,item.area+item.addr)
                .setText(R.id.date,item.ctime)
                .setText(R.id.distance,item.distance)
                .setText(R.id.profit,"分润   "+"线"+item.line_rate+"柜"+item.cabinet_rate)
                .setText(R.id.n1,item.cabinet_count)
                .setText(R.id.n2,item.chargingline_count)
                .setText(R.id.rent,item.total_rent);
        Glide.with(mContext).load(item.picture).into((ImageView) helper.getView(R.id.imageView));
    }


}
