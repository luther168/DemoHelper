package com.cn.luo.demo.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.cn.luo.demo.BR;
import com.cn.luo.demo.R;
import com.cn.luo.demo.model.Demo;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:  2017/6/28 16:25
 * NOTE:
 */
public class GenericViewHolder extends BaseViewHolder {

    public GenericViewHolder(View convertView) {
        super(convertView);
    }

    void bind(@NonNull ViewDataBinding binding, @NonNull Demo item, Fragment fragment) {
        binding.setVariable(BR.item, item);
        if (fragment != null) {
            binding.setVariable(BR.fragment, fragment);
        }
        binding.setVariable(BR.position, getLayoutPosition());
    }

    public ViewDataBinding getBinding() {
        return (ViewDataBinding) itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
    }
}
