package com.ud.share.ui.home.page6;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by PP on 2019/3/12.
 */
public class CashFragment extends BaseFragment {
    @BindView(R.id.tabSegment)
    QMUITabSegment mTabSegment;
    @BindView(R.id.contentViewPager)
    ViewPager mContentViewPager;
    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.total)
    TextView mTotal;

    private List<BaseFragment> mBaseFragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cash;
    }

    @Override
    protected void init() {
        initMultiFragment();
        mTotal.setVisibility(View.GONE);
        mBar.setTitle("提现记录");
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
    }

    private void initMultiFragment() {
        mBaseFragments.add(CashItemFragment.getInstance(0));
        mBaseFragments.add(CashItemFragment.getInstance(1));
        mBaseFragments.add(CashItemFragment.getInstance(2));
        mBaseFragments.add(CashItemFragment.getInstance(3));

        FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
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
        mContentViewPager.setCurrentItem(0);
        mTabSegment.addTab(new QMUITabSegment.Tab("全部"));
        mTabSegment.addTab(new QMUITabSegment.Tab("提现中"));
        mTabSegment.addTab(new QMUITabSegment.Tab("提现成功"));
        mTabSegment.addTab(new QMUITabSegment.Tab("提现失败"));

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
