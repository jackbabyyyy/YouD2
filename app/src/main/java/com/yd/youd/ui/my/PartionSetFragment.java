package com.yd.youd.ui.my;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;
import com.yd.youd.model.BaseJson;
import com.yd.youd.net.AppUrl;
import com.yd.youd.net.HttpUtil;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * Created by PP on 2019/3/15.
 */
public class PartionSetFragment extends BaseFragment {
    @BindView(R.id.line)
    EditText mLine;
    @BindView(R.id.cabinet)
    EditText mCabinet;
    @BindView(R.id.bn)
    Button mBn;

    @BindView(R.id.bar)
    QMUITopBarLayout mBar;


    @Override
    protected int getLayoutId() {
        return R.layout.fragmet_partion;
    }

    @Override
    protected void init() {
        mBar.setTitle("分润设置");
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

    }


    @OnClick(R.id.bn)
    public void onViewClicked() {
        String line=mLine.getText().toString().trim();
        String cabinet=mCabinet.getText().toString().trim();
        HashMap<String,String> map=new HashMap<>();
        map.put("sub_line_rate",line);
        map.put("sub_cabinet_rate",cabinet);
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.partionSet, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {


            }


            @Override
            public void onResponse(String response) throws IOException {
                BaseJson json= JSON.parseObject(response,BaseJson.class);
                showToast(json.msg);
                popBackStack();


            }
        });


    }
}
