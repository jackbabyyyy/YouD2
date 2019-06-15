package com.ud.share.ui.home.page;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.walkermanx.photopicker.PhotoPicker;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.custom.TimeSelectPicker;
import com.ud.share.map.MapActivity;
import com.ud.share.model.BaseJson;
import com.ud.share.model.BusinessDetailBean;
import com.ud.share.model.ImgBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;

import org.jaaksi.pickerview.topbar.DefaultTopBar;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class BusinessChangeFragment extends BaseFragment {


    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.name)
    EditText mName;
    @BindView(R.id.contact)
    EditText mContact;
    @BindView(R.id.mobile)
    EditText mMobile;
    @BindView(R.id.time)
    EditText mTime;
    @BindView(R.id.addr)
    EditText mAddr;
    @BindView(R.id.t1)
    View t1;
    @BindView(R.id.t2)
    View t2;
    @BindView(R.id.head)
    ImageView mHead;
    @BindView(R.id.photo)
    ImageView mPhoto;
    @BindView(R.id.addr_detail)
    EditText mAddrDetail;
    @BindView(R.id.rate_line)
    EditText mRateLine;
    @BindView(R.id.rate_package)
    EditText mRatePackage;
    private BusinessDetailBean mBusinessDetailBean;


    private String mArea;
    private String mHeadUrl ;
    private String mLat;
    private String mLng;
    private String mAdcode;
    private String mUploadUrl;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_business_change;
    }

    @Override
    protected void init() {
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        mBar.setTitle("修改商户信息");
        mBar.addRightTextButton("提交", R.id.bar_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getBusinessChange();
            }
        });


        ((TextView) t1.findViewById(R.id.section_name)).setText("商户图片");
        ((TextView) t2.findViewById(R.id.section_name)).setText("商户基础信息");
        //时间 地址不可手动编辑
        mTime.setFocusableInTouchMode(false);
        mAddr.setFocusableInTouchMode(false);
        mTime.setFocusable(false);
        mAddr.setFocusable(false);


        initDataView();


    }

    public static BusinessChangeFragment getInstance(String infoJson) {
        Bundle bundle = new Bundle();
        bundle.putString("info", infoJson);
        BusinessChangeFragment fragment = new BusinessChangeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private void initDataView() {
        String info = getArguments().getString("info");


        mBusinessDetailBean = JSON.parseObject(info, BusinessDetailBean.class);
        mName.setText(mBusinessDetailBean.data.seller.name);
        mContact.setText(mBusinessDetailBean.data.seller.contacts);
        mMobile.setText(mBusinessDetailBean.data.seller.mobile);
        mTime.setText(mBusinessDetailBean.data.seller.shopstart + "-" + mBusinessDetailBean.data.seller.shopend);
        mAddr.setText(mBusinessDetailBean.data.seller.area);
        mAddrDetail.setText(mBusinessDetailBean.data.seller.addr);
        mRateLine.setText(mBusinessDetailBean.data.seller.line_rate);
        mRatePackage.setText(mBusinessDetailBean.data.seller.cabinet_rate);
        mHeadUrl = mBusinessDetailBean.data.seller.picture;
        Glide.with(getActivity()).load(mHeadUrl).into(mHead);


    }

    private void getBusinessChange() {
        String sid = mBusinessDetailBean.data.seller.sid;
        String shop_name = mName.getText().toString().trim();
        String addr = mAddrDetail.getText().toString().trim();
        String picture = mUploadUrl;
        String longitude = mLng;
        String latitude = mLat;
        String contacts = mContact.getText().toString().trim();
        String mobile = mMobile.getText().toString().trim();
        String line = mRateLine.getText().toString().trim();
        String cabinet_rate = mRatePackage.getText().toString().trim();

//

        HashMap<String, String> map = new HashMap<>();
        map.put("sid", sid);
        if (!TextUtils.isEmpty(shop_name)) {
            map.put("shop_name", shop_name);
        }
        if (!TextUtils.isEmpty(addr)) {
            map.put("addr", addr);
        }
        if (!TextUtils.isEmpty(picture)) {
            map.put("picture", picture);
        }
        if (!TextUtils.isEmpty(longitude)) {
            map.put("adcode", mAdcode);
            map.put("area", mArea);
            map.put("latitude", latitude);
            map.put("longitude", longitude);
        }
        if (!TextUtils.isEmpty(contacts)) {
            map.put("contacts", contacts);
        }
        if (!TextUtils.isEmpty(mobile)) {
            map.put("mobile", mobile);
        }

//        map.put("per_price","");
//        map.put("per_ceiling","");
        if (!TextUtils.isEmpty(line)) {
            map.put("line_rate", line);

        }
        if (!TextUtils.isEmpty(cabinet_rate)) {
            map.put("cabinet_rate", cabinet_rate);
        }
        String shopStart = mTime.getText().toString().split("-")[0];
        String shopEnd = mTime.getText().toString().split("-")[1];
        if (!TextUtils.isEmpty(shopStart)){
            map.put("shopstart", shopStart);
            map.put("shopend", shopEnd);
        }





        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.businessChange, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String s) throws IOException {
                        BaseJson json = JSON.parseObject(s, BaseJson.class);
                        showToast(json.msg);
                        popBackStack();
                    }
                });
    }


    private void getBusinessImageChange(File file) {

        HttpUtil.getInstance(getActivity())
                .upload(false, AppUrl.businessImgUpload, file.getAbsolutePath(), file.getName(), new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String s) throws IOException {

                        ImgBean bean = JSON.parseObject(s, ImgBean.class);
                        mUploadUrl = bean.data.picture;

                    }
                });
    }

    @OnClick({R.id.photo, R.id.iv_time, R.id.iv_addr})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.photo:
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setPreviewEnabled(false)
                        .setCrop(true)
                        .setCropXY(1, 1)
                        .start(getActivity(), this);
                break;

            case R.id.iv_time:

                TimeSelectPicker mTimePicker = new TimeSelectPicker.Builder(getActivity(), TimeSelectPicker.TYPE_TIME, new TimeSelectPicker.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(TimeSelectPicker picker, Date date, Date datestart) {
//                        Toast.makeText(MainActivity.this, picker.last + "", Toast.LENGTH_SHORT).show();
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                        String t1 = format.format(date);
                        String t2 = format.format(datestart);

                        mTime.setText(t1 + "-" + t2);
                    }
                })
                        // 设置不包含超出的结束时间<=
                        .setContainsEndDate(false)
                        // 设置时间间隔为30分钟
                        .setTimeMinuteOffset(30)
                        .setRangDate(1556294400000L, 1556380799000L)
                        .create();
                // 2018/2/5 03:14:11 - 2020/1/2 22:51:6
                Dialog pickerDialog = mTimePicker.getPickerDialog();
                pickerDialog.setCanceledOnTouchOutside(true);
                DefaultTopBar topBar = (DefaultTopBar) mTimePicker.getTopBar();
                topBar.getTitleView().setText("请选择时间");
                pickerDialog.show();


                break;


            case R.id.iv_addr:
                startActivityForResult(new Intent(getActivity(), MapActivity.class), 999);
                break;

        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 999 && resultCode == 999) {
            mLat = data.getStringExtra("lat");
            mLng = data.getStringExtra("lng");
            mAdcode = data.getStringExtra("adcode");
            mArea = data.getStringExtra("area");
            mAddr.setText(mArea);
            return;
        }

        if (null != data) {
            String path = data.getStringExtra(PhotoPicker.KEY_CAMEAR_PATH);
            Glide.with(getActivity()).load(Uri.fromFile(new File(path))).into(mHead);
            upload(path);
        }
    }

    private void upload(String path) {
        Luban.with(getActivity())
                .load(Uri.fromFile(new File(path))).ignoreBy(50).filter(new CompressionPredicate() {
            @Override
            public boolean apply(String path) {
                return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
            }
        })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File file) {
                        getBusinessImageChange(file);


                    }

                    @Override
                    public void onError(Throwable e) {


                    }
                }).launch();


    }


}
