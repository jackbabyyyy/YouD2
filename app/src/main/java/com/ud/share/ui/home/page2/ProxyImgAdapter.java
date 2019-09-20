package com.ud.share.ui.home.page2;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;

import java.io.File;
import java.util.List;

/**
 * Created by PP on 2019-08-21.
 */
public class ProxyImgAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public String[] imgArray=new String[3];

    public ProxyImgAdapter( @Nullable List<String> data) {
        super(R.layout.adapter_proxy_img, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, String item) {

        if (!TextUtils.isEmpty(item)){

            Glide.with(mContext).load(Uri.fromFile(new File(item))).into((ImageView) helper.getView(R.id.iv));
        }


    }
}
