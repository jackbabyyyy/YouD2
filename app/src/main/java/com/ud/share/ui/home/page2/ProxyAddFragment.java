//package com.ud.share.ui.home.page2;
//
//import android.graphics.Rect;
//import android.os.Handler;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.EditText;
//
//import com.alibaba.fastjson.JSON;
//import com.qmuiteam.qmui.util.QMUIDisplayHelper;
//import com.qmuiteam.qmui.widget.QMUITopBarLayout;
//import com.ud.share.R;
//import com.ud.share.base.BaseFragment;
//import com.ud.share.model.BaseJson;
//import com.ud.share.net.AppUrl;
//import com.ud.share.net.HttpUtil;
//import com.ud.share.utils.AppData;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import butterknife.BindView;
//import okhttp3.Request;
//
///**
// * Created by PP on 2019/3/13.
// */
//public class ProxyAddFragment extends BaseFragment  {
//
//
//    @BindView(R.id.bar)
//    QMUITopBarLayout mBar;
//    @BindView(R.id.recycler)
//    RecyclerView mRecycler;
//
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_proxy_add;
//    }
//
//    @Override
//    protected void init() {
//
//        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popBackStack();
//            }
//        });
//        mBar.setTitle("添加下级代理");
//        mBar.addRightTextButton("提交",R.id.topbar_right).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getAgentAdd();
//            }
//        });
//        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//                super.getItemOffsets(outRect, view, parent, state);
//                if (parent.getChildAdapterPosition(view)==0){
//                    outRect.top=QMUIDisplayHelper.dp2px(getActivity(),25);
//                }
//                outRect.bottom= QMUIDisplayHelper.dp2px(getActivity(),25);
//            }
//        });
//
//        ProxyAddAdapter adapter=new ProxyAddAdapter(AppData.getInitAgentInfo());
//        mRecycler.setAdapter(adapter);
//
//
//
//
//
//    }
//
//
//
//
//    private void getAgentAdd() {
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                String name=((EditText) mRecycler.getLayoutManager().findViewByPosition(0).findViewById(R.id.content)).getText().toString();
//                String phone=((EditText) mRecycler.getLayoutManager().findViewByPosition(1).findViewById(R.id.content)).getText().toString();
//                String line=((EditText) mRecycler.getLayoutManager().findViewByPosition(2).findViewById(R.id.content)).getText().toString();
//                String cabinet=((EditText) mRecycler.getLayoutManager().findViewByPosition(3).findViewById(R.id.content)).getText().toString();
//                String deposit=((EditText) mRecycler.getLayoutManager().findViewByPosition(4).findViewById(R.id.content)).getText().toString();
//
//                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(line)||TextUtils.isEmpty(cabinet)||TextUtils.isEmpty(deposit)){
//                    showToast("请将资料填写完整");
//                    return;
//                }
//
//                Map<String,String> map=new HashMap<>();
//                map.put("real_name",name);
//                map.put("phone",phone);
//                map.put("line_rate",line);
//                map.put("cabinet_rate",cabinet);
//                map.put("deposit",deposit);
//                HttpUtil.getInstance(getActivity())
//                        .postForm(AppUrl.proxyAdd, map, new HttpUtil.ResultCallback() {
//                            @Override
//                            public void onError(Request request, Exception e) {
//
//                            }
//
//                            @Override
//                            public void onResponse(String response) throws IOException {
//                                BaseJson json= JSON.parseObject(response,BaseJson.class);
//                                showToast(json.msg);
//                                popBackStack();
//
//
//                            }
//                        });
//            }
//        },100);
//
//
//
//    }
//}
