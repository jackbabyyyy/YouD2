package com.ud.share.ui.statistics;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.model.DotBean;
import com.ud.share.model.StaticsBean;
import com.ud.share.model.StaticsBean2;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.ui.statistics.listviewitems.ChartItem;
import com.ud.share.ui.statistics.listviewitems.LineChartItem;
import com.ud.share.utils.StringUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * Created by PP on 2019/6/15.
 */
public class StaItem2Fragment extends BaseFragment {

    private static final String TAG = StaItemFragment.class.getSimpleName();

    @BindView(R.id.listView1)
    ListView mListview;
    @BindView(R.id.flow)
    TagFlowLayout mFlowLayout;
    @BindView(R.id.start)
    TextView mStart;
    @BindView(R.id.end)
    TextView mEnd;
    @BindView(R.id.search)
    TextView mSearch;
    @BindView(R.id.search2)
    TextView mSearch2;

    @BindView(R.id.searchName)
    TextView mSearchName;
    private ArrayList<ChartItem> mChartItems;
    private ChartDataAdapter mMAdapter;
    private int mType = 1;
    private String mStartData;
    private String mEndData;

    private String id="";
    private String type="";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sta_item2;
    }

    @OnClick({R.id.start, R.id.end, R.id.search,R.id.searchName,R.id.search2})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:

                TimePickerView pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {


                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        mStart.setText(df.format(date));


                    }
                }).build();

                pvTime.show();
                break;

            case R.id.end:
                TimePickerView pvTime2 = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {

                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        mEnd.setText(df.format(date));


                    }
                }).build();

                pvTime2.show();
                break;

            case R.id.search:
                getData(1);
                break;

            case R.id.searchName:
                startFragmentForResult(new SearchOneFragment(),1);
                break;

            case R.id.search2:
                getData(2);
                break;

        }
    }


    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Intent data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode==1){
            if (data!=null){
                mSearchName.setText(data.getStringExtra("keyword"));
                id=data.getStringExtra("id");
                type=data.getStringExtra("type");
                getData(2);
            }

        }
    }

    @Override
    protected void init() {


        mSearch2.setVisibility(View.GONE);

        List<String> labels = new ArrayList<>();
        labels.add("本周");
        labels.add("上周");
        labels.add("本月");
        labels.add("上月");
        TagAdapter tagAdapter = new TagAdapter<String>(labels) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.tv, mFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        };


        mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                mType = position + 1;
                getData(0);
                return false;
            }
        });


        tagAdapter.setSelectedList(0);
        mFlowLayout.setAdapter(tagAdapter);


        mChartItems = new ArrayList<>();

        String s="{\"code\":1,\"msg\":\"成功\",\"data\":{\"analysis_data\":{\"member\":[{\"date\":\"2019-06-01\",\"number\":\"500\"}],\"seller\":[{\"date\":\"2019-06-01\",\"number\":\"555\"}],\"agent\":[{\"date\":\"2019-06-01\",\"number\":\"333\"}]},\"total\":{\"member\":\"0\",\"seller\":\"0\",\"agent\":\"0\"}}}";
        StaticsBean bean = JSON.parseObject(s, StaticsBean.class);
        mChartItems.add(new LineChartItem(generateDataLine(bean.data.analysis_data.member), "用户增长", "统计" + bean.data.total.member + "人", getActivity()));
        mChartItems.add(new LineChartItem(generateDataLine(bean.data.analysis_data.seller), "商户增长", "统计" + bean.data.total.seller + "户", getActivity()));

        mMAdapter = new ChartDataAdapter(getActivity(), mChartItems);
        mListview.setAdapter(mMAdapter);

        getData(0);

    }


    private LineData generateDataLine(List<DotBean> list) {
        ArrayList<Entry> values1 = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            values1.add(new Entry((float) (StringUtils.date2TimeStamp(list.get(i).date)), Float.parseFloat(list.get(i).number)));
        }

        LineDataSet d1 = new LineDataSet(values1, "");

        d1.setLineWidth(2.0f);
        d1.setCircleRadius(2.5f);
        d1.setCircleColor(getActivity().getResources().getColor(R.color.theme2));
        d1.setColor(getActivity().getResources().getColor(R.color.theme2));
        d1.setHighLightColor(getActivity().getResources().getColor(R.color.theme2));
        d1.setValueTextColor(getActivity().getResources().getColor(R.color.color333));
        d1.setValueTextSize(9f);

        d1.setDrawValues(true);
        ArrayList<ILineDataSet> sets = new ArrayList<>();
        sets.add(d1);

        return new LineData(sets);
    }

    private void getData(int  clicklType) {

        mStartData = TextUtils.isEmpty(mStart.getText().toString()) ? "" : mStart.getText().toString();
        mEndData = TextUtils.isEmpty(mEnd.getText().toString()) ? "" : mEnd.getText().toString();

        String url=null;
        if (clicklType==0){
            if (TextUtils.isEmpty(id)){
                url= AppUrl.statics2 + "?type=" + mType;
            }else{
                url=AppUrl.statics2 + "?type=" + mType+"&user_id="+id+"&user_type="+type;
            }
        }else if (clicklType==1){
            if (TextUtils.isEmpty(mStartData)||TextUtils.isEmpty(mEndData)){
                showToast("请选择日期");
            }else{
                if (TextUtils.isEmpty(id)){
                    url=AppUrl.statics2 + "?start_data=" + mStartData + "&end_data=" + mEndData;
                }else{
                    url=AppUrl.statics2 + "?start_data=" + mStartData + "&end_data=" + mEndData+"&user_id="+id+"&user_type="+type;
                }
            }
        }else if (clicklType==2){
            if (TextUtils.isEmpty(id)){
                showToast("请输入代理商或商户");
            }else {
                if (TextUtils.isEmpty(mStartData)){
                    url=AppUrl.statics2 + "?type=" + mType+"&user_id="+id+"&user_type="+type;
                }else {
                    url=AppUrl.statics2 + "?start_data=" + mStartData + "&end_data=" + mEndData+"&user_id="+id+"&user_type="+type;
                }

            }

        }

        if (url==null)return;



        HttpUtil.getInstance(getActivity()).getAsynHttp(url, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String s) throws IOException {
                Log.d(TAG, "onResponse: " + s);

                StaticsBean2 bean = JSON.parseObject(s, StaticsBean2.class);
                mChartItems.clear();

                mChartItems.add(new LineChartItem(generateDataLine(bean.data.analysis_data.income), "收益明细", "统计" + bean.data.total.income + "元", getActivity()));
                mChartItems.add(new LineChartItem(generateDataLine(bean.data.analysis_data.withdraw), "提现", "统计" + bean.data.total.withdraw + "元", getActivity()));

                mMAdapter.notifyDataSetChanged();


            }
        });

    }


}
