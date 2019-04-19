package com.yd.youd.ui.home.page2;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.yd.youd.R;
import com.yd.youd.model.ProxyListBean;

import java.util.List;

/**
 * Created by PP on 2019/3/13.
 */
public class ProxyAdapter extends BaseQuickAdapter<ProxyListBean.DataBeanX.DataBean, BaseViewHolder> {
    public ProxyAdapter(@Nullable List<ProxyListBean.DataBeanX.DataBean> data ) {
        super(R.layout.adapter_proxy, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProxyListBean.DataBeanX.DataBean item) {

        helper.setText(R.id.name,item.real_name)
                .setText(R.id.phone,item.phone)
                .setText(R.id.agent,"下级代理总数："+item.sum_agents+"个")
                .setText(R.id.income,"总收益："+item.total_rent+"元")
        .addOnClickListener(R.id.edit);

        RecyclerView recyclerView=helper.getView(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        ProxyItemAdapter adapter=new ProxyItemAdapter(item.equipment);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom= QMUIDisplayHelper.dp2px(mContext,10);
                if (parent.getChildAdapterPosition(view)==0){
                    outRect.top=QMUIDisplayHelper.dp2px(mContext,10);
                }
            }
        });
        recyclerView.setAdapter( adapter);

    }


    public class ProxyItemAdapter extends BaseQuickAdapter<ProxyListBean.DataBeanX.DataBean.EquipmentBean,BaseViewHolder>{
        public ProxyItemAdapter( @Nullable List<ProxyListBean.DataBeanX.DataBean.EquipmentBean> data) {
            super(R.layout.adapter_proxy_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ProxyListBean.DataBeanX.DataBean.EquipmentBean item) {
            helper.setText(R.id.equipment,"设备型号："+item.model)
                    .setText(R.id.num_g,item.total+"个")
                    .setText(R.id.num,item.distribution);
        }
    }



}
