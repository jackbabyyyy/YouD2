package com.ud.share.ui.my;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.model.InfoBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.utils.AppData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Created by PP on 2019/3/8.
 */
public class InfoFragment extends BaseFragment {


    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.bar)
    QMUITopBarLayout mBar;

    private InfoAdapter mAdapter;
    private String mNextString;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info;
    }

    public static InfoFragment getInstance(String jsonString) {

        InfoFragment fragment = new InfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("json", jsonString);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void init() {

        String lastPageString = getArguments().getString("json");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mAdapter = new InfoAdapter(initInfoData(lastPageString));
        mRecyclerView.setAdapter(mAdapter);


        mBar.setTitle("个人信息");

        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
        mBar.addRightTextButton("修改", R.id.topbar_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(InfoChangeFragment.getInstance(mNextString));

            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();

        getInfo();
    }

    private List<AppData.InfoBean> initInfoData(String lastPageString) {

        InfoBean infoBean = JSON.parseObject(lastPageString, InfoBean.class);
        List<String> contents = new ArrayList<>();
        contents.add(infoBean.data.realname);
        contents.add(infoBean.data.phone);
        contents.add(infoBean.data.card_sn);
        contents.add(infoBean.data.credit_num);
        contents.add(infoBean.data.bank_name);
        contents.add(infoBean.data.belong_bank);
        List<AppData.InfoBean> infoBeanList = new ArrayList<>();
        for (int i = 0; i < AppData.getInfo().size(); i++) {
            AppData.InfoBean bean = new AppData.InfoBean();
            bean.title = AppData.getInfo().get(i);
            bean.name = contents.get(i);
            infoBeanList.add(bean);

        }

        return infoBeanList;

    }

    private void getInfo() {
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.infoGet, null, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }
                    @Override
                    public void onResponse(String response) throws IOException {
                        mNextString = response;
                        mAdapter.setNewData(initInfoData(response));
                    }
                });
    }


}
