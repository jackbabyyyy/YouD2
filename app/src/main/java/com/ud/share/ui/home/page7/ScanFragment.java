package com.ud.share.ui.home.page7;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.comm.InstallObjectEnum;
import com.ud.share.model.BaseJson;
import com.ud.share.model.IsDeviceBindBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.ui.home.page.BusinessAddFragment;
import com.ud.share.ui.login.LoginActivity;
import com.ud.share.utils.SP;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import okhttp3.Request;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Created by PP on 2019/3/12.
 */
public class ScanFragment extends BaseFragment implements QRCodeView.Delegate {
    private static final String TAG = ScanFragment.class.getSimpleName();
    @BindView(R.id.zxingview)
    ZXingView mZXingView;
    @BindView(R.id.bar)
    QMUITopBarLayout mBar;

    private String mSid;
    private int mType;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_check;
    }

    @Override
    protected void init() {
        mType = getArguments().getInt("type");
        mSid = getArguments().getString("sid");
        mZXingView.setDelegate(this);
        mBar.setTitle("扫描二维码");
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

    }

    @Override
    public void onStart() {
        mZXingView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStart();

        mZXingView.startCamera();
        mZXingView.startSpotAndShowRect();
    }

    @Override
    public void onDestroy() {
        mZXingView.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {


        vibrate();

//        mZXingView.startSpot(); // 开始识别

//        //TODO 设备检测
//        if (mGo==GO_CHECK){
//            startFragmentAndDestroyCurrent(new CheckTypeFragment());
//        }else{


        isBind(result);


    }


    public static ScanFragment getInstance(String sid, int type) {
        Bundle bundle = new Bundle();
        bundle.putString("sid", sid);
        bundle.putInt("type", type);
        ScanFragment fragment = new ScanFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

        // 这里是通过修改提示文案来展示环境是否过暗的状态，接入方也可以根据 isDark 的值来实现其他交互效果
        String tipText = mZXingView.getScanBoxView().getTipText();
        String ambientBrightnessTip = "\n环境过暗，请打开闪光灯";
        if (isDark) {
            if (!tipText.contains(ambientBrightnessTip)) {
                mZXingView.getScanBoxView().setTipText(tipText + ambientBrightnessTip);
            }
        } else {
            if (tipText.contains(ambientBrightnessTip)) {
                tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip));
                mZXingView.getScanBoxView().setTipText(tipText);
            }
        }

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.d(TAG, "打开相机出错");
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }


    private void isBind(String sn) {
        HashMap<String, String> map = new HashMap<>();
        map.put("sn", sn);
        HttpUtil.getInstance(getActivity()).postForm(AppUrl.isDeviceBind, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String s) throws IOException {
                IsDeviceBindBean json = JSON.parseObject(s, IsDeviceBindBean.class);
                if (json.data.is_bind.contains("true")) {
                    //解绑
                    showUnBindDialog(sn);
                } else {
                    //绑定
                    String snsEncode = URLEncoder.encode(sn, "UTF-8");
                    getInstall(snsEncode);


                }


            }
        });

    }


    private void showUnBindDialog(String sn) {

        new QMUIDialog.MessageDialogBuilder(getActivity())
                .setMessage("该设备已经绑定，是否解绑？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })

                .addAction(0, "解绑", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        //解绑操作

                        unbindDevice(sn);


                    }
                })
                .create(com.qmuiteam.qmui.R.style.QMUI_Dialog).show();


    }

    private void unbindDevice(String sn) {
        HashMap<String, String> map = new HashMap<>();
        map.put("sns", sn);

        HttpUtil.getInstance(getActivity()).postForm(AppUrl.bindDevice, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String s) throws IOException {
                BaseJson json = JSON.parseObject(s, BaseJson.class);
                showToast(json.msg);

            }
        });

    }


    private void getInstall(String sns) {
        HashMap<String, String> map = new HashMap<>();

        String url=null;
        if (mType== InstallObjectEnum.BUSINESS.getId()){
            map.put("sid", mSid);
            url=AppUrl.bind;
        }else{
            map.put("aid",mSid);
            url=AppUrl.bindProxy;
        }
        map.put("sns", sns);


        HttpUtil.getInstance(getActivity()).postForm(url, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String s) throws IOException {
                BaseJson json = JSON.parseObject(s, BaseJson.class);
                if (json.code == 1) {
                    popBackStack();
                }
                showToast(json.msg);

            }
        });
    }


}
