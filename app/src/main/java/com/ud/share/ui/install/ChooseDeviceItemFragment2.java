package com.ud.share.ui.install;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.comm.InstallObjectEnum;
import com.ud.share.event.Event;
import com.ud.share.event.SearchEvent;
import com.ud.share.model.BaseJson;
import com.ud.share.model.DeviceListBean;
import com.ud.share.model.DeviceSearchBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.ui.home.page.BusinessAddFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    @BindView(R.id.start)
    AutoCompleteTextView mStart;
    @BindView(R.id.end)
    MyAutoCompleteTextView mEnd;

    @BindView(R.id.tvAll)
    TextView mTvAll;
    @BindView(R.id.tvCheck)
    TextView mTvCheck;
    @BindView(R.id.btnAll)
    TextView mBtnAll;


    private List<String> mChooseDeviceItemLIst = new ArrayList<>();

    public ChooseDeviceAdapter2 mAdapter;
    private String mSid;
    private int mType;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_choose_device_item2;
    }

    @Override
    protected void init() {

        mSid = getArguments().getString("sid");
        mType=getArguments().getInt("type");

        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
        mBar.setTitle("设备分配");

        initCompeteEditText();

        mAdapter = new ChooseDeviceAdapter2(mChooseDeviceItemLIst);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            int space = QMUIDisplayHelper.dp2px(getActivity(), 10);

            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int pos = parent.getChildAdapterPosition(view);
                if (pos % 3 == 0) {
                    outRect.left = space;
                    outRect.right = space / 2;
                    outRect.bottom = space;
                } else if (pos % 3 == 1) {
                    outRect.left = space / 2;
                    outRect.right = space / 2;
                    outRect.bottom = space;
                } else if (pos % 3 == 2) {
                    outRect.left = space / 2;
                    outRect.right = space;
                    outRect.bottom = space;
                }

            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                boolean check = mAdapter.mChecks.contains(mAdapter.getData().get(position));


                if (check) {
                    mAdapter.mChecks.remove(mAdapter.getData().get(position));
                } else {
                    mAdapter.mChecks.add(mAdapter.getData().get(position));

                }
                mTvCheck.setText("选中    " + mAdapter.mChecks.size());
                mAdapter.notifyItemChanged(position);


            }
        });

//        getData();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        }, 150);

    }

    private void initCompeteEditText() {
        mStart.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                search(s.toString(), 1);
            }
        });

        mEnd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                search(s.toString(), 2);
            }
        });

        mStart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) mStart.getAdapter().getItem(position);
                mEnd.setText(s.substring(0, 6));


            }
        });


    }

    public static ChooseDeviceItemFragment2 getInstance(String sid,int type) {
        Bundle bundle = new Bundle();
        bundle.putString("sid", sid);
        bundle.putInt("type",type);
        ChooseDeviceItemFragment2 fragment = new ChooseDeviceItemFragment2();
        fragment.setArguments(bundle);
        return fragment;
    }


    private void getData() {
        String start = mStart.getText().toString().trim();
        String end = mEnd.getText().toString().trim();
        HashMap<String, String> map = new HashMap<>();
        if (!TextUtils.isEmpty(start)) {
            map.put("startline", start);
        }
        if (!TextUtils.isEmpty(end)) {
            map.put("endline", end);
        }


        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.deviceList, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {
                        DeviceListBean bean = JSON.parseObject(response, DeviceListBean.class);


                        //

                        mAdapter.setNewData(bean.data.data);

                        mTvAll.setText("总计    " + bean.data.total);
                    }
                });


    }

    @OnClick({R.id.btn, R.id.btnAll, R.id.search})
    public void onClick(View view) {


        switch (view.getId()) {


            case R.id.btnAll:


                if (mBtnAll.getText().toString().equals("全选")) {
                    mAdapter.mChecks.addAll(mAdapter.getData());
                    mAdapter.notifyDataSetChanged();
                    mBtnAll.setText("取消");
                    mTvCheck.setText("选中    " + mAdapter.getData().size());
                } else {
                    mAdapter.mChecks.clear();
                    mAdapter.notifyDataSetChanged();
                    mBtnAll.setText("全选");
                    mTvCheck.setText("选中    " + 0);

                }


                break;


            case R.id.btn:


                //sns
                String[] listArray = new String[mAdapter.mChecks.size()];
                for (int i = 0; i < mAdapter.mChecks.size(); i++) {
                    listArray[i] = mAdapter.mChecks.get(i);
                }
                try {
                    String sns = Arrays.toString(listArray).replace("[", "").replace("]", "").replace(" ", "");

                    //Log.d("chen", "onClick: "+sns);
                    String snsEncode = URLEncoder.encode(sns, "UTF-8");
                    getInstall(snsEncode);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.search:

                getData();
                //清空选择状态
                if (mAdapter.mChecks.size()!=0){
                    mAdapter.mChecks.clear();
                    mAdapter.setNewData(mAdapter.getData());
                    mTvCheck.setText("选中    " + 0);
                }

//                DeviceSearchFragment fragment=new DeviceSearchFragment();
//                Bundle bundle=new Bundle();
//              //  bundle.putString("type",type);
//                fragment.setArguments(bundle);
//                startFragment(fragment);
                break;
        }

    }


    public void search(String key, int type) {
        HashMap<String, String> map = new HashMap<>();
        map.put("sn", key);
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.devieSearch, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String s) throws IOException {
                DeviceSearchBean bean = JSON.parseObject(s, DeviceSearchBean.class);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.route_inputs_device, bean.data);

                if (type == 1) {
                    mStart.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    mEnd.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }






            }
        });
    }


    private void getInstall(String sns) {
        HashMap<String, String> map = new HashMap<>();
        String url=null;
        if (mType== InstallObjectEnum.BUSINESS.getId()){
            map.put("sid", mSid);
            url=AppUrl.bind;
        }else{
            map.put("aid",mSid);
            url=AppUrl.bindProxy;
        }

        map.put("sns", sns);



        HttpUtil.getInstance(getActivity()).postForm(url, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String s) throws IOException {
                BaseJson json = JSON.parseObject(s, BaseJson.class);
                if (json.code == 1) {
                    popBackStack();
                }
                showToast(json.msg);

            }
        });
    }


}
