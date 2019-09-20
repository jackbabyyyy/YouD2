//package com.ud.share.ui.install;
//
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.TextureView;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//
//import com.alibaba.fastjson.JSON;
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.qmuiteam.qmui.widget.QMUITopBarLayout;
//import com.ud.share.R;
//import com.ud.share.base.BaseFragment;
//import com.ud.share.model.DeviceListBean;
//import com.ud.share.model.DeviceSearchBean;
//import com.ud.share.net.AppUrl;
//import com.ud.share.net.HttpUtil;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import butterknife.Unbinder;
//import okhttp3.Request;
//
///**
// * Created by PP on 2019/6/1.
// */
//public class DeviceSearchFragment extends BaseFragment {
//
//
//    @BindView(R.id.bar)
//    QMUITopBarLayout mBar;
//    @BindView(R.id.etStart)
//    EditText mEtStart;
//    @BindView(R.id.recycler)
//    RecyclerView mRecycler;
//    @BindView(R.id.etEnd)
//    EditText mEtEnd;
//    @BindView(R.id.recycler2)
//    RecyclerView mRecycler2;
//    @BindView(R.id.btn)
//    Button mBtn;
//
//    private List<String> mStartDatas = new ArrayList<>();
//    private List<String> mEndDatas = new ArrayList<>();
//    private SearchAdapter mAdapter;
//    private SearchAdapter mAdapter2;
//    private String mType;
//
//    private boolean isStartClick,isEndClick;
//
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_device_search;
//    }
//
//    @Override
//    protected void init() {
//        mBar.setTitle("设备SN码  批量搜索");
//        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popBackStack();
//            }
//        });
//
//        if (getArguments()!=null){
//            mType = getArguments().getString("type");
//        }
//
//        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecycler2.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        mAdapter = new SearchAdapter(mStartDatas);
//        mAdapter2 = new SearchAdapter(mEndDatas);
//        mRecycler.setAdapter(mAdapter);
//        mRecycler2.setAdapter(mAdapter2);
//
//        mEtStart.addTextChangedListener(new MyTextWatcher(true));
//        mEtEnd.addTextChangedListener(new MyTextWatcher(false));
//
//        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                mEtStart.setText(mAdapter.getData().get(position));
//                isStartClick=true;
//            }
//        });
//
//        mAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                mEtEnd.setText(mAdapter2.getData().get(position));
//                isEndClick=true;
//
//            }
//        });
//
//
//
//    }
//
//    private void search(String key, boolean start) {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("sn", key);
//        HttpUtil.getInstance(getActivity()).postForm(AppUrl.devieSearch, map, new HttpUtil.ResultCallback() {
//            @Override
//            public void onError(Request request, Exception e) {
//
//            }
//
//            @Override
//            public void onResponse(String s) throws IOException {
//                DeviceSearchBean bean = JSON.parseObject(s, DeviceSearchBean.class);
//
//                if (start){
//                    mAdapter.setNewData(bean.data);
//                }else{
//                    mAdapter2.setNewData(bean.data);
//                }
//
//
//            }
//        });
//    }
//
//
//
//
//    @OnClick(R.id.btn)
//    public void onViewClicked() {
//
//        String start=mEtStart.getText().toString();
//
//
//            start=mEtStart.getText().toString();
//
//        String end=mEtEnd.getText().toString();
//
//        if (TextUtils.isEmpty(start)||start.length()<10){
//            showToast("请选择或输入起始SN码");
//            return;
//        }
//
//        if (TextUtils.isEmpty(end)||start.length()<10){
//            showToast("请选择或输入截止SN码");
//            return;
//        }
//
//
//        DeviceSearchValueFragment fragment=new DeviceSearchValueFragment();
//        Bundle bundle=new Bundle();
//        bundle.putString("startline",start);
//        bundle.putString("endline",end);
//        bundle.putString("model",mType);
//        fragment.setArguments(bundle);
//        startFragment(fragment);
//
//    }
//
//
//
//    public class MyTextWatcher implements TextWatcher{
//        private boolean isStart;
//
//
//        public MyTextWatcher(boolean isStart) {
//            this.isStart = isStart;
//        }
//
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//            String content=s.toString();
//            if (!TextUtils.isEmpty(content)){
//
//                search(content,isStart);
//
//            }else{
//
//                if (isStart){
//                    isStartClick=false;
//                    mAdapter.setNewData(mStartDatas);
//                }else{
//                    isEndClick=false;
//                    mAdapter2.setNewData(mEndDatas);
//                }
//
//
//            }
//
//
//        }
//    }
//}
