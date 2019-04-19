package com.yd.youd.ui.home.page2;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;
import com.yd.youd.model.ProxyIncomeBean;
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
public class ProxyFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemChildClickListener, View.OnClickListener {

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.bar_left)
    View mBarLeft;
    @BindView(R.id.bar_right)
    View mBarRight;
    @BindView(R.id.content)
    EditText mSearch;
    @BindView(R.id.bn_agent_add)
    Button mButton;



    private List<ProxyListBean.DataBeanX.DataBean> mDatas = new ArrayList<>();
    private ProxyAdapter mAdapter;

    private int mPage = 1;
    private ProxyListBean mBean;
    private TextView mMAgentNum;
    private TextView mMAgentSum;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_proxy;
    }

    @Override
    protected void init() {

        View head = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_proxy_head, null);
        TextView mDividerTitle = head.findViewById(R.id.divider_title);
        mMAgentNum = head.findViewById(R.id.agent_num);
        mMAgentSum = head.findViewById(R.id.agent_sum);
        mDividerTitle.setText("代理商列表");

        //
        mSearch.setHint("请输入代理商名称或者手机号码");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ProxyAdapter(mDatas);
        mAdapter.addHeaderView(head);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setLoadMoreView(new LoadMoreView() {
            @Override
            public int getLayoutId() {
                return R.layout.view_load_more;
            }

            @Override
            protected int getLoadingViewId() {
                return R.id.normal;
            }

            @Override
            protected int getLoadFailViewId() {
                return R.id.normal;
            }

            @Override
            protected int getLoadEndViewId() {
                return R.id.normal;
            }
        });


        getProxyList();
        getProxyIncome();


    }

    private void getProxyIncome() {
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.proxyIncome, null, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {
                        ProxyIncomeBean bean=JSON.parseObject(response,ProxyIncomeBean.class);
                        mMAgentNum.setText(bean.data.sum_agents);
                        mMAgentSum.setText(bean.data.total_rent);

                    }
                });
    }

    private void getProxyList() {
        Map<String, String> map = new HashMap<>();
        map.put("per_page", "10");
        map.put("page", mPage + "");
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.proxyGet, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {
                        mBean = JSON.parseObject(response, ProxyListBean.class);
//ceshi
//                        ProxyListBean.DataBeanX.DataBean.EquipmentBean list=mBean.data.data.get(0).equipment.get(0);
//                        mBean.data.data.get(0).equipment.add(list);
//                        mBean.data.data.get(0).equipment.add(list);
                        mAdapter.addData(mBean.data.data);
                        mAdapter.loadMoreComplete();

                    }
                });

    }


    @Override
    public void onLoadMoreRequested() {
        mPage++;
        if (mBean.data.last_page.equals("1")) {
            mAdapter.loadMoreEnd();
            return;
        }
        getProxyList();


    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {


        switch (view.getId()) {

            case R.id.edit:

                showEditDialog(JSON.toJSONString((ProxyListBean.DataBeanX.DataBean)adapter.getData().get(position)));

                break;

        }


    }

    private void showEditDialog(String json) {

        ProxyListBean.DataBeanX.DataBean bean=JSON.parseObject(json,ProxyListBean.DataBeanX.DataBean.class);
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_edit_proxy, null);



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Dialog dialog = builder.create();
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = QMUIDisplayHelper.dp2px(getActivity(), 300);
        lp.height = QMUIDisplayHelper.dp2px(getActivity(), 400);
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setContentView(dialogView);

        View change=dialogView.findViewById(R.id.change);
        TextView tvChange=change.findViewById(R.id.tvChange);
        if (!bean.is_onchange.equals("1")){//不可修改
            change.setVisibility(View.GONE);
//            change.setEnabled(false);
//           tvChange.setTextColor(getResources().getColor(R.color.color666));
        }

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(ProxyChangeFragment.getInstance(json));
                dialog.dismiss();

            }
        });

    }



    @OnClick({R.id.bar_left, R.id.bar_right, R.id.bn_agent_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bar_left:
                popBackStack();
                break;
            case R.id.bar_right:
                break;
            case R.id.bn_agent_add:
                startFragment(new ProxyAddFragment() );
                break;
        }
    }

    @Override
    public void onClick(View v) {

    }
}
