package com.yd.youd.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.yd.youd.R;

public class TimeCount extends CountDownTimer {

    private TextView mTvSms;
    private Context mContext;

        public TimeCount(long millisInFuture, long countDownInterval, TextView textView, Context context) {
            super(millisInFuture, countDownInterval);
            mTvSms=textView;
            mContext=context;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mTvSms.setClickable(false);
            mTvSms.setBackground(mContext.getResources().getDrawable(R.drawable.btn_selector_unable));
            mTvSms.setText(millisUntilFinished/1000+"s");

        }

        @Override
        public void onFinish() {
            mTvSms.setClickable(true);
            mTvSms.setBackground(mContext.getResources().getDrawable(R.drawable.btn_selector));
            mTvSms.setText("获取验证码");


        }



    }