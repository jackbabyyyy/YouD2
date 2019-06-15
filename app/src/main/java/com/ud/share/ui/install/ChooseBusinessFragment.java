package com.ud.share.ui.install;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.model.BaseJson;
import com.ud.share.model.BusinessListBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.utils.AppData;
import com.ud.share.utils.StringUtils;

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
    private BusinessListBean mBean;

    private List<BusinessListBean.DataBeanX.DataBean> mChooseBusinessItems = new ArrayList<>();
    private ChooseBusinessAdapter mAdapter;


    public static final int FROM_INSTALL_ONE = 0;
    public static final int FROM_INSTALL_HAND_SELECT = 1;
    private String mSns;
    private String mModel;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_choose_business;
    }


    public static ChooseBusinessFragment getInstance(String sns, String model) {
        Bundle bundle = new Bundle();
        bundle.putString("model", model);
        bundle.putString("sns", sns);
        ChooseBusinessFragment fragment = new ChooseBusinessFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void init() {

        mModel = getArguments().getString("model");
        mSns = getArguments().getString("sns");

        mTopBarLayout.setTitle("选择商户");
        mTopBarLayout.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });


        mAdapter = new ChooseBusinessAdapter(mChooseBusinessItems);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = QMUIDisplayHelper.dp2px(getActivity(), 15);
            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                String sid = mAdapter.getData().get(position).sid;
                if (TextUtils.isEmpty(mModel)) {
                    String snscode = StringUtils.getFileName(mSns);
                    if (snscode.contains("X") && snscode.contains("A")) {
                        getInstall(sid, AppData.MODEL_LINE, snscode);
                    }

                } else {

                    getInstall(sid, mModel, mSns);


                }


            }
        });

        getBusinessList();


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

    @Override
    public void onLoadMoreRequested() {
        mPage++;
        if (mBean.data.last_page.equals(mBean.data.current_page)) {
            mAdapter.loadMoreEnd();
            return;
        }
        getBusinessList();

    }

    private void getInstall(String sid, String model, String sns) {
        HashMap<String, String> map = new HashMap<>();
        map.put("sid", sid);
        map.put("model", model);


        map.put("sns", sns);


        HttpUtil.getInstance(getActivity()).postForm(AppUrl.bind, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String s) throws IOException {
                BaseJson json = JSON.parseObject(s, BaseJson.class);
                if (json.code == 1) {
                    popBackStack();
                }
                showToast(json.msg);

            }
        });
    }
}
