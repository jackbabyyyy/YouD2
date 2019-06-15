package com.ud.share.ui.extend;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.utils.AppData;
import com.ud.share.web.WebFragment;

import com.ud.share.wxapi.WXTools;

import butterknife.BindView;

/**
 * Created by PP on 2019/3/5.
 */
public class ExtendFragment extends BaseFragment {

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.bar)
    QMUITopBar mQMUITopBar;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_extend;
    }

    @Override
    protected void init() {
        mQMUITopBar.setTitle("幽电分享");
        mQMUITopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
               if (parent.getChildAdapterPosition(view)<2){
                   outRect.top= QMUIDisplayHelper.dp2px(getActivity(),30);
               }
            }
        });


        ExtendAdapter extendAdapter=new ExtendAdapter(AppData.getExtend());
        mRecyclerView.setAdapter(extendAdapter);

        extendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        startFragment(new QrCodeFragment());
                        break;
                    case 1:
                        showTODO();
                   //     startFragment(new WordFragment());
                        break;
                    case 2:

                        WXTools wxTools=new WXTools(getActivity());
                        wxTools.shareUrl(true,AppData.INVITE_LINK,"幽电","悠悠一会就来电");

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

    }


}
