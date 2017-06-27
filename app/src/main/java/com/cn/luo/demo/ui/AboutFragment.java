package com.cn.luo.demo.ui;


import android.view.View;

import com.cn.luo.demo.R;
import com.cn.luo.demo.base.BaseFragment;
import com.cn.luo.demo.view.TitleView;

public class AboutFragment extends BaseFragment {

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initUI() {
        TitleView titleView = view.findViewById(R.id.titleView);
        titleView.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

}
