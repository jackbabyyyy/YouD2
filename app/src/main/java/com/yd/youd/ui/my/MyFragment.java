package com.yd.youd.ui.my;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.walkermanx.photopicker.PhotoPicker;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;
import com.yd.youd.model.BaseJson;
import com.yd.youd.model.InfoBean;
import com.yd.youd.net.AppUrl;
import com.yd.youd.net.HttpUtil;
import com.yd.youd.ui.login.LoginActivity;
import com.yd.youd.utils.AppData;
import com.yd.youd.utils.SP;
import com.yd.youd.web.WebFragment;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Request;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by PP on 2019/3/5.
 */
public class MyFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.circleImageView)
    CircleImageView mCircleImageView;
    @BindView(R.id.tvName)
    TextView mTvName;
    @BindView(R.id.set)
    View mViewSet;
    @BindView(R.id.info)
    View mViewInfo;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.divider_title)
    TextView mDividerTitle;
    private InfoBean mInfoBean;
    private String mToNextPageString;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my
                ;
    }

    @Override
    protected void init() {
        mDividerTitle.setText("应用设置");


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setNestedScrollingEnabled(false);
        MyAdapter myAdapter = new MyAdapter(AppData.getMy());
        mRecyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(this);

        getInfo();


    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            case 0://常见问题
                startFragment(WebFragment.getInstance(AppData.getH5().data.faq));
                break;
            case 1:
                break;
            case 2:
                //密码  修改
                if (mInfoBean.data.isset_pwd.equals("1")) {
                    startFragment(PassChangeFragment.getInstance(false));
                } else {
                    startFragment(PassChangeFragment.getInstance(true));

                }
                break;
            case 3://关于我们
                startFragment(WebFragment.getInstance(AppData.getH5().data.about));
                break;
            case 4:

                showLoginOut();
                break;
        }

    }

    private void showLoginOut() {

        new QMUIDialog.MessageDialogBuilder(getActivity())
                .setMessage("确定要退出当前登录吗？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction(0, "退出", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        //shanchu 登录信息
                        SP.clear(getActivity());

                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        popBackStack();
                    }
                })
                .create(com.qmuiteam.qmui.R.style.QMUI_Dialog).show();


    }


    @OnClick({R.id.circleImageView, R.id.set, R.id.info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.circleImageView:
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setPreviewEnabled(false)
                        .setCrop(true)
                        .setCropXY(1, 1)
                        .start(getActivity(), this);
                break;
            case R.id.set:
                startFragment(new PartionSetFragment());
                break;
            case R.id.info:
                startFragment(InfoFragment.getInstance(mToNextPageString));
                break;
        }
    }

    private void getInfo() {
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.infoGet, null, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {

                        mToNextPageString = response;

                        mInfoBean = JSON.parseObject(response, InfoBean.class);
                        //
                        Glide.with(getActivity()).load(mInfoBean.data.avatar).into(mCircleImageView);
                        mTvName.setText(mInfoBean.data.realname);


                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (null != data) {
            String path = data.getStringExtra(PhotoPicker.KEY_CAMEAR_PATH);
            Glide.with(getActivity()).load(Uri.fromFile(new File(path))).into(mCircleImageView);


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
                        getHeadChange(file);


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("chen", "onError: " + e.getMessage());

                    }
                }).launch();


    }

    private void getHeadChange(File file) {
        HttpUtil.getInstance(getActivity())
                .upload(true,AppUrl.headChange, file.getAbsolutePath(), file.getName(), new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {
                        BaseJson baseJson = JSON.parseObject(response, BaseJson.class);
                        showToast(baseJson.msg);

                    }
                });

    }
}
