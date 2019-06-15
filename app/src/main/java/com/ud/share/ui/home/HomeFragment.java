package com.ud.share.ui.home;

import android.Manifest;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.model.HomeBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.ui.home.page.BusinessFragment;
import com.ud.share.ui.home.page2.ProxyFragment;
import com.ud.share.ui.home.page3.DeviceFragment;
import com.ud.share.ui.home.page4.OrderFragment;
import com.ud.share.ui.home.page5.ProfitDetailFragment;
import com.ud.share.ui.home.page6.CashFragment;
import com.ud.share.ui.home.page6.CashGetFragment;
import com.ud.share.utils.AppData;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by PP on 2019/3/5.
 */
public class HomeFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener , EasyPermissions.PermissionCallbacks {


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


    @OnClick({R.id.tvCash,R.id.textView3})
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
   //     mQMUITopBar.addRightImageButton(R.mipmap.msg,R.id.topbar_right);



        applyPermissions();
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
                    startFragment( OrderFragment.getInstance(0));
                    break;
                case 4:
                    startFragment(new ProfitDetailFragment());
                    break;
                case 5:
                    startFragment(new CashFragment());
                    break;
                case 6:
                    showTODO();
//                    if (EasyPermissions.hasPermissions(getActivity(), Manifest.permission.CAMERA)){
//                        startFragment( ScanFragment.getInstance(ScanFragment.GO_CHECK));
//                    }else{
//                        EasyPermissions.requestPermissions(getActivity(),"需要使用相机",888,Manifest.permission.CAMERA);
//                    }
                    break;
                case 7:
                    showTODO();

                    break;

            }


        }else if(adapter instanceof HomeBotAdapter){
            switch (position){
                case 0:
                    startFragment(OrderFragment.getInstance(0));
                    break;
                case 1:
                    startFragment(OrderFragment.getInstance(1));
                    break;
                case 2:
                    startFragment(new DeviceFragment());
                    break;
                case 3:

                    showTODO();
            //        startFragment(new DepositFragment());
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

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

    }
}
