package com.ud.share.ui.home.page2;

import android.app.Dialog;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.event.FreshProxyEvent;
import com.ud.share.model.ProxyIncomeBean;
import com.ud.share.model.ProxyListBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
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
    private MyTextWatcher mWatcher;

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

        EventBus.getDefault().register(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ProxyAdapter(mDatas);
        mAdapter.addHeaderView(head);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom=QMUIDisplayHelper.dp2px(getActivity(),10);
            }
        });
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


        getProxyIncome();
        getProxyList();
        mWatcher = new MyTextWatcher();
    }


    public class MyTextWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (TextUtils.isEmpty(s.toString())){
                closeKeyBorad();
                mPage=1;
                mAdapter.getData().clear();
                getProxyList();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        //
        mSearch.addTextChangedListener(mWatcher);

    }

    @Override
    public void onStop() {
        super.onStop();
        mSearch.removeTextChangedListener(mWatcher);
    }

    private void getProxyIncome() {
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.proxyIncome, null, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {
                        ProxyIncomeBean bean = JSON.parseObject(response, ProxyIncomeBean.class);
                        mMAgentNum.setText("代理商总数："+bean.data.sum_agents+"家");
                        mMAgentSum.setText(bean.data.total_rent);

                    }
                });
    }

    private void getProxyList() {
        Map<String, String> map = new HashMap<>();
        map.put("per_page", "10");
        map.put("page", mPage + "");
        map.put("qstr",mSearch.getText().toString());
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.proxyGet, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {
                        mBean = JSON.parseObject(response, ProxyListBean.class);

                        mAdapter.addData(mBean.data.data);
                        mAdapter.loadMoreComplete();

                    }
                });

    }


    @Override
    public void onLoadMoreRequested() {
        mPage++;
        if (mBean.data.last_page.equals(mBean.data.current_page)) {
            mAdapter.loadMoreEnd();
            return;
        }
        getProxyList();


    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {


        switch (view.getId()) {

            case R.id.edit:

                //TODO 编辑代理
                // showEditDialog(JSON.toJSONString((ProxyListBean.DataBeanX.DataBean)adapter.getData().get(position)));

                startFragment(ProxyChangeFragment.getInstance(JSON.toJSONString((ProxyListBean.DataBeanX.DataBean) adapter.getData().get(position))));
                break;

        }


    }

    private void showEditDialog(String json) {

        ProxyListBean.DataBeanX.DataBean bean = JSON.parseObject(json, ProxyListBean.DataBeanX.DataBean.class);
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

        View change = dialogView.findViewById(R.id.change);
        TextView tvChange = change.findViewById(R.id.tvChange);
        if (!bean.is_onchange.equals("1")) {//不可修改
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
                closeKeyBorad();
                mPage=1;
                mAdapter.getData().clear();
                getProxyList();
                break;
            case R.id.bn_agent_add:
                startFragment(new ProxyAddFragment2());
                break;
        }
    }

    @Override
    public void onClick(View v) {

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(FreshProxyEvent event) {
        //刷新
        mPage = 1;
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        getProxyList();

    }



}
