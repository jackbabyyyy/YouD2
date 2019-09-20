package com.ud.share.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.ud.share.R;

public class TimeCount2 extends CountDownTimer {

    private TextView mTvSms;
    private Context mContext;

        public TimeCount2(long millisInFuture, long countDownInterval, TextView textView, Context context) {
            super(millisInFuture, countDownInterval);
            mTvSms=textView;
            mContext=context;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mTvSms.setClickable(false);

            mTvSms.setText(millisUntilFinished/1000+"s");

        }

        @Override
        public void onFinish() {
            mTvSms.setClickable(true);

            mTvSms.setText("重新发送");


        }



    }