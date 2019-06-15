package com.ud.share.ui.install;

import android.view.View;
import android.widget.LinearLayout;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.ui.home.page7.ScanFragment;

import butterknife.BindView;
import butterknife.OnClick;



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
                startFragment( ScanFragment.getInstance(ScanFragment.GO_INSTALL));
                break;
            case R.id.item2:
                startFragment(new ChooseDeviceFragment());
               // startFragment( ChooseBusinessFragment.getInstance(FROM_INSTALL_HAND_SELECT,""));
                break;
        }
    }
}
