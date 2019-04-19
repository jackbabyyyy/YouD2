package com.yd.youd.ui.extend;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;
import com.yd.youd.utils.AppData;
import com.yd.youd.web.WebFragment;
import com.yd.youd.wx.OnResponseListener;
import com.yd.youd.wx.WXshare;

import butterknife.BindView;

/**
 * Created by PP on 2019/3/5.
 */
public class ExtendFragment extends BaseFragment {

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.bar)
    QMUITopBar mQMUITopBar;

    private WXshare mWXshare;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_extend;
    }

    @Override
    protected void init() {
        mQMUITopBar.setTitle("展业");

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
               if (parent.getChildAdapterPosition(view)<2){
                   outRect.top= QMUIDisplayHelper.dp2px(getActivity(),30);
               }
            }
        });

        mWXshare = new WXshare(getActivity());
        mWXshare.register();
        mWXshare.setListener(new OnResponseListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onFail(String message) {

            }
        });


        ExtendAdapter extendAdapter=new ExtendAdapter(AppData.getExtend());
        mRecyclerView.setAdapter(extendAdapter);

        extendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        startFragment(new WordFragment());
                        break;
                    case 2:
                        mWXshare.shareUrl(0,getActivity(),"http","幽电","悠悠一会就来电");
                        break;
                    case 3:
                        startFragment(WebFragment.getInstance(AppData.getH5().data.tutorial));
                        break;
                }

            }
        });



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWXshare.unregister();
    }
}
