package com.ud.share.ui.statistics;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.model.SearchOneBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * Created by PP on 2019-07-04.
 */
public class SearchOneFragment extends BaseFragment {


    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.content)
    EditText mEtSearch;
    @BindView(R.id.bar_right)
    View mBarRight;


    private List<SearchOneBean.DataBean> mDataBeans=new ArrayList<>();
    private SearchOneAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_one;
    }

    @Override
    protected void init() {
        mBarRight.setVisibility(View.INVISIBLE);
        mEtSearch.setHint("请输入商家或代理商");
        mAdapter = new SearchOneAdapter(mDataBeans);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent();
                SearchOneBean.DataBean bean=mAdapter.getData().get(position);
                String s=bean.user_type.equals("1")?"[代理商]  ":"[商户]    ";
                intent.putExtra("keyword",s+bean.name);
                intent.putExtra("id",bean.id);
                intent.putExtra("type",bean.user_type);
                setFragmentResult(1,intent);
                popBackStack();

            }
        });

        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())){
                    getData();
                }

            }
        });


    }

    @OnClick({R.id.bar_right,R.id.bar_left})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bar_left:

              popBackStack();
                break;

            case R.id.bar_right:
                getData();

                break;
        }


    }


    private void getData(){
        String key=mEtSearch.getText().toString();


        HttpUtil.getInstance(getActivity()).getAsynHttp(AppUrl.searchOne+"?keyword="+key, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }


            @Override
            public void onResponse(String s) throws IOException {
                SearchOneBean bean= JSON.parseObject(s,SearchOneBean.class);
                mAdapter.setNewData(bean.data);


            }
        });
    }
}
