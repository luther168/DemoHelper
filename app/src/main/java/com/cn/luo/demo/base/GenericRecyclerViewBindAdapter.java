package com.cn.luo.demo.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cn.luo.demo.R;
import com.cn.luo.demo.model.Demo;

import java.util.List;

/**
 * Created by Administrator on 2016/9/5.
 * 用来实现万能绑定适配器（适用于item为DataBinding的布局）
 */
public class GenericRecyclerViewBindAdapter extends BaseQuickAdapter<Demo, GenericViewHolder> {

    private Fragment fragment;

    public GenericRecyclerViewBindAdapter(Fragment fragment, @LayoutRes int layoutResId, @Nullable List<Demo> data) {
        super(layoutResId, data);
        this.fragment = fragment;
    }

    public void extraConvert() {

    }

    @Override
    protected void convert(GenericViewHolder helper, Demo item) {
        helper.bind(helper.getBinding(), item, fragment);
        extraConvert();
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }
}
