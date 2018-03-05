package com.cn.luo.demo.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.cn.luo.demo.R;
import com.cn.luo.demo.base.BaseFragment;
import com.cn.luo.demo.base.QuickFragmentPagerAdapter;
import com.cn.luo.demo.databinding.FragmentViewPagerBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:        2017/11/14 11:10
 * NOTE:
 */
public class ViewPagerFragment extends BaseFragment {
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_view_pager;
    }

    @Override
    protected void initUI() {
        FragmentViewPagerBinding binding = DataBindingUtil.bind(rootView);
        List<BaseFragment> fragmentList = new ArrayList<>();
        addHelloWorldFragment(fragmentList, "1");
        addHelloWorldFragment(fragmentList, "2");
        addHelloWorldFragment(fragmentList, "3");
        binding.viewPager.setAdapter(new QuickFragmentPagerAdapter<>(
                getChildFragmentManager(), fragmentList,
                new String[]{"Tab1", "Tab2", "Tab3"}));
        binding.tabPagerIndicator.setViewPager(binding.viewPager);

        binding.titleView.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    private void addHelloWorldFragment(List<BaseFragment> fragmentList, String info) {
        HelloWorldFragment helloWorldFragment = new HelloWorldFragment();
        Bundle bundle = new Bundle();
        bundle.putString(HelloWorldFragment.INTENT_INFO, info);
        helloWorldFragment.setArguments(bundle);
        fragmentList.add(helloWorldFragment);
    }

    @Override
    public void onClick(View view) {

    }
}
