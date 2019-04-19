package com.yd.youd.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;
import com.yd.youd.model.LoginBean;
import com.yd.youd.net.AppUrl;
import com.yd.youd.net.HttpUtil;
import com.yd.youd.ui.MainActivity;
import com.yd.youd.utils.SP;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * Created by PP on 2019/3/5.
 * <p>
 * 密码登录
 */
public class LoginPsdFragment extends BaseFragment {


    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_pass)
    EditText mEtPass;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.tv_sms)
    TextView mTvSms;



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_psd;
    }

    @Override
    protected void init() {


    }




    @OnClick({R.id.btn_login, R.id.tv_sms})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                getLoginPass();
                break;
            case R.id.tv_sms:
                startFragment(new LoginSmsFragment());
                break;
        }
    }

    private void getLoginPass() {
        String phone=mEtPhone.getText().toString();
        String pass=mEtPass.getText().toString();
        if (TextUtils.isEmpty(phone)||TextUtils.isEmpty(pass)){
            showToast("请填写完整");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("phone",phone);
        map.put("password",pass);
        HttpUtil.getInstance(getActivity())
                .postForm(AppUrl.loginPass, map, new HttpUtil.ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws IOException {
                        LoginBean loginBean= JSON.parseObject(response,LoginBean.class);
                        SP.saveToken(getActivity(),loginBean.data.userToken);
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().finish();

                    }
                });
    }
}
