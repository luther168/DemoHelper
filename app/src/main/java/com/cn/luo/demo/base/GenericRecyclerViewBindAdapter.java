package com.cn.luo.demo.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.luo.demo.BR;

import java.util.List;

/**
 * Created by Administrator on 2016/9/5.
 * 用来实现万能绑定适配器
 */
public class GenericRecyclerViewBindAdapter<T> extends RecyclerView.Adapter<GenericRecyclerViewBindAdapter.ViewHolder> {
    protected Context context;
    protected List<T> list;
    private int layoutId;
    private Fragment fragment;

    private int mSelectedIndex;

    public GenericRecyclerViewBindAdapter(Context context, Fragment fragment, List<T> list, int layoutId) {
        this.context = context;
        this.fragment = fragment;
        this.list = list;
        this.layoutId = layoutId;
    }

    public void replaceData(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new ViewHolder(convertView, fragment);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getItem(position), position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public static class ViewHolder<T> extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;
        private Fragment fragment;

        ViewHolder(View convertView, Fragment fragment) {
            super(convertView);
            this.binding = DataBindingUtil.bind(convertView);
            this.fragment = fragment;
        }

        void bind(@NonNull T item, Integer position) {
            binding.setVariable(BR.item, item);
            if (fragment != null) {
                binding.setVariable(BR.fragment, fragment);
            }
            binding.setVariable(BR.position, position);
        }
    }
}
