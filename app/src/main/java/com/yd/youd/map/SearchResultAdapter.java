package com.yd.youd.map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.yd.youd.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名： com.amap.searchdemo
 * <p>
 * 创建时间：2016/10/19
 * 项目名称：SearchDemo
 *
 * @author guibao.ggb
 * @email guibao.ggb@alibaba-inc.com
 * <p>
 * 类说明：
 */
public class SearchResultAdapter extends BaseAdapter {

    private List<PoiItem> data;
    private Context context;

    private int selectedPosition = 0;

    public SearchResultAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(List<PoiItem> data) {
        this.data = data;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.view_holder_result, parent, false);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.bindView(position);

        return convertView;
    }


    class ViewHolder {
        TextView textTitle;
        TextView textSubTitle;
        ImageView imageCheck;
        View root;

        public ViewHolder(View view) {
            root=view.findViewById(R.id.root);
            textTitle = (TextView) view.findViewById(R.id.text_title);
            textSubTitle = (TextView) view.findViewById(R.id.text_title_sub);
            imageCheck = (ImageView) view.findViewById(R.id.image_check);
        }

        public void bindView(int position) {
            if (position >= data.size())
                return;

            PoiItem poiItem = data.get(position);

            textTitle.setText(poiItem.getTitle());
            textSubTitle.setText(poiItem.getCityName() + poiItem.getAdName() + poiItem.getSnippet());


            root.setBackgroundResource(position==selectedPosition?R.drawable.map_check:R.drawable.map_uncheck);
            imageCheck.setImageResource(position==selectedPosition?R.mipmap.map_check:R.mipmap.map_uncheck);
            textTitle.setTextColor(position==selectedPosition?context.getResources().getColor(R.color.theme):context.getResources().getColor(R.color.color333));
            textSubTitle.setVisibility((position == 0 && poiItem.getPoiId().equals("regeo")) ? View.GONE : View.VISIBLE);
        }
    }
}
