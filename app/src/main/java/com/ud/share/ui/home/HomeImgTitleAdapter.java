package com.ud.share.ui.home;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;

import java.util.List;

/**
 * Created by PP on 2019/3/6.
 */
public class HomeImgTitleAdapter extends BaseQuickAdapter<ImgTitleBean, BaseViewHolder> {
    public HomeImgTitleAdapter(int lay,@Nullable List<ImgTitleBean> data) {
        super(lay, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ImgTitleBean item) {


        TextView icon=helper.getView(R.id.name);
        if (TextUtils.isEmpty(item.title)){
            icon.setVisibility(View.GONE);
        }

        helper.setText(R.id.name,item.title)
                .setImageResource(R.id.icon,item.icon);


    }
}
