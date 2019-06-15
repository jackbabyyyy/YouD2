package com.ud.share.ui.home.page3;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.model.DeviceListBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;

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
public class DeviceItemFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    private List<DeviceListBean.DataBeanX.DataBean> mDeviceItems=new ArrayList<>();
    private DeviceItemAdapter mAdapter;
    private String type="";

    private int mPage=1;
    private DeviceListBean mBean;
    private String mKey="";


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_device_item;
    }

    public static DeviceItemFragment getInstance(String type){
        Bundle bundle=new Bundle();
        bundle.putString("type",type);
        DeviceItemFragment fragment=new DeviceItemFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void init() {
        EventBus.getDefault().register(this);

        type=getArguments().getString("type");
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        getData();
        mAdapter = new DeviceItemAdapter(mDeviceItems);
        mRecycler.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this,mRecycler);

        mRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildAdapterPosition(view)==0){
                    outRect.top= QMUIDisplayHelper.dp2px(getActivity(),20);
                }
            }
        });
    }

    private void getData() {
        HashMap<String,String> map=new HashMap<>();
        map.put("model",type+"");
        map.put("per_page", "10");
        map.put("page", mPage + "");
        map.put("sn",mKey+"");

        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.deviceList, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {
                        mBean = JSON.parseObject(response, DeviceListBean.class);
                        mAdapter.addData(mBean.data.data);
                        mAdapter.loadMoreComplete();

                    }
                });


    }


    @Override
    public void onLoadMoreRequested() {
        mPage++;
        if (mBean.data.last_page.equals(mBean.data.current_page)) {
            mAdapter.loadMoreEnd();
            return;
        }
        getData();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MsgDevice event) {
        mKey = event.device;
        mPage=1;


        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        getData();


    }


}
