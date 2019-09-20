package com.ud.share.ui.install;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ud.share.R;
import com.ud.share.model.DeviceListBean;

import java.net.HttpRetryException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by PP on 2019/4/4.
 */
public class ChooseDeviceAdapter2 extends BaseQuickAdapter<String , BaseViewHolder> {

//    root.setBackgroundResource(position==selectedPosition?R.drawable.map_check:R.drawable.map_uncheck);
//            imageCheck.setImageResource(position==selectedPosition?R.mipmap.map_check:R.mipmap.map_uncheck);
//            textTitle.setTextColor(position==selectedPosition?context.getResources().getColor(R.color.theme):context.getResources().getColor(R.color.color333));



    public List<String> mChecks=new ArrayList<>();
    public ChooseDeviceAdapter2(@Nullable List<String > data) {
        super(R.layout.adapter_choose_device2, data);



    }

    @Override
    protected void convert(BaseViewHolder helper, String  item) {
        helper.setText(R.id.code,item);
        ImageView check=helper.getView(R.id.iv);
        View root=helper.getView(R.id.root);

        if(mChecks.contains(item)){
            root.setBackgroundResource(R.mipmap.device_check);
            check.setImageResource(R.mipmap.selected);
        }else{
            root.setBackgroundResource(R.mipmap.device_uncheck);
            check.setImageResource(R.mipmap.unselect);
        }






    }


}
