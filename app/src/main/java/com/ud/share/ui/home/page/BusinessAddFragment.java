package com.ud.share.ui.home.page;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.custom.TimeSelectPicker;
import com.ud.share.event.FreshBusinessEvent;
import com.ud.share.map.MapActivity;
import com.ud.share.model.BaseJson;
import com.ud.share.model.ImgBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.utils.TimeCount;
import com.walkermanx.photopicker.PhotoPicker;

import org.greenrobot.eventbus.EventBus;
import org.jaaksi.pickerview.topbar.DefaultTopBar;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by PP on 2019/4/25.
 */
public class BusinessAddFragment extends BaseFragment {
    @BindView(R.id.t2)
    View t2;
    @BindView(R.id.head)
    ImageView mHead;

    @BindView(R.id.t1)
    View t1;
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

    @BindView(R.id.addr_detail)
    EditText mAddrDetail;
    @BindView(R.id.rate_line)
    EditText mRateLine;
    @BindView(R.id.rate_package)
    EditText mRatePackage;
    @BindView(R.id.line2)
    EditText mLine2;
    @BindView(R.id.line6)
    EditText mLine6;
    @BindView(R.id.line12)
    EditText mLine12;
    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.install)
    Button mInstall;

    @BindView(R.id.et_sms)
    EditText mEtSms;
    @BindView(R.id.tv_sms)
    TextView mTvSms;



    private String mArea;
    private String mHeadUrl = "";
    private String mLat;
    private String mLng;
    private String mAdcode;
//    private String mSns;
//    private String mModel;

    private TimeCount mCount;




    @Override
    protected int getLayoutId() {
        return R.layout.fragment_businnss_add;
    }

    @Override
    protected void init() {
//       int type=getArguments().getInt("type");

//       if (type==INSTALL){
//           mSns = getArguments().getString("sns");
//           mModel = getArguments().getString("model");
//
//           mBar.setTitle("铺货");
//           mBar.addRightTextButton("选择商户", R.id.topbar_right).setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                //   startFragment(ChooseBusinessFragment.getInstance(mSns,mModel));
//               }
//           });
//
//           mInstall.setVisibility(View.VISIBLE);
//
//       }else{


           mBar.setTitle("新增商户");
           mBar.addRightTextButton("确定", R.id.topbar_right).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                  installDevice();
               }
           });

           mInstall.setVisibility(View.GONE);
//       }

        mCount = new TimeCount(60000,1000,mTvSms,getActivity());


        ((TextView) t1.findViewById(R.id.section_name)).setText("商户图片");
        ((TextView) t2.findViewById(R.id.section_name)).setText("商户基础信息");
        //时间 地址不可手动编辑
        mTime.setFocusableInTouchMode(false);
        mAddr.setFocusableInTouchMode(false);
        mTime.setFocusable(false);
        mAddr.setFocusable(false);

        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        mTvSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSms();

            }
        });


    }

//    public static BusinessAddFragment getInstance(int type,String snsArray,String model){
//        Bundle bundle=new Bundle();
//        bundle.putInt("type",type);
//        bundle.putString("sns",snsArray);
//        bundle.putString("model",model);
//        BusinessAddFragment fragment=new BusinessAddFragment();
//        fragment.setArguments(bundle);
//        return fragment;
//    }

    //新增商户和下拨设备公用
    private void installDevice() {
        String name = mName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            showToast("请输入商户名称");
            return;
        }
        String contact = mContact.getText().toString();
        if (TextUtils.isEmpty(contact)) {
            showToast("请输入负责人实名");
            return;
        }
        String mobile = mMobile.getText().toString();
        if (TextUtils.isEmpty(mobile)) {
            showToast("请输入联系电话");
        }
        String shopStart = mTime.getText().toString().split("-")[0];
        if (TextUtils.isEmpty(shopStart)) {
            showToast("请设置营业时间");
        }
        String shopEnd = mTime.getText().toString().split("-")[1];
        String area = mArea;
        if (TextUtils.isEmpty(mArea)) {
            showToast("请设置商户地址");
        }
        String addr = mAddrDetail.getText().toString();
        String picture = mHeadUrl;
        if (TextUtils.isEmpty(picture)) {
            showToast("请选择商户门照图片");
        }
        String lng = mLng;
        String lat = mLat;
        String adcode = mAdcode;
        String lineRate = mRateLine.getText().toString();
        if (TextUtils.isEmpty(lineRate)) {
            showToast("请输入线充分润比例");
            return;
        }
        String packRate = mRatePackage.getText().toString();
        if (TextUtils.isEmpty(packRate)) {
            showToast("请输入充电宝分润比例");
            return;
        }
        String line2 = mLine2.getText().toString();
        if (TextUtils.isEmpty(line2)) {
            showToast("请输入线充2小时价格");
            return;
        }
        String line6 = mLine6.getText().toString();
        if (TextUtils.isEmpty(line6)) {
            showToast("请输入线充6小时价格");
            return;
        }
        String line12 = mLine12.getText().toString();
        if (TextUtils.isEmpty(line12)) {
            showToast("请输入线充12小时价格");
            return;
        }
        String smsCode=mEtSms.getText().toString().trim();
        if (TextUtils.isEmpty(smsCode)){
            showToast("请输入短信验证码");
            return;
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("contacts", contact);
        map.put("mobile", mobile);
        map.put("shopstart", shopStart);
        map.put("shopend", shopEnd);
        map.put("area", area);
        map.put("addr", addr);
        map.put("picture", picture);
        map.put("longitude", lng);
        map.put("latitude", lat);
        map.put("adcode", adcode);
        map.put("line_rate", lineRate);
        map.put("cabinet_rate", packRate);
        map.put("line_hour_2", line2);
        map.put("line_hour_6", line6);
        map.put("line_hour_12", line12);
        map.put("sms_code",smsCode);

//        if (!TextUtils.isEmpty(mModel)){
//            map.put("model",mModel);
//            map.put("sns",mSns);
//        }


        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.businessAdd, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }


                    @Override
                    public void onResponse(String s) throws IOException {
                        EventBus.getDefault().post(new FreshBusinessEvent());
                        BaseJson baseJson=JSON.parseObject(s,BaseJson.class);
                        showToast(baseJson.msg);
                        popBackStack();

                    }
                });

    }


    @OnClick({R.id.photo, R.id.iv_time, R.id.iv_addr,R.id.install})
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
                // 2018/2/5 03:14:11 - 2020-01-02 22:51:6
                Dialog pickerDialog = mTimePicker.getPickerDialog();
                pickerDialog.setCanceledOnTouchOutside(true);
                DefaultTopBar topBar = (DefaultTopBar) mTimePicker.getTopBar();
                topBar.getTitleView().setText("请选择时间");

                pickerDialog.show();


                break;


            case R.id.iv_addr:
                startActivityForResult(new Intent(getActivity(), MapActivity.class), 999);
                break;
            case R.id.install:

               installDevice();

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

    private void getBusinessImageChange(File file) {

        HttpUtil.getInstance(getActivity())
                .upload(false, AppUrl.businessImgUpload, file.getAbsolutePath(), file.getName(), new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }
                    @Override
                    public void onResponse(String s) throws IOException {

                        ImgBean bean = JSON.parseObject(s, ImgBean.class);
                        mHeadUrl = bean.data.picture;

                    }
                });
    }

    private void getSms() {

        String text=mMobile.getText().toString();
        if (TextUtils.isEmpty(text)&&text.length()!=11){
            showToast("请填写手机号码");
            return;
        }


        Map<String,String> map=new HashMap<>();
        map.put("phone",text);
        map.put("type","2");//2 商户
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.getDefSms, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {


                    }

                    @Override
                    public void onResponse(String response)  {
                        BaseJson baseJson= JSON.parseObject(response,BaseJson.class);
                        showToast(baseJson.msg);




                    }
                });

        mCount.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCount.cancel();
    }
}
