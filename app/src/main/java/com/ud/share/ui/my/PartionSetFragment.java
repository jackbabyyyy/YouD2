package com.ud.share.ui.my;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.model.BaseJson;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
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
        map.put("sub_cabinet_rate","0");
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
