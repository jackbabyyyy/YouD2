package com.ud.share.ui.home.page;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.event.FreshBusinessEvent;
import com.ud.share.model.BaseJson;
import com.ud.share.model.BusinessDetailBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Created by PP on 2019/3/26.
 */
public class BusinessTariffFragment extends BaseFragment {


    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.section_name)
    TextView mSectionName;

    private List<TariffItem> mTariffItems = new ArrayList<>();
    private BusinessDetailBean mBusinessDetailBean;
    private BusinessTariffAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_business_tariff;
    }

    public static BusinessTariffFragment getInstance(String infoJson) {
        Bundle bundle = new Bundle();
        bundle.putString("info", infoJson);
        BusinessTariffFragment fragment = new BusinessTariffFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void init() {

        mBusinessDetailBean = JSON.parseObject(getArguments().getString("info"), BusinessDetailBean.class);
        mSectionName.setText("充电资费标准");
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        mBar.setTitle("修改资费标准");
        mBar.addRightTextButton("提交", R.id.topbar_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();
            }
        });
        //
        mTariffItems.add(new TariffItem("2小时", mBusinessDetailBean.data.seller.line_hour_2));
        mTariffItems.add(new TariffItem("6小时", mBusinessDetailBean.data.seller.line_hour_6));
        mTariffItems.add(new TariffItem("12小时", mBusinessDetailBean.data.seller.line_hour_12));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom= QMUIDisplayHelper.dp2px(getActivity(),15);
            }
        });
        mAdapter = new BusinessTariffAdapter(mTariffItems);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void getData() {


        new Handler().post(new Runnable() {
            @Override
            public void run() {

                EditText line2 = mRecyclerView.getLayoutManager().findViewByPosition(0).findViewById(R.id.value);
                EditText line6 = mRecyclerView.getLayoutManager().findViewByPosition(1).findViewById(R.id.value);
                EditText line12 = mRecyclerView.getLayoutManager().findViewByPosition(2).findViewById(R.id.value);

                String l2 = line2.getText().toString().trim();
                String l6 = line6.getText().toString().trim();
                String l12 = line12.getText().toString().trim();
                if (TextUtils.isEmpty(l2) || TextUtils.isEmpty(l6) || TextUtils.isEmpty(l12)) {
                    showToast("请填写完整");
                    return;
                }

                HashMap<String, String> map = new HashMap<>();
                map.put("line_hour_2", l2);
                map.put("line_hour_6", l6);
                map.put("line_hour_12", l12);
                map.put("sid",mBusinessDetailBean.data.seller.sid);

                HttpUtil.getInstance(getActivity())
                        .postForm(AppUrl.businessChange, map, new HttpUtil.ResultCallback() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(String s) throws IOException {
                                EventBus.getDefault().post(new FreshBusinessEvent());
                                BaseJson json=JSON.parseObject(s,BaseJson.class);
                                showToast(json.msg);
                                popBackStack();

                            }
                        });
            }
        });

    }


}
