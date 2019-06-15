package com.ud.share.ui.home.page;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.model.BaseJson;
import com.ud.share.model.BusinessDetailBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Request;

public class BusinessChargeFragment extends BaseFragment {


    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.section_name)
    TextView mSectionName;
    @BindView(R.id.content)
    EditText mContent;
    @BindView(R.id.content2)
    EditText mContent2;
    private BusinessDetailBean mBusinessDetailBean;


    public static BusinessChargeFragment getInstance(String infoJson) {
        Bundle bundle = new Bundle();
        bundle.putString("info", infoJson);
        BusinessChargeFragment fragment = new BusinessChargeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_business_charge;
    }

    @Override
    protected void init() {
        mBusinessDetailBean = JSON.parseObject(getArguments().getString("info"), BusinessDetailBean.class);

        mSectionName.setText("门店计费单价");
        mBar.setTitle("修改计费方式");
        mBar.addRightTextButton("提交", R.id.topbar_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();
            }
        });
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popBackStack();
            }
        });


    }

    private void getData() {
        String p1=mContent.getText().toString().trim();
        String p2=mContent2.getText().toString().trim();
        if (TextUtils.isEmpty(p1)||TextUtils.isEmpty(p2)){
            showToast("请填写完整");
            return;
        }
        HashMap<String,String> map=new HashMap<>();
        map.put("per_price",p1);
        map.put("per_ceiling",p2);
        map.put("sid", mBusinessDetailBean.data.seller.sid);

        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.businessChange, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }
                    @Override
                    public void onResponse(String s) throws IOException {
                           BaseJson json=JSON.parseObject(s,BaseJson.class);
                           showToast(json.msg);
                           popBackStack();

                    }
                });
    }


}
