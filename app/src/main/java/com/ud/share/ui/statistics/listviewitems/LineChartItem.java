
package com.ud.share.ui.statistics.listviewitems;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.ud.share.R;
import com.ud.share.ui.statistics.XAxisValueFormatter;

import java.util.List;

public class LineChartItem extends ChartItem {

    private final Typeface mTf;
    private String mTitle;
    private String mContent;

    public LineChartItem(ChartData<?> cd, String title, String content, Context c) {
        super(cd);
        mTf = Typeface.createFromAsset(c.getAssets(), "OpenSans-Regular.ttf");
        this.mTitle = title;
        this.mContent = content;
    }

    @Override
    public int getItemType() {
        return TYPE_LINECHART;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, Context c) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(c).inflate(R.layout.list_item_linechart, null);
            holder.chart = convertView.findViewById(R.id.chart);
            holder.title = convertView.findViewById(R.id.title);
            holder.content = convertView.findViewById(R.id.content);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        //表头
        holder.title.setText(mTitle);
        holder.content.setText(mContent);


        // apply styling
        // holder.chart.setValueTypeface(mTf);
        holder.chart.getDescription().setEnabled(false);
        holder.chart.setDrawGridBackground(false);


        ValueFormatter valueFormatter = new XAxisValueFormatter();
        XAxis xAxis = holder.chart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setValueFormatter(valueFormatter);

        YAxis leftAxis = holder.chart.getAxisLeft();

        leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(5, false);

        leftAxis.setTextColor(c.getResources().getColor(R.color.theme2));
        leftAxis.setAxisMinimum(0.1f); // this replaces setStartAtZero(true)
        YAxis rightAxis = holder.chart.getAxisRight();
        rightAxis.setEnabled(false);
//        rightAxis.setTypeface(mTf);
//        rightAxis.setLabelCount(5, false);
//        rightAxis.setDrawGridLines(false);
//        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)


        Legend l = holder.chart.getLegend();
        l.setEnabled(false);


        // set data

        holder.chart.setData((LineData) mChartData);

        // do not forget to refresh the chart
        // holder.chart.invalidate();
        holder.chart.animateX(750);
        return convertView;
    }

    private static class ViewHolder {
        LineChart chart;
        TextView title;
        TextView content;
    }
}
