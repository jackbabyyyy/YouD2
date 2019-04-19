package com.yd.youd.ui.home.page7;

import android.os.Vibrator;
import android.util.Log;

import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Created by PP on 2019/3/12.
 */
public class DeviceCheckFragment extends BaseFragment implements QRCodeView.Delegate {
    private static final String TAG=DeviceCheckFragment.class.getSimpleName();
    @BindView(R.id.zxingview)
    ZXingView mZXingView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_check;
    }

    @Override
    protected void init() {
        mZXingView.setDelegate(this);

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
        Log.d(TAG, "result:" + result);

        vibrate();

//        mZXingView.startSpot(); // 开始识别

        //
        startFragment(new CheckTypeFragment());
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
        Vibrator vibrator = (Vibrator)getActivity(). getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
}
