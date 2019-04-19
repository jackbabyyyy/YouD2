package com.yd.youd.ui.extend;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by PP on 2019/4/10.
 */
public class WordFragment extends BaseFragment {

    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    private List<WordItem> mWordItems=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_word;
    }

    @Override
    protected void init() {
        mBar.setTitle("文案库");
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        WordAdapter adapter=new WordAdapter(mWordItems);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.saveIV:
                        break;
                    case R.id.saveText:
                        break;
                }
            }
        });


    }
}
