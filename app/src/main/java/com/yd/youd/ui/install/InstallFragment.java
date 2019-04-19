package com.yd.youd.ui.install;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by PP on 2019/3/5.
 */
public class InstallFragment extends BaseFragment {
    @BindView(R.id.bar)
    QMUITopBar mBar;
    @BindView(R.id.item)
    LinearLayout mItem;
    @BindView(R.id.item2)
    LinearLayout mItem2;
    Unbinder unbinder;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_install;
    }

    @Override
    protected void init() {
        mBar.setTitle("装机");


    }


    @OnClick({R.id.item, R.id.item2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item:
                break;
            case R.id.item2:
                break;
        }
    }
}
