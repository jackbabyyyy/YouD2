package com.yd.youd.ui.install;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;
import com.yd.youd.ui.home.page3.DeviceItemFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by PP on 2019/4/4.
 */
public class ChooseDeviceFragment extends BaseFragment {
    @BindView(R.id.bar_left)
    ImageView mBarLeft;
    @BindView(R.id.content)
    EditText mContent;
    @BindView(R.id.bar_right)
    TextView mBarRight;
    @BindView(R.id.tabSegment)
    QMUITabSegment mTabSegment;
    @BindView(R.id.contentViewPager)
    ViewPager mContentViewPager;

    private int mItemCount ;
    private List<BaseFragment> mBaseFragments=new ArrayList<>();






    @Override
    protected int getLayoutId() {
        return R.layout.fragment_choose_device;
    }

    @Override
    protected void init() {

        mBaseFragments.add(new ChooseDeviceItemFragment());
        mBaseFragments.add(new ChooseDeviceItemFragment());
        mBaseFragments.add(new ChooseDeviceItemFragment());
        mBaseFragments.add(new ChooseDeviceItemFragment());

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
        mContentViewPager.setCurrentItem(0);
        mTabSegment.addTab(new QMUITabSegment.Tab("M800"));
        mTabSegment.addTab(new QMUITabSegment.Tab("MC800"));
        mTabSegment.addTab(new QMUITabSegment.Tab("S500"));
        mTabSegment.addTab(new QMUITabSegment.Tab("L1000"));

        int space = QMUIDisplayHelper.dp2px(getContext(), 16);
        mTabSegment.setHasIndicator(true);
        mTabSegment.setMode(QMUITabSegment.MODE_FIXED);
        mTabSegment.setItemSpaceInScrollMode(space);
        mTabSegment.setupWithViewPager(mContentViewPager, false);
        mTabSegment.setPadding(space, 0, space, 0);
        mTabSegment.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                Toast.makeText(getContext(), "select index " + index, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(int index) {
                Toast.makeText(getContext(), "unSelect index " + index, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselected(int index) {
                Toast.makeText(getContext(), "reSelect index " + index, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDoubleTap(int index) {
                Toast.makeText(getContext(), "double tap index " + index, Toast.LENGTH_SHORT).show();
            }
        });





    }



}
