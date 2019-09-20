package com.ud.share.ui.home.page6;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.model.CashBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.utils.StatusUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Created by PP on 2019/4/1.
 */
public class CashItemFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    private List<CashBean.DataBeanX.DataBean> mCashItems=new ArrayList<>();
    private CashItemAdapter mAdapter;

    private int mPage=1;
    private CashBean mBean;
    private int mOrderStatus;
    private TextView mTotal;

    public boolean isFisrtSetTotal=true;
    private String mType;
    private TextView mTvNum;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cash_item;
    }

    @Override
    protected void init() {
        mOrderStatus = getArguments().getInt("status");
        mType = StatusUtil.getCashType(mOrderStatus);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getData();
        mAdapter = new CashItemAdapter(mCashItems);


        View v=LayoutInflater.from(getActivity()).inflate(R.layout.head_cash_get,null);
        mTvNum = v.findViewById(R.id.totalnum);
        mTotal = v.findViewById(R.id.total);
        mAdapter.addHeaderView(v);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this,mRecyclerView);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);

                if (parent.getChildAdapterPosition(view)==1){
                }else if (parent.getChildAdapterPosition(view)>=1){
                    outRect.bottom=QMUIDisplayHelper.dp2px(getActivity(),20);
                }
            }
        });

    }

    public static CashItemFragment getInstance(int status) {
        Bundle bundle = new Bundle();
        bundle.putInt("status", status);
        CashItemFragment fragment = new CashItemFragment();
        fragment.setArguments(bundle);
        return fragment;

    }


    private void getData() {
        HashMap<String,String> map=new HashMap<>();
        map.put("per_page","20");
        map.put("current_page",mPage+"");
        map.put("status",mOrderStatus+"");
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.cashList, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String s) throws IOException {
                mBean = JSON.parseObject(s, CashBean.class);
                mAdapter.addData(mBean.data.data);
                mAdapter.loadMoreComplete();

                if (isFisrtSetTotal){
                    mTotal.setVisibility(View.VISIBLE);
                    mTvNum.setVisibility(View.VISIBLE);
                    mTvNum.setText("共计数据："+mBean.data.total+"条");
                    mTotal.setText("共计金额："+mBean.data.total_amount+"元");
                    isFisrtSetTotal=false;
                }


            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        mPage++;
        if (mBean.data.last_page==mBean.data.current_page) {
            mAdapter.loadMoreEnd();
            return;
        }
          getData();
    }
}
