package com.yd.youd.ui.home.page5;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by PP on 2019/4/2.
 */
public class ProfitDetailFragment extends BaseFragment {
    @BindView(R.id.bar_left)
    ImageView mBarLeft;
    @BindView(R.id.content)
    EditText mContent;
    @BindView(R.id.bar_right)
    TextView mBarRight;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.type)
    TextView mType;
    @BindView(R.id.textView4)
    TextView mTextView4;
    @BindView(R.id.textView6)
    TextView mTextView6;
    @BindView(R.id.textView8)
    TextView mTextView8;
    @BindView(R.id.profit)
    TextView mProfit;
    @BindView(R.id.divider_title)
    TextView mDividerTitle;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;


    private List<ProfitDetailItem> mProfitDetailItems=new ArrayList<>();
    private ProfitDetailAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profit_detail;
    }

    @Override
    protected void init() {

        getData();
        mAdapter = new ProfitDetailAdapter(mProfitDetailItems);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(mAdapter);



    }

    private void getData() {
        //TODO 真实数据
        ProfitDetailItem item=new ProfitDetailItem();
        item.business="ksfsf";
        item.code="7324823234";
        item.profit="+4.2yuan";
        item.reback="2012";
        item.time="2013";
        item.type="mc800";
        for (int i=0;i<10;i++){
            mProfitDetailItems.add(item);
        }
    }


    @OnClick({R.id.bar_left, R.id.bar_right, R.id.name, R.id.type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bar_left:
                break;
            case R.id.bar_right:
                break;
            case R.id.name:
                break;
            case R.id.type:
                break;

        }
    }
}
