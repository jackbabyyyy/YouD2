package com.ud.share.utils;

import com.bumptech.glide.request.RequestOptions;
import com.ud.share.R;

public class GlideManager {


    public static RequestOptions getGlideOptions() {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.icon_mine_avatar)//图片加载出来前，显示的图片
                .fallback(R.mipmap.icon_mine_avatar) //url为空的时候,显示的图片
                .error(R.mipmap.icon_mine_avatar);
        return options;
    }
}
