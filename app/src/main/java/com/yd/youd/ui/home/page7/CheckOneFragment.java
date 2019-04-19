package com.yd.youd.ui.home.page7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by PP on 2019/4/1.
 */
public class CheckOneFragment extends BaseFragment {

    @BindView(R.id.section_name)
    TextView mName;
    @BindView(R.id.edit)
    EditText mEdit;
    @BindView(R.id.btn)
    Button mBtn;
    @BindView(R.id.bar)
    QMUITopBarLayout mBar;



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_check_one;
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
        mName.setText("设备弹出");


    }


    @OnClick({  R.id.btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {


            case R.id.btn:
                break;
        }
    }
}
