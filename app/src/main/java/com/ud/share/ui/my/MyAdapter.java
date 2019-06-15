package com.ud.share.ui.my;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;
import com.ud.share.utils.AppData;

import java.util.List;

/**
 * Created by PP on 2019/3/6.
 */
public class MyAdapter extends BaseQuickAdapter<AppData.MyBean, BaseViewHolder> {
    public MyAdapter( @Nullable List<AppData.MyBean> data) {
        super(R.layout.adapter_my, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppData.MyBean item) {
        helper.setText(R.id.tv,item.name);


        Drawable icon=mContext.getResources().getDrawable(item.icon);
        icon.setBounds(0,0,icon.getMinimumWidth(),icon.getMinimumHeight());
        Drawable right=mContext.getResources().getDrawable(R.mipmap.icon_mine_arrow);
        right.setBounds(0,0,right.getMinimumWidth(),right.getMinimumHeight());
        ((TextView)helper.getView(R.id.tv)).setCompoundDrawables(icon,null,right,null);



    }
}
