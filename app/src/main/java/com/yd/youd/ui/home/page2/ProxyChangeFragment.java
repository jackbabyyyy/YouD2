package com.yd.youd.ui.home.page2;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;
import com.yd.youd.model.BaseJson;
import com.yd.youd.model.ProxyListBean;
import com.yd.youd.net.AppUrl;
import com.yd.youd.net.HttpUtil;
import com.yd.youd.utils.AppData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Created by PP on 2019/3/13.
 */
public class ProxyChangeFragment extends BaseFragment {

    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    private ProxyListBean.DataBeanX.DataBean mDataBean;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_proxy_change;
    }



    public static ProxyChangeFragment getInstance(String json){
        ProxyChangeFragment fragment=new ProxyChangeFragment();
        Bundle bundle=new Bundle();
        bundle.putString("json",json);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void init() {

         String lastPageJson = getArguments().getString("json");
        mDataBean = JSON.parseObject(lastPageJson, ProxyListBean.DataBeanX.DataBean.class);

        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
        mBar.setTitle("修改代理商信息");
        mBar.addRightTextButton("提交",R.id.topbar_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAgentChange();
            }
        });
        
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildAdapterPosition(view)==0){
                    outRect.top= QMUIDisplayHelper.dp2px(getActivity(),25);
                }
                outRect.bottom= QMUIDisplayHelper.dp2px(getActivity(),25);
            }
        });


        ProxyChangeAdapter adapter=new ProxyChangeAdapter(AppData.getAgentInfo(lastPageJson));
        mRecycler.setAdapter(adapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecycler.getLayoutManager().findViewByPosition(0).findViewById(R.id.content).setEnabled(false);
                mRecycler.getLayoutManager().findViewByPosition(1).findViewById(R.id.content).setEnabled(false);

            }
        },100);

    }

    private void getAgentChange() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

        String line=((EditText) mRecycler.getLayoutManager().findViewByPosition(2).findViewById(R.id.content)).getText().toString();
        String cabinet=((EditText) mRecycler.getLayoutManager().findViewByPosition(3).findViewById(R.id.content)).getText().toString();
        String deposit=((EditText) mRecycler.getLayoutManager().findViewByPosition(4).findViewById(R.id.content)).getText().toString();


        Map<String,String> map=new HashMap<>();
        map.put("agent_id",mDataBean.agent_id+"");
        map.put("line_rate",line);
        map.put("cabinet_rate",cabinet);
        map.put("deposit",deposit);
                Log.d("http", "run: "+mDataBean.agent_id+"A"+line+"A"+cabinet+"A"+deposit);
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.proxyChange, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {
                        BaseJson json= JSON.parseObject(response,BaseJson.class);
                        showToast(json.msg);

                    }
                });
    }
},100);


        }
}
