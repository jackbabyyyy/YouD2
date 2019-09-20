package com.ud.share.ui.install;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.comm.InstallObjectEnum;
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

    private String mPid;
    private int mType;
    private String mName;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_install;
    }

    @Override
    protected void init() {

        mPid = getArguments().getString("pid");
        mName = getArguments().getString("name");
        int mType=getArguments().getInt("type");

        boolean isBusiness=mType== InstallObjectEnum.BUSINESS.getId();
        mBar.setTitle(mName);

        mBar.addLeftTextButton(isBusiness?"切换商户":"切换代理商",R.id.bar_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();

            }
        });
    }

    public static InstallFragment getInstance(String pid,String name,int type){
        Bundle bundle=new Bundle();
        bundle.putString("pid",pid);
        bundle.putInt("type",type);
        bundle.putString("name",name);
        InstallFragment installFragment=new InstallFragment();
        installFragment.setArguments(bundle);
        return  installFragment;
    }


    @OnClick({R.id.item, R.id.item2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item:
                startFragment( ScanFragment.getInstance(mPid,mType));
                break;
            case R.id.item2:
                startFragment( ChooseDeviceItemFragment2.getInstance(mPid,mType));

                break;
        }
    }
}
