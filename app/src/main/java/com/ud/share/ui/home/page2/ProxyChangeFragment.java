package com.ud.share.ui.home.page2;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.event.FreshProxyEvent;
import com.ud.share.model.BaseJson;
import com.ud.share.model.ProxyListBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.utils.AppData;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;
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
                mRecycler.getLayoutManager().findViewByPosition(1).findViewById(R.id.content).setEnabled(false);
                mRecycler.getLayoutManager().findViewByPosition(2).findViewById(R.id.content).setEnabled(false);

            }
        },100);

    }

    private void getAgentChange() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

        String line=((EditText) mRecycler.getLayoutManager().findViewByPosition(3).findViewById(R.id.content)).getText().toString();
//        String cabinet=((EditText) mRecycler.getLayoutManager().findViewByPosition(4).findViewById(R.id.content)).getText().toString();
        String deposit=((EditText) mRecycler.getLayoutManager().findViewByPosition(4).findViewById(R.id.content)).getText().toString();
        String agent_name=((EditText) mRecycler.getLayoutManager().findViewByPosition(0).findViewById(R.id.content)).getText().toString();


        Map<String,String> map=new HashMap<>();
        map.put("agent_id",mDataBean.agent_id+"");
        map.put("line_rate",line);
        map.put("cabinet_rate","0");
        map.put("deposit",deposit);
        map.put("agent_name",agent_name);

        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.proxyChange, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {
                        EventBus.getDefault().post(new FreshProxyEvent());
                        BaseJson json= JSON.parseObject(response,BaseJson.class);
                        showToast(json.msg);
                        popBackStack();

                    }
                });
    }
},100);


        }
}
