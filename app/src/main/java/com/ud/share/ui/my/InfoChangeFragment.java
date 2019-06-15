package com.ud.share.ui.my;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.model.BaseJson;
import com.ud.share.model.InfoBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.utils.AppData;
import com.ud.share.utils.CardNumUtils;
import com.ud.share.utils.SP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Created by PP on 2019/3/8.
 */
public class InfoChangeFragment extends BaseFragment {


    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    private InfoChangeAdapter mAdapter;
    private LinearLayoutManager mManager;
    private String mCredit_num;
    private String mBank_name;
    private String mBelong_bank;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info_change;
    }

    public static InfoChangeFragment getInstance(String json){
        InfoChangeFragment fragment=new InfoChangeFragment();
        Bundle bundle=new Bundle();
        bundle.putString("json",json);
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    protected void init() {
        String lastPageString = getArguments().getString("json");


        mManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mManager);
        mAdapter = new InfoChangeAdapter(initInfoData(lastPageString));
        mRecyclerView.setAdapter(mAdapter);


        mBar.setTitle("个人信息修改");
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
        mBar.addRightTextButton("提交", R.id.topbar_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getInfoChange();

            }
        });


           //
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i<3;i++){
                    mManager.findViewByPosition(i).findViewById(R.id.content).setEnabled(false);
                    }
                }
            },200);




    }

    private void getInfoChange() {
         new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mCredit_num=((EditText)mManager.findViewByPosition(3).findViewById(R.id.content)).getText().toString();
                mBank_name=((EditText)mManager.findViewByPosition(4).findViewById(R.id.content)).getText().toString();
                mBelong_bank=((EditText)mManager.findViewByPosition(5).findViewById(R.id.content)).getText().toString();

                if (TextUtils.isEmpty(mCredit_num)){
                    showToast("请输入银行卡号");
                    return;
                }

                if (!CardNumUtils.matchLuhn(mCredit_num)){
                    showToast("请输入正确的银行卡号");
                    return;
                }

                if (TextUtils.isEmpty(mBank_name)){
                    showToast("请填写正确的银行");
                    return;
                }
                if (TextUtils.isEmpty(mBelong_bank)){
                    showToast("请填写正确的所属分行");
                    return;
                }


                Map<String,String> map=new HashMap<>();
                map.put("credit_num",mCredit_num+"");
                map.put("bank_name",mBank_name+"");
                map.put("belong_bank",mBelong_bank+"");
                HttpUtil.getInstance(getActivity()).postForm(AppUrl.infoChange, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {
                        BaseJson json=JSON.parseObject(response,BaseJson.class);
                        if (json.code==1){
                            String info= SP.getInfo(getActivity());
                            InfoBean bean=JSON.parseObject(info,InfoBean.class);
                            bean.data.credit_num=mCredit_num+"";
                            bean.data.bank_name=mBank_name+"";
                            bean.data.belong_bank=mBelong_bank+"";
                            String infoChange=JSON.toJSONString(bean);
                            SP.saveInfo(getActivity(),infoChange);
                        }
                        showToast(json.msg);
                        popBackStack();

                    }
                });

            }
        },200);

    }


    private List<AppData.InfoBean> initInfoData(String lastPageString){
        InfoBean infoBean= JSON.parseObject(lastPageString,InfoBean.class);
        List<String > contents=new ArrayList<>();
        contents.add(infoBean.data.realname);
        contents.add(infoBean.data.phone);
        contents.add(infoBean.data.card_sn);
        contents.add(infoBean.data.credit_num);
        contents.add(infoBean.data.bank_name);
        contents.add(infoBean.data.belong_bank);
        List<AppData.InfoBean> infoBeanList=new ArrayList<>();
        for (int i=0;i<AppData.getInfo().size();i++){
            AppData.InfoBean bean=new AppData.InfoBean();
            bean.title=AppData.getInfo().get(i);
            bean.name=contents.get(i);
            infoBeanList.add(bean);

        }
        return infoBeanList;

    }






}
