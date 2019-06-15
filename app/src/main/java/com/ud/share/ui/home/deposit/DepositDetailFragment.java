package com.ud.share.ui.home.deposit;

import android.view.View;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by PP on 2019/4/3.
 */
public class DepositDetailFragment extends BaseFragment {
    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.section_name)
    TextView mSectionName;
    @BindView(R.id.iv)
    CircleImageView mIv;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.status)
    TextView mStatus;
    @BindView(R.id.code)
    TextView mCode;
    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.type)
    TextView mType;
    @BindView(R.id.money)
    TextView mMoney;
    @BindView(R.id.balance)
    TextView mBalance;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_deposit_detail
                ;
    }


    @Override
    protected void init() {
        mBar.setTitle("押金详情");
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        mSectionName.setText("押金编号+");

    }


}
