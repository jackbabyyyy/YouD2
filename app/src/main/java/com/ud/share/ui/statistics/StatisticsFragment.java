package com.ud.share.ui.statistics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by PP on 2019/5/28.
 */
public class StatisticsFragment extends BaseFragment {


    @BindView(R.id.tabSegment)
    QMUITabSegment mTabSegment;
    @BindView(R.id.contentViewPager)
    ViewPager mContentViewPager;


    private List<BaseFragment> mBaseFragments = new ArrayList<>();
    private FragmentPagerAdapter mPagerAdapter;




    @Override
    protected int getLayoutId() {
        return R.layout.fragment_statistics;
    }


    public static StatisticsFragment getInstance(){
        Bundle bundle=new Bundle();
        StatisticsFragment fragment=new StatisticsFragment();
        fragment.setArguments(bundle);
        return  fragment;
    }


    @Override
    protected void init() {



        mBaseFragments.add(new StaItemFragment());
        mBaseFragments.add(new StaItem2Fragment());


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
        mTabSegment.addTab(new QMUITabSegment.Tab("客户统计"));
        mTabSegment.addTab(new QMUITabSegment.Tab("经营统计"));


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






}
