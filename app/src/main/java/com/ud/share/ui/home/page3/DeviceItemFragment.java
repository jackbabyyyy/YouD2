package com.ud.share.ui.home.page3;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.model.AllDeviceBean;
import com.ud.share.model.DeviceListBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.ui.home.page.BusinessDetailFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;


/**
 * Created by PP on 2019/3/26.
 */
public class DeviceItemFragment extends BaseFragment
//        implements BaseQuickAdapter.RequestLoadMoreListener
{
    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    private List<AllDeviceBean.DataBean> mDeviceItems=new ArrayList<>();
    private DeviceItemAdapter mAdapter;
    private String data="";

    private int mPage=1;
    private DeviceListBean mBean;
    private String mKey="";
    private int mType;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_device_item;
    }

    public static DeviceItemFragment getInstance(String data,int type){
        Bundle bundle=new Bundle();
        bundle.putString("data",data);
        bundle.putInt("type",type);
        DeviceItemFragment fragment=new DeviceItemFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void init() {
        EventBus.getDefault().register(this);

        data=getArguments().getString("data");
        mType = getArguments().getInt("type");


//        getData();
        mDeviceItems=JSON.parseObject(data,AllDeviceBean.class).data;
        if (mDeviceItems.size()!=0){
            if (mType==2){
                mRecycler.setLayoutManager((new GridLayoutManager(getActivity(), 3)));
                mRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
                    int space = QMUIDisplayHelper.dp2px(getActivity(), 10);

                    @Override
                    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                        super.getItemOffsets(outRect, view, parent, state);
                        int pos = parent.getChildAdapterPosition(view);
                        if (pos<3){
                            outRect.top=space;
                        }
                        if (pos % 3 == 0) {
                            outRect.left = space;
                            outRect.right = space / 2;
                            outRect.bottom = space;
                        } else if (pos % 3 == 1) {
                            outRect.left = space / 2;
                            outRect.right = space / 2;
                            outRect.bottom = space;
                        } else if (pos % 3 == 2) {
                            outRect.left = space / 2;
                            outRect.right = space;
                            outRect.bottom = space;
                        }

                    }
                });
            }else{
                mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                        super.getItemOffsets(outRect, view, parent, state);
                        int space=QMUIDisplayHelper.dp2px(getActivity(),16);
                        if (parent.getChildAdapterPosition(view)==0){
                            outRect.top=space;
                        }
                            outRect.bottom= space;
                            outRect.left=space/2;
                            outRect.right=space/2;

                    }
                });
            }
        }
        else{
            mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

        mAdapter = new DeviceItemAdapter(mDeviceItems,mType);
        mRecycler.setAdapter(mAdapter);
//        mAdapter.setOnLoadMoreListener(this,mRecycler);



        mAdapter.bindToRecyclerView(mRecycler);
        mAdapter.setEmptyView(R.layout.recycler_empty);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mType==1){
                    startFragment(BusinessDetailFragment.getInstance(mDeviceItems.get(position).sid));

                }
            }
        });
    }

//    private void getData() {
//        HashMap<String,String> map=new HashMap<>();
//        map.put("model",type+"");
//        map.put("per_page", "10");
//        map.put("page", mPage + "");
//        map.put("sn",mKey+"");
//
//        HttpUtil.getInstance(getActivity())
//                .postForm(AppUrl.deviceList, map, new HttpUtil.ResultCallback() {
//                    @Override
//                    public void onError(Request request, Exception e) {
//
//                    }
//
//                    @Override
//                    public void onResponse(String response) throws IOException {
//                        mBean = JSON.parseObject(response, DeviceListBean.class);
//                        mAdapter.addData(mBean.data.data);
//                        mAdapter.loadMoreComplete();
//
//                    }
//                });
//
//
//    }


//    @Override
//    public void onLoadMoreRequested() {
//        mPage++;
//        if (mBean.data.last_page.equals(mBean.data.current_page)) {
//            mAdapter.loadMoreEnd();
//            return;
//        }
//        getData();
//
//    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MsgDevice event) {
       String data=event.device;
       if (event.type==mType){
           mDeviceItems=JSON.parseObject(data,AllDeviceBean.class).data;
           mAdapter.setNewData(mDeviceItems);

       }

    }


}
