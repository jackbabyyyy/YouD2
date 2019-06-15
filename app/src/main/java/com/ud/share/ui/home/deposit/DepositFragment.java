package com.ud.share.ui.home.deposit;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.model.DepositBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Created by PP on 2019/4/3.
 */
public class DepositFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;


    private List<DepositBean.DataBean> mDepositItems=new ArrayList<>();
    private DepositAdapter mAdapter;

    private int mPage=1;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_deposit;
    }

    @Override
    protected void init() {
        mBar.setTitle("押金列表");
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });


        getData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new DepositAdapter(mDepositItems);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildAdapterPosition(view)==0){
                    outRect.top= QMUIDisplayHelper.dp2px(getActivity(),20);
                }
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //todo 押金明细
                //startFragment(new DepositDetailFragment());
            }
        });
        mAdapter.setOnLoadMoreListener(this,mRecyclerView);



        getData();

    }

    private void getData() {

        HashMap<String ,String> map=new HashMap<>();
        map.put("page_num",mPage+"");
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.depositGet, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) throws IOException {
                DepositBean depositBean=JSON.parseObject(response,DepositBean.class);
                if (depositBean.data.size()!=0){
                    mAdapter.addData(depositBean.data);
                    mAdapter.loadMoreComplete();
                }else{
                    mAdapter.loadMoreEnd();
                }



            }
        });

    }

    @Override
    public void onLoadMoreRequested() {
        mPage++;
        getData();



    }


}
