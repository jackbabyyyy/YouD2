package com.yd.youd.ui.home.page;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.walkermanx.photopicker.PhotoPicker;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;
import com.yd.youd.model.BaseJson;
import com.yd.youd.model.ImgBean;
import com.yd.youd.net.AppUrl;
import com.yd.youd.net.HttpUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
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
    private String mHeadUrl="";

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



    }


    private void getBusinessChange(){
        HashMap<String ,String > map=new HashMap<>();
        map.put("sid","");
        map.put("shop_name","");
        map.put("addr","");
        map.put("picture","");
        map.put("longitude","");
        map.put("latitude","");
        map.put("per_price","");
        map.put("per_ceiling","");
        map.put("contacts","");
        map.put("mobile","");

        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.businessChange, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }
                    @Override
                    public void onResponse(String s) throws IOException {

                    }
                });
    }


    private void getBusinessImageChange(File file){

        HttpUtil.getInstance(getActivity())
                .upload(false, AppUrl.businessImgUpload, file.getAbsolutePath(), file.getName(), new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String s) throws IOException {
                        ImgBean bean= JSON.parseObject(s,ImgBean.class);
                        mHeadUrl = bean.data.url;

                    }
                });
    }

    @OnClick(R.id.photo)
    public void onClick(){
        PhotoPicker.builder()
                .setPhotoCount(1)
                .setPreviewEnabled(false)
                .setCrop(true)
                .setCropXY(1, 1)
                .start(getActivity(), this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

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
                        Log.d("chen", "onSuccess: " + file.getAbsolutePath() + "/--/" + file.getName());
                        getBusinessImageChange(file);


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("chen", "onError: " + e.getMessage());

                    }
                }).launch();


    }





}
