package com.ud.share.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.model.ItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BaseRecyclerFragment extends BaseFragment {


    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    private List<ItemBean> mDatas=new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_recycler;
    }



    @Override
    protected void init() {

        BaseItemAdapter adapter=new BaseItemAdapter(mDatas);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(adapter);

    }










}
