package com.ud.share.ui.extend;

import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.utils.AppData;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

/**
 * Created by PP on 2019/4/22.
 */
public class QrCodeFragment extends BaseFragment {
    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.qr)
    ImageView mQr;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_qrcode
                ;
    }

    @Override
    protected void init() {
        mBar.setTitle("二维码图片链接");
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
        String qrlink= AppData.INVITE_LINK;
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap= QRCodeEncoder.syncEncodeQRCode(qrlink,108);
                Glide.with(getActivity()).load(bitmap).into(mQr);
            }
        });





    }


}
