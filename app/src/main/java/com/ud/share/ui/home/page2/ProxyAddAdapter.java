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
public class ProxyAddAdapter extends BaseQuickAdapter<AppData.AgentInfoBean, BaseViewHolder> {
    public ProxyAddAdapter(@Nullable List<AppData.AgentInfoBean> data) {
        super(R.layout.adapter_proxy_add,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppData.AgentInfoBean item) {
        helper.setText(R.id.title,item.title)
                .setText(R.id.des,item.des);
        EditText content=helper.getView(R.id.content);
        content.setHint(item.hint);
        TextView des=helper.getView(R.id.des);
        if (TextUtils.isEmpty(item.des)){
            des.setVisibility(View.GONE);
        }else{
            des.setVisibility(View.VISIBLE);
        }

    }
}
