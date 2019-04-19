package com.yd.youd.ui.home.page;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;
import com.yd.youd.base.BaseItemAdapter;
import com.yd.youd.model.ItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BusinessChargeFragment extends BaseFragment {


    @BindView(R.id.bar)
    QMUITopBarLayout mBar;



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_business_charge;
    }

    @Override
    protected void init() {
        mBar.setTitle("修改计费方式");
        mBar.addRightTextButton("提交",R.id.topbar_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });








    }



}
