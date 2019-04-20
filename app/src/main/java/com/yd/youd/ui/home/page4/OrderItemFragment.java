package com.yd.youd.ui.home.page4;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;
import com.yd.youd.model.OrderBean;
import com.yd.youd.net.AppUrl;
import com.yd.youd.net.HttpUtil;
import com.yd.youd.ui.home.page6.CashItem;
import com.yd.youd.ui.home.page6.CashItemAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Created by PP on 2019/4/1.
 */
public class OrderItemFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    private List<OrderBean.DataBean.ListBean> mOrderItems = new ArrayList<>();
    private OrderItemAdapter mAdapter;

    private int mPage = 1;
    private int mOrderStatus = -1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_item;
    }

    @Override
    protected void init() {

        mOrderStatus = getArguments().getInt("status");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new OrderItemAdapter(mOrderItems);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom=QMUIDisplayHelper.dp2px(getActivity(),20);
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.top = QMUIDisplayHelper.dp2px(getActivity(), 20);
                }
            }
        });


        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String id=mAdapter.getData().get(position).id;
                String no=mAdapter.getData().get(position).order_no;
                startFragment( OrderDetailFragment.getInstance(id,no));
            }
        });

        getData();

    }

    public static OrderItemFragment getInstance(int status) {
        Bundle bundle = new Bundle();
        bundle.putInt("status", status);
        OrderItemFragment fragment = new OrderItemFragment();
        fragment.setArguments(bundle);
        return fragment;

    }

    private void getData() {

        HashMap<String, String> map = new HashMap<>();
        map.put("order_status", mOrderStatus + "");
        map.put("page", mPage + "");
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.orderGet, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) throws IOException {
                OrderBean bean = JSON.parseObject(response, OrderBean.class);
                if (bean.data.list.size() != 0) {
                    mAdapter.addData(bean.data.list);
                    mAdapter.loadMoreComplete();
                } else {
                    mAdapter.loadMoreEnd();
                }


            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        mPage++;
        getData();

    }
}
