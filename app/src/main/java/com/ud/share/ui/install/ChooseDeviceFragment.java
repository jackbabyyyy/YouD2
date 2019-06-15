package com.ud.share.ui.install;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.model.DeviceTypeBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Created by PP on 2019/4/4.
 */
public class ChooseDeviceFragment extends BaseFragment {
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

    private int mItemCount;
    private List<BaseFragment> mBaseFragments = new ArrayList<>();
    private FragmentPagerAdapter mPagerAdapter;
    private DeviceTypeBean mBean;



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_choose_device;
    }


    public static ChooseDeviceFragment getInstance(){
        Bundle bundle=new Bundle();
        ChooseDeviceFragment fragment=new ChooseDeviceFragment();
        fragment.setArguments(bundle);
        return  fragment;
    }


    @Override
    protected void init() {


        getDeviceType();
        mSearch.setHint("请输入您想要搜索的设备SN码");

        mBarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });


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
//        mTabSegment.addTab(new QMUITabSegment.Tab(""));
//        mTabSegment.addTab(new QMUITabSegment.Tab(""));
//        mTabSegment.addTab(new QMUITabSegment.Tab(""));
        mTabSegment.addTab(new QMUITabSegment.Tab(""));

        int space = QMUIDisplayHelper.dp2px(getContext(), 16);
        mTabSegment.setHasIndicator(true);
        mTabSegment.setMode(QMUITabSegment.MODE_SCROLLABLE);
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


    private void getDeviceType() {
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.deviceType, null, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) throws IOException {
                mBean = JSON.parseObject(response, DeviceTypeBean.class);
                mTabSegment.reset();
//                mTabSegment.addTab(new QMUITabSegment.Tab(mBean.data.t1));
//                mTabSegment.addTab(new QMUITabSegment.Tab(mBean.data.t2));
                mTabSegment.addTab(new QMUITabSegment.Tab(mBean.data.t3));
       //         mTabSegment.addTab(new QMUITabSegment.Tab(mBean.data.t4));
                mTabSegment.notifyDataChanged();


//                mBaseFragments.add(ChooseDeviceItemFragment.getInstance(mBean.data.t1));
//                mBaseFragments.add(ChooseDeviceItemFragment.getInstance(mBean.data.t2));
                mBaseFragments.add(ChooseDeviceItemFragment2.getInstance(mBean.data.t3));
         //       mBaseFragments.add(ChooseDeviceItemFragment.getInstance(mBean.data.t4));
                mPagerAdapter.notifyDataSetChanged();


            }
        });
    }





}
