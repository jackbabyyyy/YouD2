package com.ud.share.ui.home.page7;

import android.view.View;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by PP on 2019/4/1.
 */
public class CheckTypeFragment extends BaseFragment {
    @BindView(R.id.type)
    TextView mType;
    @BindView(R.id.type2)
    TextView mType2;
    @BindView(R.id.bar)
    QMUITopBarLayout mBar;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_check_type;
    }

    @Override
    protected void init() {

        mBar.setTitle("指定设备弹出");
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });



    }

    @OnClick({R.id.type, R.id.type2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.type:

                startFragment(new CheckOneFragment());
                break;
            case R.id.type2:
                startFragment(new CheckAllFragment());
                break;
        }
    }
}
