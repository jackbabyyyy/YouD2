package com.ud.share.ui.install;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.comm.InstallObjectEnum;
import com.ud.share.model.BusinessListBean;
import com.ud.share.model.ProxyListBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.ui.home.page.BusinessAddFragment;
import com.ud.share.ui.home.page2.ProxyAddFragment2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Created by PP on 2019/4/4.
 */
public class ChooseBusinessFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.bar)
    QMUITopBarLayout mTopBarLayout;
    @BindView(R.id.search)
    EditText mSearch;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    private int mPage = 1;
    private BusinessListBean mBusinessListBean;
    private ProxyListBean mProxyListBean;

    private List<BusinessListBean.DataBeanX.DataBean> mChooseBusinessItems;
    private List<ProxyListBean.DataBeanX.DataBean> mChooseProxyItems;
    private ChooseBusinessAdapter mBusinessAdapter;
    private ChooseProxyAdapetr mProxyAdapetr;
    private MyTextWatcher mWatcher;
    private String mTitle;
    private String mRightBtnText;
    private int mType;
    private String mDateSourceUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_choose_business;
    }


    @SuppressLint("ValidFragment")
    private ChooseBusinessFragment() {

    }

    public static ChooseBusinessFragment getInstance(int type) {
        ChooseBusinessFragment fragment = new ChooseBusinessFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;


    }

    @Override
    protected void init() {

        mType = getArguments().getInt("type");
        if (isBusiness()) {
            mTitle = "选择商户";
            mRightBtnText = "新增商户";
            mSearch.setHint("输入筛选商户");
            mDateSourceUrl=AppUrl.businessGet;
            mChooseBusinessItems=new ArrayList<>();
            mBusinessAdapter = new ChooseBusinessAdapter(mChooseBusinessItems);
            mBusinessAdapter.setOnLoadMoreListener(this, mRecyclerView);

            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.setAdapter(mBusinessAdapter);
            mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);
                    outRect.bottom = QMUIDisplayHelper.dp2px(getActivity(), 15);
                }
            });

            mBusinessAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


                    String pid = mBusinessAdapter.getData().get(position).sid;
                    String name=mBusinessAdapter.getData().get(position).shop_name;
                    startFragment(InstallFragment.getInstance(pid,name,InstallObjectEnum.BUSINESS.getId()));


                }
            });

        } else {
            mTitle = "选择代理商";
            mRightBtnText = "新增代理商";
            mSearch.setHint("输入筛选代理商");
            mDateSourceUrl=AppUrl.proxyGet;
            mTopBarLayout.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popBackStack();
                }
            });
            mChooseProxyItems=new ArrayList<>();
            mProxyAdapetr = new ChooseProxyAdapetr(mChooseProxyItems);
            mProxyAdapetr.setOnLoadMoreListener(this, mRecyclerView);

            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.setAdapter(mProxyAdapetr);
            mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);
                    outRect.bottom = QMUIDisplayHelper.dp2px(getActivity(), 15);
                }
            });

            mProxyAdapetr.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    String pid = mProxyAdapetr.getData().get(position).agent_id;
                    String name=mProxyAdapetr.getData().get(position).agent_name;
                    startFragment(InstallFragment.getInstance(pid,name,InstallObjectEnum.LOWERPROXY.getId()));


                }
            });

        }


        mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //关闭软键盘
                    closeKeyBorad();
                    mPage = 1;
                    if (isBusiness()) mBusinessAdapter.getData().clear();else mProxyAdapetr.getData().clear();

                    getBusinessList();

                    return true;
                }
                return false;
            }
        });
        mWatcher = new MyTextWatcher();
        mSearch.addTextChangedListener(mWatcher);

        mTopBarLayout.setTitle(mTitle);
        mTopBarLayout.addRightTextButton(mRightBtnText, R.id.bar_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBusiness())
                    startFragment(new BusinessAddFragment());
                else
                    startFragment(new ProxyAddFragment2());
            }
        });


    }


    @Override
    public void onStop() {
        super.onStop();
        mSearch.removeTextChangedListener(mWatcher);
    }

    private void getBusinessList() {
        Map<String, String> map = new HashMap<>();
        map.put("per_page", "10");
        map.put("page", mPage + "");
        if (!TextUtils.isEmpty(mSearch.getText().toString().trim())) {
            map.put("qstr", mSearch.getText().toString().trim());
        }


        HttpUtil.getInstance(getActivity())
                .postForm(mDateSourceUrl, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {
                        if (isBusiness()){
                            mBusinessListBean = JSON.parseObject(response, BusinessListBean.class);
                            mBusinessAdapter.addData(mBusinessListBean.data.data);
                            mBusinessAdapter.loadMoreComplete();
                        }else{
                            mProxyListBean=JSON.parseObject(response,ProxyListBean.class);
                            mProxyAdapetr.addData(mProxyListBean.data.data);
                            mProxyAdapetr.loadMoreComplete();
                        }

                    }
                });

    }

    @Override
    public void onLoadMoreRequested() {
        mPage++;
        if (isBusiness()){
            if (mBusinessListBean.data.last_page.equals(mBusinessListBean.data.current_page)) {
                mBusinessAdapter.loadMoreEnd();
                return;
            }
            getBusinessList();

        }else{
            if (mProxyListBean.data.last_page.equals(mProxyListBean.data.current_page)) {
                mProxyAdapetr.loadMoreEnd();
                return;
            }
            getBusinessList();
        }



    }

    @Override
    public void onResume() {
        super.onResume();
        mPage = 1;
        if (isBusiness()){
            mBusinessAdapter.getData().clear();
            mBusinessAdapter.notifyDataSetChanged();
        }else{
            mProxyAdapetr.getData().clear();
            mProxyAdapetr.notifyDataSetChanged();
        }

        getBusinessList();
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
                if (mType==InstallObjectEnum.BUSINESS.getId()) mBusinessAdapter.getData().clear();else mProxyAdapetr.getData().clear();
                getBusinessList();
            }
        }
    }

    private boolean isBusiness(){
        return mType==InstallObjectEnum.BUSINESS.getId();
    }


}
