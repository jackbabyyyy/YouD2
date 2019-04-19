package com.yd.youd.ui.install;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by PP on 2019/4/4.
 */
public class ChooseBusinessFragment extends BaseFragment {

    @BindView(R.id.bar)
    QMUITopBarLayout mTopBarLayout;
    @BindView(R.id.search)
    EditText mSearch;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;


    private List<ChooseBusinessItem> mChooseBusinessItems=new ArrayList<>();
    private ChooseBusinessAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_choose_business;
    }

    @Override
    protected void init() {
        mTopBarLayout.setTitle("选择商户");
        mTopBarLayout.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });


        mAdapter = new ChooseBusinessAdapter(mChooseBusinessItems);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });





    }
}
