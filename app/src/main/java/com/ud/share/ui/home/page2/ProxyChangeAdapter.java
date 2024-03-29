package com.ud.share.ui.home.page2;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;
import com.ud.share.utils.AppData;

import java.util.List;

/**
 * Created by PP on 2019/3/14.
 */
public class ProxyChangeAdapter extends BaseQuickAdapter<AppData.AgentInfoBean, BaseViewHolder> {
    public ProxyChangeAdapter(@Nullable List<AppData.AgentInfoBean> data) {
        super(R.layout.adapter_proxy_change,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppData.AgentInfoBean item) {
        helper.setText(R.id.title,item.title)
                .setText(R.id.des,item.des);
        EditText content=helper.getView(R.id.content);
        content.setText(item.content);
        TextView des=helper.getView(R.id.des);
        if (TextUtils.isEmpty(item.des)){
            des.setVisibility(View.GONE);
        }else{
            des.setVisibility(View.VISIBLE);
        }
        if (item.title.contains("联系方式")||item.title.contains("姓  名")){
            content.setBackground(null);
        }else{
            content.setBackgroundResource(R.drawable.edit_bg);
        }


    }

}
