package com.yd.youd.ui.install;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by PP on 2019/4/4.
 */
public class ChooseDeviceItemFragment extends BaseFragment {

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    private List<ChooseDeviceItem> mChooseDeviceItemLIst=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_choose_device_item;
    }

    @Override
    protected void init() {

        ChooseDeviceAdapter adapter=new ChooseDeviceAdapter(mChooseDeviceItemLIst);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);



    }
}
