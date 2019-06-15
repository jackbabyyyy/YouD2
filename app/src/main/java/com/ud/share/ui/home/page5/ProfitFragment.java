package com.ud.share.ui.home.page5;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.custom.SegmentControlView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by PP on 2019/3/12.
 */
public class ProfitFragment extends BaseFragment {

    @BindView(R.id.segment)
    SegmentControlView mSegmentControlView;
    @BindView(R.id.contentViewPager)
    ViewPager mViewPager;
    @BindView(R.id.bar)
    QMUITopBarLayout mBar;

    private List<BaseFragment> mBaseFragments=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profit;
    }

    @Override
    protected void init() {
        mBar.setTitle("收益明细");
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        mSegmentControlView.setOnSegmentChangedListener(new SegmentControlView.OnSegmentChangedListener() {
            @Override
            public void onSegmentChanged(int newSelectedIndex) {
                if(mViewPager != null){
                    //change the second argument to true if you want the gradient effect when viewpager is changing
                    mViewPager.setCurrentItem(newSelectedIndex, false);//viewpager changing without animation
                }
            }
        });
        //set viewpager to change segment according to the state of viewpager
        mSegmentControlView.setViewPager(mViewPager);
        //set the selected index of segments initiatively
        mSegmentControlView.setSelectedIndex(0);
        //set gradient effect if you want
        mSegmentControlView.setGradient(true);

       mBaseFragments.add(new ProfitItemFragment());
       mBaseFragments.add(new ProfitItemFragment());

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

        mViewPager.setAdapter(mFragmentPagerAdapter);
        mViewPager.setCurrentItem(0);




    }


}
