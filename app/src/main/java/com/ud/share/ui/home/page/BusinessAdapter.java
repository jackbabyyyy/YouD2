package com.ud.share.ui.home.page;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;
import com.ud.share.model.BusinessListBean;

import java.util.List;

/**
 * Created by PP on 2019/3/15.
 */
public class BusinessAdapter extends BaseQuickAdapter<BusinessListBean.DataBeanX.DataBean, BaseViewHolder> {


    private RoundedCorners mRoundedCorners;
    private RequestOptions mOptions;

    public BusinessAdapter(@Nullable List<BusinessListBean.DataBeanX.DataBean> data) {
        super(R.layout.adapter_business, data);
        mRoundedCorners = new RoundedCorners(15);
        mOptions = RequestOptions.bitmapTransform(mRoundedCorners).override(300, 300);
    }

    @Override
    protected void convert(BaseViewHolder helper, BusinessListBean.DataBeanX.DataBean item) {
        helper.setText(R.id.name,item.shop_name)
                .setText(R.id.addr,item.area+item.addr)
                .setText(R.id.date,item.ctime)
                .setText(R.id.rent,item.total_rent+"元");

        Glide.with(mContext).load(item.picture).apply(mOptions).into((ImageView) helper.getView(R.id.imageView));

        View d=helper.getView(R.id.divider);
        if (helper.getAdapterPosition()!=getData().size()){
            d.setVisibility(View.VISIBLE);
        }else{
            d.setVisibility(View.INVISIBLE);
        }
    }


}
