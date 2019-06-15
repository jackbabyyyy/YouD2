package com.ud.share.ui.install;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;
import com.ud.share.model.BusinessListBean;

import java.util.List;

/**
 * Created by PP on 2019/4/4.
 */
public class ChooseBusinessAdapter extends BaseQuickAdapter<BusinessListBean.DataBeanX.DataBean, BaseViewHolder> {
    public ChooseBusinessAdapter( @Nullable List<BusinessListBean.DataBeanX.DataBean> data) {
        super(R.layout.adapter_choose_business, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BusinessListBean.DataBeanX.DataBean item) {
        helper.setText(R.id.name,item.shop_name);
        Glide.with(mContext).load(item.picture).into((ImageView) helper.getView(R.id.icon));

    }
}
