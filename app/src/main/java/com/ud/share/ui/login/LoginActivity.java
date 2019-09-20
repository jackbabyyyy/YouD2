package com.ud.share.ui.login;

import com.ud.share.R;
import com.ud.share.base.BaseFragment;
import com.ud.share.base.BaseFragmentActivity;

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
