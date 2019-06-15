package com.ud.share.ui.home.page4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.model.OrderBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Created by PP on 2019/3/12.
 */
public class OrderFragment extends BaseFragment {
    @BindView(R.id.tabSegment)
    QMUITabSegment mTabSegment;
    @BindView(R.id.contentViewPager)
    ViewPager mContentViewPager;
    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.total)
    TextView mTvTotal;

    private List<BaseFragment> mBaseFragments=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cash;
    }

    @Override
    protected void init() {

        initMultiFragment();

        mBar.setTitle("订单列表");
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        getTab();


    }


    public static OrderFragment getInstance(int index){
        Bundle bundle=new Bundle();
        bundle.putInt("index",index);
        OrderFragment fragment=new OrderFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    private void initMultiFragment(){
        int index=getArguments().getInt("index");
        mBaseFragments.add(OrderItemFragment.getInstance(-1));
        mBaseFragments.add(OrderItemFragment.getInstance(1));
        mBaseFragments.add(OrderItemFragment.getInstance(2));
        mBaseFragments.add(OrderItemFragment.getInstance(3));
        mBaseFragments.add(OrderItemFragment.getInstance(4));

        FragmentPagerAdapter mFragmentPagerAdapter=new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mBaseFragments.get(i);
            }

            @Override
            public int getCount() {
                return mBaseFragments.size();
            }
        };

        mContentViewPager.setAdapter(mFragmentPagerAdapter);
        mContentViewPager.setCurrentItem(index);
        mTabSegment.setMode(QMUITabSegment.MODE_SCROLLABLE);

        mTabSegment.addTab(new QMUITabSegment.Tab(""));
        mTabSegment.addTab(new QMUITabSegment.Tab(""));
        mTabSegment.addTab(new QMUITabSegment.Tab(""));
        mTabSegment.addTab(new QMUITabSegment.Tab(""));
        mTabSegment.addTab(new QMUITabSegment.Tab(""));




        int space = QMUIDisplayHelper.dp2px(getContext(), 16);
        mTabSegment.setHasIndicator(true);
        mTabSegment.setItemSpaceInScrollMode(space);
        mTabSegment.setupWithViewPager(mContentViewPager, false);
        mTabSegment.setPadding(space, 0, space, 0);
        mTabSegment.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {

            }

            @Override
            public void onTabUnselected(int index) {

            }

            @Override
            public void onTabReselected(int index) {

            }

            @Override
            public void onDoubleTap(int index) {

            }
        });

    }

    private void getTab() {

        HashMap<String, String> map = new HashMap<>();
        map.put("order_status","-1");

        map.put("page","1");
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.orderGet, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) throws IOException {
                OrderBean bean = JSON.parseObject(response, OrderBean.class);


                if (bean.data!=null&&bean.data.order_num_1.size()!=0&&bean.data.order_num_1.size()>4){
                    mTabSegment.reset();
                mTabSegment.addTab(new QMUITabSegment.Tab("全部("+bean.data.order_num_1.get(0)+")"));
                mTabSegment.addTab(new QMUITabSegment.Tab("租借中("+bean.data.order_num_1.get(1)+")"));
                mTabSegment.addTab(new QMUITabSegment.Tab("已完成("+bean.data.order_num_1.get(2)+")"));
                mTabSegment.addTab(new QMUITabSegment.Tab("超时("+bean.data.order_num_1.get(3)+")"));
                mTabSegment.addTab(new QMUITabSegment.Tab("异常("+bean.data.order_num_1.get(4)+")"));
                mTabSegment.notifyDataChanged();
                }



            }
        });
    }
}
