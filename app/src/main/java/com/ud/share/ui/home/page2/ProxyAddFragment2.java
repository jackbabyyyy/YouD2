package com.ud.share.ui.home.page2;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.event.FreshProxyEvent;
import com.ud.share.model.BaseJson;
import com.ud.share.model.ImgBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.utils.TimeCount;
import com.walkermanx.photopicker.PhotoPicker;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Request;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by PP on 2019/3/13.
 */
public class ProxyAddFragment2 extends BaseFragment {


    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.nick)
    EditText mNick;
    @BindView(R.id.name)
    EditText mName;
    @BindView(R.id.phone)
    EditText mPhone;
    @BindView(R.id.et_sms)
    EditText mEtSms;
    @BindView(R.id.tv_sms)
    TextView mTvSms;
    @BindView(R.id.imageList)
    RecyclerView mImageList;

    @BindView(R.id.line)
    EditText mLine;

    @BindView(R.id.freeze)
    EditText mFreeze;
    @BindView(R.id.id_no)
    EditText mEtId;

    private ProxyImgAdapter mAdapter;
    private List<String> mImgPaths;
    private TimeCount mCount;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_proxy_add2;
    }

    @Override
    protected void init() {

        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
        mBar.setTitle("添加下级代理");
        mBar.addRightTextButton("提交", R.id.topbar_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAgentAdd();
            }
        });

        mCount = new TimeCount(60000, 1000, mTvSms, getActivity());

        //
        mImgPaths = new ArrayList<>();
        mImgPaths.add("");
        mAdapter = new ProxyImgAdapter(mImgPaths);
        mImageList.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mImageList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setPreviewEnabled(false)
                        .setCrop(true)
                        .setCropXY(1, 1)
                        .start(getActivity(), ProxyAddFragment2.this, position);

            }
        });

        mTvSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSms();
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (null != data) {
            String path = data.getStringExtra(PhotoPicker.KEY_CAMEAR_PATH);


            upload(path, requestCode);
        }
    }

    private void freshImgDatas(int index, String path, String url) {
        mImgPaths.set(index, path);
        if (mImgPaths.size() < 3) {
            mImgPaths.add("");
        }
        mAdapter.setNewData(mImgPaths);
        mAdapter.imgArray[index] = url;

    }

    private QMUITipDialog createLoadingDialog() {
        QMUITipDialog tipDialog = new QMUITipDialog.Builder(getContext())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在上传")
                .create();
        return tipDialog;
    }

    private void upload(String path, int index) {
        QMUITipDialog dialog = createLoadingDialog();
        dialog.show();

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
                        uploadImg(file, dialog, index);

                    }

                    @Override
                    public void onError(Throwable e) {


                    }
                }).launch();


    }

    private void uploadImg(File file, QMUITipDialog dialog, int index) {
        HttpUtil.getInstance(getActivity())
                .upload(false, AppUrl.businessImgUpload, file.getAbsolutePath(), file.getName(), new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        dialog.dismiss();
                        showToast("图片上传出错");
                    }

                    @Override
                    public void onResponse(String s) throws IOException {
                        dialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.getString("code").equals("1")) {
                                ImgBean bean = JSON.parseObject(s, ImgBean.class);

                                freshImgDatas(index, file.getAbsolutePath(), bean.data.url);
                            }else{
                                showToast("图片上传出错");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            showToast("图片上传出错");
                        }


                    }


                });

    }


    private void getAgentAdd() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                String name = mName.getText().toString();
                String phone = mPhone.getText().toString();
                String line = mLine.getText().toString();
                String nick = mNick.getText().toString().trim();
                String idno = mEtId.getText().toString().trim();
                String sms = mEtSms.getText().toString().trim();
                String deposit = mFreeze.getText().toString();


                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(line) || TextUtils.isEmpty(deposit)
                        || TextUtils.isEmpty(nick) || TextUtils.isEmpty(idno) || TextUtils.isEmpty(sms)) {
                    showToast("请将资料填写完整");
                    return;
                }
                if (mAdapter.imgArray.length != 3) {
                    showToast("请上传完整的身份证正面、反面、手持身份证三张照片");
                    return;
                }
                String img = TextUtils.join(",", mAdapter.imgArray);

                Map<String, String> map = new HashMap<>();
                map.put("real_name", name);
                map.put("phone", phone);
                map.put("line_rate", line);
                map.put("sms_code", sms);
                map.put("agent_name", nick);
                map.put("deposit", deposit);
                map.put("id_no", idno);
                map.put("id_img", img);
                HttpUtil.getInstance(getActivity())
                        .postForm(AppUrl.proxyAdd, map, new HttpUtil.ResultCallback() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(String response) throws IOException {
                                EventBus.getDefault().post(new FreshProxyEvent());//新增成功刷新
                                BaseJson json = JSON.parseObject(response, BaseJson.class);
                                showToast(json.msg);
                                popBackStack();


                            }
                        });
            }
        }, 100);


    }

    private void getSms() {

        String text = mPhone.getText().toString();
        if (TextUtils.isEmpty(text) && text.length() != 11) {
            showToast("请填写手机号码");
            return;
        }


        Map<String, String> map = new HashMap<>();
        map.put("phone", text);
        map.put("type", "1");//1 身份验证
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.getDefSms, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {


                    }

                    @Override
                    public void onResponse(String response) {
                        BaseJson baseJson = JSON.parseObject(response, BaseJson.class);
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
