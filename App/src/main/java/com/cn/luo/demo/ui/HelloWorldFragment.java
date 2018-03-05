package com.cn.luo.demo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cn.luo.demo.R;
import com.cn.luo.demo.base.BaseFragment;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:        2018/3/5 9:48
 * NOTE:
 */
public class HelloWorldFragment extends BaseFragment {

    public static final String INTENT_INFO = "info";

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_hello_world;
    }

    @Override
    protected void initUI() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String info = bundle.getString(INTENT_INFO);
            TextView tvInfo = rootView.findViewById(R.id.tvInfo);
            tvInfo.setText(info);
        }
    }

    @Override
    public void onClick(View view) {

    }
}
