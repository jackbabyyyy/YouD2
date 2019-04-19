package com.yd.youd.ui;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

import com.qmuiteam.qmui.widget.QMUIPagerAdapter;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUIViewPager;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;
import com.yd.youd.ui.extend.ExtendFragment;
import com.yd.youd.ui.home.HomeFragment;
import com.yd.youd.ui.install.InstallFragment;
import com.yd.youd.ui.my.MyFragment;

import butterknife.BindView;

/**
 * Created by PP on 2019/3/5.
 */
public class MainFragment extends BaseFragment {

    @BindView(R.id.pager)
    QMUIViewPager mViewPager;
    @BindView(R.id.tabs)
    QMUITabSegment mTabSegment;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void init() {

        initTabs();
        initPagers();
    }

    private void initTabs() {

        mTabSegment.setDefaultNormalColor(getResources().getColor(R.color.color999));
        mTabSegment.setDefaultSelectedColor(getResources().getColor(R.color.theme));
//        mTabSegment.setDefaultTabIconPosition(QMUITabSegment.ICON_POSITION_BOTTOM);


        QMUITabSegment.Tab tab = new QMUITabSegment.Tab(getActivity().getDrawable(R.mipmap.tabbar_home),
                getActivity().getDrawable(R.mipmap.tabbar_home_selected), "首页", false);
        QMUITabSegment.Tab tab2 = new QMUITabSegment.Tab(getActivity().getDrawable(R.mipmap.tabbar_mall),
                getActivity().getDrawable(R.mipmap.tabbar_mall_selected), "装机", false);
        QMUITabSegment.Tab tab3 = new QMUITabSegment.Tab(getActivity().getDrawable(R.mipmap.tabbar_extend),
                getActivity().getDrawable(R.mipmap.tabbar_extend_selected), "展业", false);
        QMUITabSegment.Tab tab4 = new QMUITabSegment.Tab(getActivity().getDrawable(R.mipmap.tabbar_mine),
                getActivity().getDrawable(R.mipmap.tabbar_mine_selected), "我的", false);


        // 如果你的 icon 显示大小和实际大小不吻合:
        // 通过 tab.setTabIconBounds 重新设置 bounds
//        int iconShowSize = QMUIDisplayHelper.dp2px(getContext(), 20);
//        component.setTabIconBounds(0, 0, iconShowSize, iconShowSize);
        mTabSegment.addTab(tab)
                .addTab(tab2)
                .addTab(tab3)
                .addTab(tab4);

    }

    private void initPagers() {
        mViewPager.setSwipeable(false);
        QMUIPagerAdapter adapter = new QMUIPagerAdapter() {
            private FragmentTransaction mCurrentTransaction;
            private Fragment mCurrentPrimaryItem = null;

            @Override
            protected Object hydrate(ViewGroup container, int position) {
                switch (position) {
                    case 0:
                        return new HomeFragment();
                    case 1:
                        return new InstallFragment();
                    case 2:
                        return new ExtendFragment();
                    case 3:
                        return new MyFragment();
                    default:
                        return new HomeFragment();

                }
            }

            @Override
            public void startUpdate(ViewGroup container) {
                if (container.getId() == View.NO_ID) {
                    throw new IllegalStateException("ViewPager with adapter " + this
                            + " requires a view id");
                }
            }

            @Override
            public void finishUpdate(ViewGroup container) {
                if (mCurrentTransaction != null) {
                    mCurrentTransaction.commitNowAllowingStateLoss();
                    mCurrentTransaction = null;
                }
            }

            @Override
            protected void populate(ViewGroup container, Object item, int position) {
                String name = makeFragmentName(container.getId(), position);
                if (mCurrentTransaction == null) {
                    mCurrentTransaction = getChildFragmentManager()
                            .beginTransaction();
                }
                Fragment fragment = getChildFragmentManager().findFragmentByTag(name);
                if (fragment != null) {
                    mCurrentTransaction.attach(fragment);
                } else {
                    fragment = (Fragment) item;
                    mCurrentTransaction.add(container.getId(), fragment, name);
                }
                if (fragment != mCurrentPrimaryItem) {
                    fragment.setMenuVisibility(false);
                    fragment.setUserVisibleHint(false);
                }
            }

            @Override
            protected void destroy(ViewGroup container, int position, Object object) {
                if (mCurrentTransaction == null) {
                    mCurrentTransaction = getChildFragmentManager()
                            .beginTransaction();
                }
                mCurrentTransaction.detach((Fragment) object);
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == ((Fragment) o).getView();
            }

            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                Fragment fragment = (Fragment) object;
                if (fragment != mCurrentPrimaryItem) {
                    if (mCurrentPrimaryItem != null) {
                        mCurrentPrimaryItem.setMenuVisibility(false);
                        mCurrentPrimaryItem.setUserVisibleHint(false);
                    }
                    if (fragment != null) {
                        fragment.setMenuVisibility(true);
                        fragment.setUserVisibleHint(true);
                    }
                    mCurrentPrimaryItem = fragment;
                }
            }

            private String makeFragmentName(int viewId, long id) {
                return "QDFitSystemWindowViewPagerFragment:" + viewId + ":" + id;
            }
        };


        mViewPager.setAdapter(adapter);

        mTabSegment.setupWithViewPager(mViewPager, false);


    }

    @Override
    protected boolean canDragBack() {
        return false;
    }
}
