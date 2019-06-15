package com.ud.share.ui.home;

import android.Manifest;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.map.LngLatManager;
import com.ud.share.map.OnLngLatChangeListener;
import com.ud.share.model.HomeBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.ui.MDGridRvDividerDecoration;
import com.ud.share.ui.extend.ExtendFragment;
import com.ud.share.ui.home.page.BusinessFragment;
import com.ud.share.ui.home.page2.ProxyFragment;
import com.ud.share.ui.home.page3.DeviceFragment;
import com.ud.share.ui.home.page4.OrderFragment;
import com.ud.share.ui.home.page5.ProfitDetailFragment;
import com.ud.share.ui.home.page6.CashFragment;
import com.ud.share.ui.home.page6.CashGetFragment;
import com.ud.share.utils.AppData;


import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * Created by PP on 2019/5/27.
 */
public class HomeFragment2 extends BaseFragment {


    @BindView(R.id.bar)
    QMUITopBar mBar;
    @BindView(R.id.recycler_mid)
    RecyclerView mRecyclerViewMid;
    @BindView(R.id.recycler_bot)
    RecyclerView mRecyclerViewBot;

    @BindView(R.id.tvMonth)
    TextView mTvProfit;
    @BindView(R.id.textView3)
    TextView mTvGet;
    @BindView(R.id.tvCash)
    TextView mTvHistory;
    @BindView(R.id.tvToday)
    TextView mTvAll;

    @OnClick({R.id.textView3,R.id.tvCash})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case  R.id.textView3:
                startFragment(new CashGetFragment());
                break;
            case R.id.tvCash:
                startFragment(new CashFragment());
                break;
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home2;
    }

    @Override
    protected void init() {
        mBar.setTitle("幽电科技");

        initLocation();
        mRecyclerViewMid.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRecyclerViewBot.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        HomeImgTitleAdapter adapterMid = new HomeImgTitleAdapter(R.layout.adapter_home_mid2, AppData.getHomeMid2());
        HomeImgTitleAdapter adapterBot = new HomeImgTitleAdapter(R.layout.adapter_home_bot2, AppData.getHomeBot2());

        mRecyclerViewMid.setAdapter(adapterMid);
        mRecyclerViewBot.setAdapter(adapterBot);

     //   mRecyclerViewBot.addItemDecoration(new MDGridRvDividerDecoration(getActivity()));

        adapterMid.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                switch (position) {
                    case 0:
                        startFragment(OrderFragment.getInstance(1));
                        break;
                    case 1:
                        startFragment(OrderFragment.getInstance(0));
                        break;
                    case 2:
                        startFragment(OrderFragment.getInstance(0));
                        break;
                    case 3:
                        //今日收益
                        showTODO();
                        break;
                }


            }
        });
        adapterBot.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        startFragment( BusinessFragment.getInstance(mLng,mLat));
                        break;
                    case 1:
                        startFragment(new ProxyFragment());
                        break;
                    case 2:
                        startFragment(new DeviceFragment());
                        break;
                    case 3:
                        startFragment(OrderFragment.getInstance(0));
                        break;
                    case 4:
                        startFragment(new ProfitDetailFragment());
                        break;
                    case 5:
                        startFragment(new CashGetFragment());
                        break;
                    case 6:
                        showTODO();
                        //todo 佣金管理
                        break;
                    case 7:
                        showTODO();
                        //todo 分润设置
                        break;
                    case 8:
                        showTODO();
                        //todo 设备故障
                        break;
                    case 9:
                        startFragment(new ExtendFragment());
                        //todo 幽电分享
                        break;
                    case 10:
                        showTODO();
                        //todo 超级用户
                        break;
                    case 11:
                        showTODO();
                        //todo 更多
                        break;
                }
            }
        });
        applyPermissions();

    }

    private void getData(){
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.homeData, null, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String s) throws IOException {

                HomeBean bean= JSON.parseObject(s,HomeBean.class);

                mTvProfit.setText(bean.data.invalid_withdraw);





            }
        });
    }

    private void applyPermissions() {

        new RxPermissions(getActivity()).request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(granted -> {
                    if (granted) {
                        // All requested permissions are granted
                    } else {
                        // At least one permission is denied
                    }
                });




    }

    private LngLatManager mLngLatManager;
    private String mLng="",mLat="";
    private void initLocation(){
        mLngLatManager = new LngLatManager(getActivity());
        mLngLatManager.setOnLngLatChangeListener(new OnLngLatChangeListener() {
            @Override
            public void lnglatChange(String lng,String lat) {
               mLng=lng;
               mLat=lat;

            }


        });
        mLngLatManager.startLocaion();
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }
}
