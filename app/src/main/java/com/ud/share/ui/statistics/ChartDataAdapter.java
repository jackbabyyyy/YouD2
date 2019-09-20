package com.ud.share.ui.statistics;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.ud.share.ui.statistics.listviewitems.ChartItem;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by PP on 2019-06-17.
 */
public class ChartDataAdapter extends ArrayAdapter<ChartItem> {

    ChartDataAdapter(Context context, List<ChartItem> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        //noinspection ConstantConditions
        return getItem(position).getView(position, convertView, getContext());
    }

    @Override
    public int getItemViewType(int position) {
        // return the views type
        ChartItem ci = getItem(position);
        return ci != null ? ci.getItemType() : 0;
    }

    @Override
    public int getViewTypeCount() {
        return 3; // we have 3 different item-types
    }

}
