package com.yd.youd.ui.home.page;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by PP on 2019/3/26.
 */
public class BusinessTariffFragment extends BaseFragment {




    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.bar)
    QMUITopBarLayout mBar;

    private List<TariffItem > mTariffItems=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_business_tariff;
    }

    @Override
    protected void init() {
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        mBar.setTitle("修改资费标准");
        mBar.addRightTextButton("提交",R.id.topbar_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //
        mTariffItems.add(new TariffItem("3小时","3"));
        mTariffItems.add(new TariffItem("5小时","7"));
        mTariffItems.add(new TariffItem("12小时","12"));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        BusinessTariffAdapter adapter=new BusinessTariffAdapter(mTariffItems);
        mRecyclerView.setAdapter(adapter);




    }
}
