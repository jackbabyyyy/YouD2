package com.ud.share.ui.home.page;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;

import com.ud.share.event.FreshBusinessEvent;

import com.ud.share.model.BusinessIncomeBean;
import com.ud.share.model.BusinessListBean;
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
public class BusinessFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.bar_left)
    ImageView mBarLeft;
    @BindView(R.id.content)
    EditText mSearch;
    @BindView(R.id.bar_right)
    TextView mBarRight;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    private static final String TAG = BusinessFragment.class.getSimpleName();

    private List<BusinessListBean.DataBeanX.DataBean> mData = new ArrayList<>();
    private BusinessAdapter mAdapter;
    private int mPage = 1;
    private BusinessListBean mBean;
    private TextView mAgentNum;
    private TextView mAgentSum;
    private MyTextWatcher mWatcher;

    private String mLng = "", mLat = "";

    public static BusinessFragment getInstance(String lng, String lat) {

        BusinessFragment fragment = new BusinessFragment();
        Bundle bundle = new Bundle();
        bundle.putString("lng", lng);
        bundle.putString("lat", lat);
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_business;
    }

    @Override
    protected void init() {

        mSearch.setHint("请输入商户名称或者手机号码");
        mLng = getArguments().getString("lng");
        mLat = getArguments().getString("lat");
        EventBus.getDefault().register(this);


        //
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new BusinessAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);

                if (parent.getChildAdapterPosition(view) != 0) {
                    outRect.bottom = QMUIDisplayHelper.dp2px(getActivity(), 24);
                }


            }
        });
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startFragment(BusinessDetailFragment.getInstance(mAdapter.getData().get(position).sid));
            }
        });

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_business_head, null);
        TextView mDividerTitle = view.findViewById(R.id.divider_title);
        mDividerTitle.setText("商户列表");
        mAgentNum = view.findViewById(R.id.agent_num);
        mAgentSum = view.findViewById(R.id.agent_sum);
        mAdapter.addHeaderView(view);

        getBusinessIncome();
        getBusinessList();
        mWatcher = new MyTextWatcher();

    }


    private void getBusinessList() {
        Map<String, String> map = new HashMap<>();
        map.put("per_page", "10");
        map.put("page", mPage + "");
        map.put("qstr", mSearch.getText().toString());
        map.put("longitude", mLng);
        map.put("latitude", mLat);
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.businessGet, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {

                        if (response.equals(JSON.toJSONString(mBean))) {
                            return;
                        }
                        mBean = JSON.parseObject(response, BusinessListBean.class);


                        mAdapter.addData(mBean.data.data);
                        mAdapter.loadMoreComplete();
                    }
                });

    }


    private void getBusinessIncome() {

        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.businessIncome, null, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {

                        BusinessIncomeBean bean = JSON.parseObject(response, BusinessIncomeBean.class);

                        mAgentNum.setText(bean.data.sum_sellers + "家");
                        mAgentSum.setText(bean.data.total_rent);


                    }
                });

    }


    @OnClick({R.id.bar_left, R.id.bar_right, R.id.bn_business_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bar_left:
                popBackStack();
                break;
            case R.id.bar_right:
                closeKeyBorad();
                mPage = 1;
                mAdapter.getData().clear();
                Log.d(TAG, "getBusinessList:2 ");

                getBusinessList();

                break;
            case R.id.bn_business_add:

                startFragment(new BusinessAddFragment());

                break;

        }
    }

    @Override
    public void onLoadMoreRequested() {
        mPage++;
        if (mBean.data.last_page.equals(mBean.data.current_page)) {
            mAdapter.loadMoreEnd();
            return;
        }


        getBusinessList();

    }


    @Override
    public void onResume() {
        super.onResume();


        mSearch.addTextChangedListener(mWatcher);

    }

    @Override
    public void onStop() {
        super.onStop();
        mSearch.removeTextChangedListener(mWatcher);
    }

    public class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (TextUtils.isEmpty(s.toString())) {
                closeKeyBorad();
                mPage = 1;
                mAdapter.getData().clear();
                getBusinessList();
            }
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(FreshBusinessEvent event) {
        //刷新
        mPage = 1;
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getBusinessList();
            }
        }, 180);


    }


}
