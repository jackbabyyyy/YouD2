package com.yd.youd.ui.home.page;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;
import com.yd.youd.model.BusinessIncomeBean;
import com.yd.youd.model.BusinessListBean;
import com.yd.youd.model.ProxyListBean;
import com.yd.youd.net.AppUrl;
import com.yd.youd.net.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * Created by PP on 2019/3/12.
 */
public class BusinessFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.bar_left)
    ImageView mBarLeft;
    @BindView(R.id.content)
    EditText mSearch;
    @BindView(R.id.bar_right)
    TextView mBarRight;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    private List<BusinessListBean.DataBeanX.DataBean> mData=new ArrayList<>();
    private BusinessAdapter mAdapter;
    private int mPage=1;
    private BusinessListBean mBean;
    private TextView mAgentNum;
    private TextView mAgentSum;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_business;
    }

    @Override
    protected void init() {

        mSearch.setHint("请输入商户名称或者手机号码");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new BusinessAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this,mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startFragment( BusinessDetailFragment.getInstance(mAdapter.getData().get(position).sid));
            }
        });

        View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_business_head,null);
        TextView mDividerTitle = view.findViewById(R.id.divider_title);
        mDividerTitle.setText("商户列表");
        mAgentNum = view.findViewById(R.id.agent_num);
        mAgentSum = view.findViewById(R.id.agent_sum);
        mAdapter.addHeaderView(view);

        getBusinessList();
        getBusinessIncome();

    }

    private void getBusinessList() {
        Map<String, String> map = new HashMap<>();
        map.put("per_page", "10");
        map.put("page", mPage + "");
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.businessGet, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {
                        mBean = JSON.parseObject(response, BusinessListBean.class);
                        mAdapter.addData(mBean.data.data);
                        mAdapter.loadMoreComplete();
                    }
                });

    }

    private void getBusinessIncome(){

        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.businessIncome, null, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {

                        BusinessIncomeBean bean=JSON.parseObject(response,BusinessIncomeBean.class);
                        mAgentNum.setText(bean.data.sum_sellers);
                        mAgentSum.setText(bean.data.total_rent);



                    }
                });

    }


    @OnClick({R.id.bar_left, R.id.bar_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bar_left:
                popBackStack();
                break;
            case R.id.bar_right:
                break;
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mPage++;
        if (mBean.data.last_page.equals("1")) {
            mAdapter.loadMoreEnd();
            return;
        }
        getBusinessList();

    }
}
