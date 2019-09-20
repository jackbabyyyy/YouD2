package com.ud.share.ui.home.page3;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.comm.InstallObjectEnum;
import com.ud.share.model.AllDeviceBean;
import com.ud.share.model.DeviceTypeBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.ui.install.ChooseBusinessFragment;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * Created by PP on 2019/3/12.
 */
public class DeviceFragment extends BaseFragment {

    @BindView(R.id.content)
    EditText mSearch;
    @BindView(R.id.bar_right)
    TextView mBarRight;
    @BindView(R.id.tabSegment)
    QMUITabSegment mTabSegment;
    @BindView(R.id.contentViewPager)
    ViewPager mContentViewPager;
    @BindView(R.id.bar)
    QMUITopBarLayout mBar;

    private int mItemCount;
    private List<BaseFragment> mBaseFragments = new ArrayList<>();
    private FragmentPagerAdapter mPagerAdapter;
    private MyWatcher mWatcher;
    private int mType;


    public static DeviceFragment getInsatnce(int type) {
        DeviceFragment deviceFragment = new DeviceFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        deviceFragment.setArguments(bundle);
        return deviceFragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_device;
    }

    @Override
    protected void init() {


        mType = getArguments().getInt("type");
        String title = null;
        if (mType == -1) {
            title = "所有设备列表";
        } else if (mType == 1) {
            title = "本人设备列表";
            mBar.addRightTextButton("划拨设备", R.id.bar_right).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startFragment(ChooseBusinessFragment.getInstance(InstallObjectEnum.LOWERPROXY.getId()));
                }
            });
        } else if (mType == 2) {
            title = "下级代理商设备";
        }
        mBar.setTitle(title);
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
        // getItem();
//        getDeviceType();
        mSearch.setHint("请输入您想要搜索的设备SN码");

        mWatcher = new MyWatcher();
        mSearch.addTextChangedListener(mWatcher);
        mSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Drawable drawable = mSearch.getCompoundDrawables()[2];
                if (drawable == null)
                    return false;

                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;

                if (event.getX() > mSearch.getWidth() - mSearch.getPaddingRight() - drawable.getIntrinsicWidth()) {

                    mSearch.setText("");

                    mSearch.setCompoundDrawables(null, null, null, null);


                }
                return false;
            }

        });

        mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //关闭软键盘
                    closeKeyBorad();
                    getSearch();

                    return true;
                }
                return false;
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
        mTabSegment.addTab(new QMUITabSegment.Tab("已绑定"));
        mTabSegment.addTab(new QMUITabSegment.Tab("未绑定"));


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


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getItem();
            }
        }, 200);


    }


    private void getItem() {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "1");
        if (mType > 0) {
            map.put("type", mType + "");
        }
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.allDevice, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {


            }



            @Override
            public void onResponse(String s) throws IOException {
                AllDeviceBean bean = JSON.parseObject(s, AllDeviceBean.class);
                mTabSegment.getTab(0).setText("已绑定[" + bean.total.binded + "条]");
                mTabSegment.getTab(1).setText("未绑定[" + bean.total.unbind + "条]");
                mTabSegment.notifyDataChanged();
                mBaseFragments.add(DeviceItemFragment.getInstance(s, 1));
                mPagerAdapter.notifyDataSetChanged();


                HashMap<String, String> map2 = new HashMap<>();
                map2.put("status", "2");
                if (mType > 0) {
                    map2.put("type", mType + "");
                }
                HttpUtil.getInstance(getActivity()).postForm(AppUrl.allDevice, map2, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {


                    }

                    @Override
                    public void onResponse(String s) throws IOException {
                        AllDeviceBean bean = JSON.parseObject(s, AllDeviceBean.class);


                        mBaseFragments.add(DeviceItemFragment.getInstance(s, 2));

                        mPagerAdapter.notifyDataSetChanged();

                    }
                });


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
                DeviceTypeBean bean = JSON.parseObject(response, DeviceTypeBean.class);
                mTabSegment.reset();
//                mTabSegment.addTab(new QMUITabSegment.Tab(bean.data.t1));
//                mTabSegment.addTab(new QMUITabSegment.Tab(bean.data.t2));
                mTabSegment.addTab(new QMUITabSegment.Tab(bean.data.t3));
//                mTabSegment.addTab(new QMUITabSegment.Tab(bean.data.t4));
                mTabSegment.notifyDataChanged();


                mPagerAdapter.notifyDataSetChanged();


            }
        });
    }


    @OnClick(R.id.bar_right)
    public void onClick() {


        getSearch();

    }

    private void getSearch() {
        String search = mSearch.getText().toString() + "";
        HashMap<String, String> map = new HashMap<>();
        map.put("status", mTabSegment.getSelectedIndex() == 0 ? "1" : "2");
        map.put("keyword", search);
        if (mType > 0) {
            map.put("type", mType + "");
        }
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.allDevice, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {


            }

            @Override
            public void onResponse(String s) throws IOException {

                EventBus.getDefault().post(new MsgDevice(s, mTabSegment.getSelectedIndex() + 1));

            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        mSearch.removeTextChangedListener(mWatcher);
    }

    class MyWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            Drawable drawable = getResources().getDrawable(R.mipmap.close);

            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            if (TextUtils.isEmpty(s.toString().trim())) {
                getSearch();
                mSearch.setCompoundDrawables(null, null, null, null);
            } else {
                mSearch.setCompoundDrawables(null, null, drawable, null);
            }

        }
    }


}
