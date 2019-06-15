package com.ud.share.ui.install;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
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
import com.ud.share.ui.home.page.BusinessAddFragment;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * Created by PP on 2019/4/4.
 */
public class ChooseDeviceItemFragment2 extends BaseFragment {

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;


    private List<DeviceListBean.DataBeanX.DataBean> mChooseDeviceItemLIst = new ArrayList<>();
    private String type;
    public ChooseDeviceAdapter2 mAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_choose_device_item2;
    }

    @Override
    protected void init() {
        type = getArguments().getString("type");

        mAdapter = new ChooseDeviceAdapter2(mChooseDeviceItemLIst);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            int space=QMUIDisplayHelper.dp2px(getActivity(), 10);
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int pos=parent.getChildAdapterPosition(view);
                if (pos%3==0){
                    outRect.left=space;
                    outRect.right=space/2;
                    outRect.bottom=space;
                }else if (pos%3==1){
                    outRect.left=space/2;
                    outRect.right=space/2;
                    outRect.bottom=space;
                }else if (pos%3==2){
                    outRect.left=space/2;
                    outRect.right=space;
                    outRect.bottom=space;
                }

            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                if (mAdapter.getData().get(position).is_distribution.equals("1")) {
                    //不能选择已铺货
                    return;
                }
                if (mAdapter.getData().get(position).check) {
                    mAdapter.getData().get(position).check = false;
                } else {
                    mAdapter.getData().get(position).check = true;
                }
                mAdapter.notifyItemChanged(position);

            }
        });

        getData();

    }

    public static ChooseDeviceItemFragment2 getInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("type", type);

        ChooseDeviceItemFragment2 fragment = new ChooseDeviceItemFragment2();
        fragment.setArguments(bundle);
        return fragment;
    }


    private void getData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("model", type + "");
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.deviceList, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {
                        DeviceListBean bean = JSON.parseObject(response, DeviceListBean.class);
                        mAdapter.setNewData(bean.data.data);
                    }
                });


    }

    @OnClick({R.id.btn, R.id.search})
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.btn:

                List<DeviceListBean.DataBeanX.DataBean> list = mAdapter.getData();

                if (list == null || list.size() == 0) {
                    return;
                }

                List<DeviceListBean.DataBeanX.DataBean> newList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).check) {
                        newList.add(list.get(i));
                    }
                }

                if (list == null || newList.size() == 0) {
                    return;
                }

                //sns
                String[] listArray = new String[newList.size()];
                for (int i = 0; i < newList.size(); i++) {
                    listArray[i] = newList.get(i).sn;
                }


                try {
                    String snsEncode = URLEncoder.encode(Arrays.toString(listArray).replace("[", "").replace("]", "").replace(" ", ""), "UTF-8");
                    startFragment(BusinessAddFragment.getInstance(BusinessAddFragment.INSTALL, snsEncode, type));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.search:

                DeviceSearchFragment fragment=new DeviceSearchFragment();
                Bundle bundle=new Bundle();
                bundle.putString("type",type);
                fragment.setArguments(bundle);
                startFragment(fragment);

                break;

        }


    }

}
