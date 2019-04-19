package com.yd.youd.ui.home.page3;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;
import com.yd.youd.model.DeviceListBean;
import com.yd.youd.net.AppUrl;
import com.yd.youd.net.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;


/**
 * Created by PP on 2019/3/26.
 */
public class DeviceItemFragment extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    private List<DeviceListBean.DataBeanX.DataBean> mDeviceItems=new ArrayList<>();
    private DeviceItemAdapter mAdapter;
    private String type;

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
        type=getArguments().getString("type");
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        getData();
        mAdapter = new DeviceItemAdapter(mDeviceItems);
        mRecycler.setAdapter(mAdapter);
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
        map.put("model",type);
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.deviceList, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {
//                        response="{\n" +
//                                "\t\"code\": 1,\n" +
//                                "\t\"msg\": \"操作成功\",\n" +
//                                "\t\"data\": {\n" +
//                                "\t\t\"total\": 5,\n" +
//                                "\t\t\"per_page\": 20,\n" +
//                                "\t\t\"current_page\": 1,\n" +
//                                "\t\t\"last_page\": 1,\n" +
//                                "\t\t\"data\": [{\n" +
//                                "\t\t\t\t\"sn\": \"X2019D00096\",\n" +
//                                "\t\t\t\t\"is_distribution\": \"0\",\n" +
//                                "\t\t\t\t\"shop_name\": \"\"\n" +
//                                "\t\t\t},\n" +
//                                "\t\t\t{\n" +
//                                "\t\t\t\t\"sn\": \"X2019D00098\",\n" +
//                                "\t\t\t\t\"is_distribution\": \"1\",\n" +
//                                "\t\t\t\t\"shop_name\": \"测试商户\"\n" +
//                                "\t\t\t},\n" +
//                                "\t\t\t{\n" +
//                                "\t\t\t\t\"sn\": \"X2019D00099\",\n" +
//                                "\t\t\t\t\"is_distribution\": \"0\",\n" +
//                                "\t\t\t\t\"shop_name\": \"\"\n" +
//                                "\t\t\t},\n" +
//                                "\t\t\t{\n" +
//                                "\t\t\t\t\"sn\": \"X2019D00100\",\n" +
//                                "\t\t\t\t\"is_distribution\": \"1\",\n" +
//                                "\t\t\t\t\"shop_name\": \"测试商户\"\n" +
//                                "\t\t\t}\n" +
//                                "\t\t]\n" +
//                                "\t}\n" +
//                                "}";
                        DeviceListBean bean= JSON.parseObject(response,DeviceListBean.class);
                        mAdapter.setNewData(bean.data.data);

                    }
                });


    }


}
