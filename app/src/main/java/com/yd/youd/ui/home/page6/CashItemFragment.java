package com.yd.youd.ui.home.page6;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;
import com.yd.youd.model.CashBean;
import com.yd.youd.net.AppUrl;
import com.yd.youd.net.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Created by PP on 2019/4/1.
 */
public class CashItemFragment extends BaseFragment {

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    private List<CashBean.DataBeanX.DataBean> mCashItems=new ArrayList<>();
    private CashItemAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cash_item;
    }

    @Override
    protected void init() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getData();
        mAdapter = new CashItemAdapter(mCashItems);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom=QMUIDisplayHelper.dp2px(getActivity(),20);
                if (parent.getChildAdapterPosition(view)==0){
                    outRect.top= QMUIDisplayHelper.dp2px(getActivity(),20);
                }
            }
        });

    }

    private void getData() {
        HashMap<String,String> map=new HashMap<>();
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.cashList, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String s) throws IOException {
                CashBean bean= JSON.parseObject(s,CashBean.class);
                mAdapter.setNewData(bean.data.data);



            }
        });
    }
}
