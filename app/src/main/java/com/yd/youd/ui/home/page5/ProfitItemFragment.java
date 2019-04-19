package com.yd.youd.ui.home.page5;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by PP on 2019/4/2.
 */
public class ProfitItemFragment extends BaseFragment {


    @BindView(R.id.divider_title)
    TextView mDividerTitle;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    private List<ProfitItem> mProfitItems=new ArrayList<>();
    private ProfitItemAdapter mAdafpter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profit_item;
    }

    @Override
    protected void init() {
        mDividerTitle.setText("代理商列表");


        getData();
        mAdafpter = new ProfitItemAdapter(mProfitItems);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(mAdafpter);
        mRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom= QMUIDisplayHelper.dp2px(getActivity(),20);
            }
        });

        mAdafpter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


            }
        });


    }

    private void getData() {
        //todo 真实数据
        ProfitItem item=new ProfitItem();
        item.img="";
        item.name="王向东";
        item.order="282";
        item.profit="546.00元";
        item.phone="12372284423";
        for (int i=0;i<10;i++){
            mProfitItems.add(item);
        }
    }


}
