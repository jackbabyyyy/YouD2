package com.yd.youd.ui.home.page3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;
import com.yd.youd.model.DeviceTypeBean;
import com.yd.youd.net.AppUrl;
import com.yd.youd.net.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * Created by PP on 2019/3/12.
 */
public class DeviceFragment extends BaseFragment {


    @BindView(R.id.bar_left)
    ImageView mBarLeft;
    @BindView(R.id.content)
    EditText mSearch;
    @BindView(R.id.bar_right)
    TextView mBarRight;
    @BindView(R.id.tabSegment)
    QMUITabSegment mTabSegment;
    @BindView(R.id.contentViewPager)
    ViewPager mContentViewPager;

    private int mItemCount ;
    private List<BaseFragment> mBaseFragments=new ArrayList<>();
    private FragmentPagerAdapter mPagerAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_device;
    }

    @Override
    protected void init() {

        getDeviceType();
        mSearch.setHint("请输入您想要搜索的设备SN码");


        mPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mBaseFragments.get(i);
            }

            @Override
            public int getCount() {
                return mBaseFragments.size();
            }
        };



        mContentViewPager.setAdapter(mPagerAdapter);
        mContentViewPager.setCurrentItem(0);
        mTabSegment.addTab(new QMUITabSegment.Tab(""));
        mTabSegment.addTab(new QMUITabSegment.Tab(""));
        mTabSegment.addTab(new QMUITabSegment.Tab(""));
        mTabSegment.addTab(new QMUITabSegment.Tab(""));

        int space = QMUIDisplayHelper.dp2px(getContext(), 16);
        mTabSegment.setHasIndicator(true);
        mTabSegment.setMode(QMUITabSegment.MODE_FIXED);
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

    private void getDeviceType(){
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.deviceType, null, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) throws IOException {
                DeviceTypeBean bean= JSON.parseObject(response,DeviceTypeBean.class);
                mTabSegment.reset();
                mTabSegment.addTab(new QMUITabSegment.Tab(bean.data.t1));
                mTabSegment.addTab(new QMUITabSegment.Tab(bean.data.t2));
                mTabSegment.addTab(new QMUITabSegment.Tab(bean.data.t3));
                mTabSegment.addTab(new QMUITabSegment.Tab(bean.data.t4));
                mTabSegment.notifyDataChanged();



                mBaseFragments.add( DeviceItemFragment.getInstance(bean.data.t1));
                mBaseFragments.add( DeviceItemFragment.getInstance(bean.data.t2));
                mBaseFragments.add( DeviceItemFragment.getInstance(bean.data.t3));
                mBaseFragments.add( DeviceItemFragment.getInstance(bean.data.t4));
                mPagerAdapter.notifyDataSetChanged();



            }
        });
    }




}
