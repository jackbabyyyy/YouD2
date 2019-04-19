package com.yd.youd.ui.home;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;
import com.yd.youd.model.HomeBean;
import com.yd.youd.net.AppUrl;
import com.yd.youd.net.HttpUtil;
import com.yd.youd.ui.home.deposit.DepositFragment;
import com.yd.youd.ui.home.page.BusinessFragment;
import com.yd.youd.ui.home.page2.ProxyFragment;
import com.yd.youd.ui.home.page3.DeviceFragment;
import com.yd.youd.ui.home.page4.OrderFragment;
import com.yd.youd.ui.home.page5.ProfitFragment;
import com.yd.youd.ui.home.page6.CashFragment;
import com.yd.youd.ui.home.page6.CashGetFragment;
import com.yd.youd.ui.home.page7.DeviceCheckFragment;
import com.yd.youd.utils.AppData;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * Created by PP on 2019/3/5.
 */
public class HomeFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.tvMonth)
    TextView mTvMonth;
    @BindView(R.id.tvCash)
    TextView mTvCash;
    @BindView(R.id.tvToday)
    TextView mTvToday;
    @BindView(R.id.recycler_mid)
    RecyclerView mRecyclerMid;
    @BindView(R.id.recycler_bot)
    RecyclerView mRecyclerBot;
    @BindView(R.id.bar)
    QMUITopBar mQMUITopBar;
    private HomeBotAdapter mHomeBotAdapter;


    @OnClick(R.id.tvCash)
    public void onViewClicked() {
        startFragment(new CashGetFragment());
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        mQMUITopBar.setTitle("幽电");
        mQMUITopBar.addRightImageButton(R.mipmap.msg,R.id.topbar_right);


        mRecyclerMid.setLayoutManager(new GridLayoutManager(getActivity(),4));
        mRecyclerBot.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mRecyclerMid.setNestedScrollingEnabled(false);
        mRecyclerBot.setNestedScrollingEnabled(false);
        mRecyclerBot.setHasFixedSize(true);
        mRecyclerMid.setHasFixedSize(true);
        mRecyclerMid.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top= QMUIDisplayHelper.dp2px(getActivity(),28);
                if (parent.getChildAdapterPosition(view)<4){
                    outRect.bottom=QMUIDisplayHelper.dp2px(getActivity(),15);
                }else{
                    outRect.bottom=QMUIDisplayHelper.dp2px(getActivity(),28);
                }
            }
        });

        mRecyclerBot.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.bottom=QMUIDisplayHelper.dp2px(getActivity(),15);
                if (parent.getChildAdapterPosition(view)%2==0){
                    outRect.right=QMUIDisplayHelper.dp2px(getActivity(),7);
                }else{
                    outRect.left=QMUIDisplayHelper.dp2px(getActivity(),7);
                }
            }
        });

        HomeMidAdapter homeMidAdapter=new HomeMidAdapter(AppData.getHomeMid());
        mHomeBotAdapter = new HomeBotAdapter(AppData.getHomeBot());
        mRecyclerMid.setAdapter(homeMidAdapter);
        mRecyclerBot.setAdapter(mHomeBotAdapter);

        homeMidAdapter.setOnItemClickListener(this);
        mHomeBotAdapter.setOnItemClickListener(this);
        //
        getData();


    }



    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (adapter instanceof HomeMidAdapter){
            switch (position){
                case 0:
                    startFragment(new BusinessFragment());
                break;
                case 1:
                    startFragment(new ProxyFragment());
                    break;
                case 2:
                    startFragment(new DeviceFragment());
                    break;
                case 3:
                    startFragment(new OrderFragment());
                    break;
                case 4:
                    startFragment(new ProfitFragment());
                    break;
                case 5:
                    startFragment(new CashFragment());
                    break;
                case 6:
                    startFragment(new DeviceCheckFragment());
                    break;
            }


        }else if(adapter instanceof HomeBotAdapter){
            switch (position){
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:

                    startFragment(new DepositFragment());
                    break;
            }



        }

    }


    private void getData(){
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.homeData, null, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String s) throws IOException {

                HomeBean bean= JSON.parseObject(s,HomeBean.class);
                mTvMonth.setText(bean.data.month_income);
                mTvCash.setText(bean.data.invalid_withdraw);
                mTvToday.setText(bean.data.day_income);

                mHomeBotAdapter.getData().get(0).des=bean.data.day_order_num+"条";
                mHomeBotAdapter.getData().get(1).des=bean.data.using_equipment+"件";
                mHomeBotAdapter.getData().get(2).des=bean.data.standby_equipment+"件";
                mHomeBotAdapter.setNewData(mHomeBotAdapter.getData());



            }
        });
    }
}
