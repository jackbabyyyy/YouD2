/*
 * Tencent is pleased to support the open source community by making QMUI_Android available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the MIT License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yd.youd.base;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.yd.youd.R;

import butterknife.ButterKnife;


/**
 * Created by cgspine on 2018/1/7.
 */

public abstract class BaseFragment extends QMUIFragment {

    public BaseFragment() {
    }

    protected abstract int getLayoutId();



    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(getLayoutId(), null);
        ButterKnife.bind(this, root);

        init();

        return root;
    }

    protected abstract void init();

    @Override
    protected int backViewInitOffset() {
        return QMUIDisplayHelper.dp2px(getContext(), 100);
    }


    protected void showToast(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }








    //    protected void goToWebExplorer(@NonNull String url, @Nullable String title) {
//        Intent intent = QDMainActivity.createWebExplorerIntent(getContext(), url, title);
//        startActivity(intent);
//    }
//
//    protected void injectDocToTopBar(QMUITopBar topBar) {
//        final QDItemDescription description = QDDataManager.getInstance().getDescription(this.getClass());
//        if (description != null) {
//            topBar.addRightTextButton("DOC", QMUIViewHelper.generateViewId())
//                    .setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            goToWebExplorer(description.getDocUrl(), description.getName());
//                        }
//                    });
//        }
//    }
//
//    protected void injectDocToTopBar(QMUITopBarLayout topBar){
//        final QDItemDescription description = QDDataManager.getInstance().getDescription(this.getClass());
//        if (description != null) {
//            topBar.addRightTextButton("DOC", QMUIViewHelper.generateViewId())
//                    .setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            goToWebExplorer(description.getDocUrl(), description.getName());
//                        }
//                    });
//        }
//    }
}
