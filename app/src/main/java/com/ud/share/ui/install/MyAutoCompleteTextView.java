package com.ud.share.ui.install;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

/**
 * Created by PP on 2019-07-26.
 */
public class MyAutoCompleteTextView extends AutoCompleteTextView {
    public MyAutoCompleteTextView(Context context) {
        super(context);
    }

    public MyAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        if (selStart == selEnd) { // 防止不能多选
            setSelection(getText().length()); // 保证光标始终在最后面
        }

    }
}
