package com.yd.youd.ui.login;

import com.yd.youd.R;
import com.yd.youd.base.BaseFragment;
import com.yd.youd.base.BaseFragmentActivity;
import com.yd.youd.ui.login.LoginPsdFragment;

/**
 * Created by PP on 2019/3/5.
 */
public class LoginActivity extends BaseFragmentActivity {


    @Override
    protected int getContextViewId() {
        return R.id.login;
    }


    @Override
    protected BaseFragment getFirstFragment() {

        return new LoginPsdFragment();
    }


}
