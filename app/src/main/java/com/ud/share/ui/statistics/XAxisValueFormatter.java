package com.ud.share.ui.statistics;

import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.ud.share.utils.StringUtils;

/**
 * Created by PP on 2019-06-19.
 */
public class XAxisValueFormatter extends ValueFormatter {

    @Override
    public String getFormattedValue(float value) {
        return StringUtils.timeStamp2Date((long) value);
    }
}
