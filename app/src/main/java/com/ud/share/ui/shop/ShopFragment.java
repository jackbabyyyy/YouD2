package com.ud.share.ui.shop;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by PP on 2019/5/28.
 */
public class ShopFragment extends BaseFragment {

    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    private List<ShopItem> mShopItems=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop
                ;
    }

    @Override
    protected boolean canDragBack() {
        return false;
    }

    @Override
    protected void init() {
        mBar.setTitle("幽电-商城");

        mShopItems.add(new ShopItem(R.mipmap.shop1,"幽电GA06共享充电柜"));
        mShopItems.add(new ShopItem(R.mipmap.shop2,"幽电XA05共享充电线"));

        ShopAdapter adapter=new ShopAdapter(mShopItems);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom= QMUIDisplayHelper.dp2px(getActivity(),25);
            }
        });



    }
}
