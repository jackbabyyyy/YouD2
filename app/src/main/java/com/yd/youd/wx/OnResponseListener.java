package com.yd.youd.wx;

public interface OnResponseListener {

void onSuccess();
void onCancel();
void onFail(String message); }