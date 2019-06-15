package com.ud.share.ui.home.page5;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.model.ProfitListBean;
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
 * Created by PP on 2019/4/2.
 */
public class ProfitDetailFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener {
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

    private int mPage=1;



    private List<ProfitListBean.DataBeanX.DataBean> mProfitDetailItems=new ArrayList<>();
    private ProfitDetailAdapter mAdapter;
    private ProfitListBean mBean;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profit_detail;
    }

    @Override
    protected void init() {
        mContent.setHint("请输入订单编号");
        mAdapter = new ProfitDetailAdapter(mProfitDetailItems);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter.setOnLoadMoreListener(this,mRecycler);
        mRecycler.setAdapter(mAdapter);


        getData();

        mContent.addTextChangedListener(new TextWatcher() {
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
                    getData();
                }

            }
        });

    }

    private void getData() {
        HashMap<String,String> map=new HashMap<>();
        map.put("qstr",mContent.getText().toString());
        map.put("per_page","20");
        map.put("page",mPage+"");
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.profitList, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String s) throws IOException {

                        mBean = JSON.parseObject(s, ProfitListBean.class);
                        mAdapter.addData(mBean.data.data);
                        mAdapter.loadMoreComplete();

                    }
                });

    }


    @OnClick({R.id.bar_left, R.id.bar_right, R.id.name, R.id.type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bar_left:
                popBackStack();
                break;
            case R.id.bar_right:
                closeKeyBorad();
                mPage=1;
                mAdapter.getData().clear();
                getData();

                break;
            case R.id.name:
                break;
            case R.id.type:
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
       getData();



    }
}
