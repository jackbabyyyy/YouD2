package com.ud.share.ui;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUIPagerAdapter;
import com.qmuiteam.qmui.widget.QMUIViewPager;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.ui.home.HomeFragment2;
import com.ud.share.ui.install.InstallFragment;
import com.ud.share.ui.my.MyFragment;
import com.ud.share.ui.shop.ShopFragment;
import com.ud.share.ui.statistics.StatisticsFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by PP on 2019/3/5.
 */
public class MainFragment2 extends BaseFragment  {

    @BindView(R.id.pager)
    QMUIViewPager mViewPager;

    @BindView(R.id.rg_tab)
    LinearLayout mRadioGroup;
    @BindView(R.id.send_igv)
    ImageView mTabMid;
    @BindView(R.id.send_tv)
    TextView mTabMidText;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main2;
    }


    private int[] tab = {R.mipmap.tab1_, R.mipmap.tab2_,-1, R.mipmap.tab3_, R.mipmap.tab4_};
    private int[] tab2 = {R.mipmap.tab1, R.mipmap.tab2,-1, R.mipmap.tab3, R.mipmap.tab4};

    @Override
    protected void init() {

        initTabs();
        initPagers();
    }

    @OnClick({R.id.main_rb,R.id.message_rb,R.id.send_rl,R.id.friend_rb,R.id.info_rb})
    protected void onClick(View view) {
        switch (view.getId()){
            case R.id.main_rb:
               checkTab(0);
                break;
            case R.id.message_rb:
                checkTab(1);
                break;
            case R.id.send_rl:
                checkTab(2);
                break;
            case R.id.friend_rb:
                checkTab(3);
                break;
            case R.id.info_rb:
                checkTab(4);
                break;

        }

    }

    private void initTabs() {





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
                        return new HomeFragment2();
                    case 1:
                        return new StatisticsFragment();
                    case 2:
                        return new InstallFragment();
                    case 3:
                        return new ShopFragment();
                    case 4:
                        return new MyFragment();
                    default:
                        return new HomeFragment2();

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
                return 5;
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

    }

    @Override
    protected boolean canDragBack() {
        return false;
    }





    private void clickTab(int clicked) {

        if (clicked==2){
            mTabMid.setImageResource(R.mipmap.tab_mid_check);
            mTabMidText.setTextColor(getActivity().getResources().getColor(R.color.theme2));
            for (int j=0;j<mRadioGroup.getChildCount();j++){
                if (j==2){

                }else{
                    LinearLayout layout = (LinearLayout) mRadioGroup.getChildAt(j);
                    ImageView imageView = (ImageView) layout.getChildAt(0);
                    imageView.setImageResource(tab[j]);
                    TextView textView= (TextView) layout.getChildAt(1);
                    textView.setTextColor(getActivity().getResources().getColor(R.color.black));

                }



            }
        }else{

            mTabMidText.setTextColor(getActivity().getResources().getColor(R.color.black));
            mTabMid.setImageResource(R.mipmap.tab_mid);
            for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
                if (i == 2) {
                    continue;
                } else {
                    LinearLayout layout = (LinearLayout) mRadioGroup.getChildAt(i);
                    ImageView imageView = (ImageView) layout.getChildAt(0);
                    TextView textView= (TextView) layout.getChildAt(1);

                    if (i == clicked) {

                        imageView.setImageResource(tab2[i]);
                        textView.setTextColor(getActivity().getResources().getColor(R.color.theme2));
                    }else{

                        imageView.setImageResource(tab[i]);
                        textView.setTextColor(getActivity().getResources().getColor(R.color.black));
                    }

                }


            }

        }



    }

    private void checkTab(int i){
        clickTab(i);
        mViewPager.setCurrentItem(i,false);
    }
}
