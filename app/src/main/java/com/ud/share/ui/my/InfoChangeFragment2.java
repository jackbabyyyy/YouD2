package com.ud.share.ui.my;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.amap.api.maps.model.MarkerOptions;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.event.ChangePhoneEvent;
import com.ud.share.event.Event;
import com.ud.share.model.BaseJson;
import com.ud.share.model.InfoBean;
import com.ud.share.net.AppUrl;
import com.ud.share.net.HttpUtil;
import com.ud.share.ui.ChangePhone.SmsFragment;
import com.ud.share.utils.AppData;
import com.ud.share.utils.CardNumUtils;
import com.ud.share.utils.SP;
import com.ud.share.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * Created by PP on 2019/3/8.
 */
public class InfoChangeFragment2 extends BaseFragment {


    @BindView(R.id.bar)
    QMUITopBarLayout mBar;
    @BindView(R.id.name)
    EditText mName;
    @BindView(R.id.phone)
    EditText mPhone;
    @BindView(R.id.changePhone)
    TextView mChangePhone;
    @BindView(R.id.cer)
    EditText mCer;
    @BindView(R.id.bank)
    EditText mBank;
    @BindView(R.id.bank2)
    EditText mBank2;
    @BindView(R.id.bank3)
    EditText mBank3;

    private InfoChangeAdapter mAdapter;
    private LinearLayoutManager mManager;
    private String mCredit_num;
    private String mBank_name;
    private String mBelong_bank;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info_change2;
    }

    public static InfoChangeFragment2 getInstance(String json) {
        InfoChangeFragment2 fragment = new InfoChangeFragment2();
        Bundle bundle = new Bundle();
        bundle.putString("json", json);
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    protected void init() {
        String lastPageString = getArguments().getString("json");
        initInfoData(lastPageString);

        EventBus.getDefault().register(this);

        mBar.setTitle("个人信息修改");
        mBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
        mBar.addRightTextButton("提交", R.id.topbar_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getInfoChange();

            }
        });

        mChangePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeDialog();

            }
        });
        mPhone.setEnabled(false);


    }

    private void showChangeDialog() {

        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(getActivity());
        builder.setTitle("第一步.请先验证原手机号")
                .setPlaceholder("在此输入您原来的手机号")

                .setInputType(InputType.TYPE_CLASS_NUMBER)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("下一步", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        if (text != null && text.length() > 0) {

                            defPhone(text.toString(), dialog);


                        } else {

                            showToast("请输入手机号");
                        }
                    }
                })
                .create(com.qmuiteam.qmui.R.style.QMUI_Dialog).show();

    }



    private void defPhone(String phone, QMUIDialog qmuiDialog) {
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("type", "1");

        HttpUtil.getInstance(getActivity()).postForm(AppUrl.defPhone, map, new HttpUtil.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String s) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    showToast(jsonObject.getString("msg"));
                    qmuiDialog.dismiss();
                    //第二步
                    startFragment(SmsFragment.getInstance(phone,"1"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }



    private void getInfoChange() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mCredit_num = mBank.getText().toString();
                mBank_name = mBank2.getText().toString();
                mBelong_bank = mBank3.getText().toString();

                if (TextUtils.isEmpty(mCredit_num)) {
                    showToast("请输入银行卡号");
                    return;
                }

                if (!CardNumUtils.matchLuhn(mCredit_num)) {
                    showToast("请输入正确的银行卡号");
                    return;
                }

                if (TextUtils.isEmpty(mBank_name)) {
                    showToast("请填写正确的银行");
                    return;
                }
                if (TextUtils.isEmpty(mBelong_bank)) {
                    showToast("请填写正确的所属分行");
                    return;
                }


                Map<String, String> map = new HashMap<>();
                map.put("credit_num", mCredit_num + "");
                map.put("bank_name", mBank_name + "");
                map.put("belong_bank", mBelong_bank + "");
                HttpUtil.getInstance(getActivity()).postForm(AppUrl.infoChange, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {
                        BaseJson json = JSON.parseObject(response, BaseJson.class);
                        if (json.code == 1) {
                            String info = SP.getInfo(getActivity());
                            InfoBean bean = JSON.parseObject(info, InfoBean.class);
                            bean.data.credit_num = mCredit_num + "";
                            bean.data.bank_name = mBank_name + "";
                            bean.data.belong_bank = mBelong_bank + "";
                            String infoChange = JSON.toJSONString(bean);
                            SP.saveInfo(getActivity(), infoChange);
                        }
                        showToast(json.msg);
                        popBackStack();

                    }
                });

            }
        }, 200);

    }


    private void initInfoData(String lastPageString) {
        InfoBean infoBean = JSON.parseObject(lastPageString, InfoBean.class);

        mName.setText(infoBean.data.realname);
        mPhone.setText(StringUtils.getPhoneX(infoBean.data.phone));
        mCer.setText(infoBean.data.card_sn);
        mBank.setText(infoBean.data.credit_num);
        mBank2.setText(infoBean.data.bank_name);
        mBank3.setText(infoBean.data.belong_bank);


    }




    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ChangePhoneEvent event) {
        mPhone.setText(event.phone);
    }
}
