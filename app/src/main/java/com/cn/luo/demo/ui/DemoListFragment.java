package com.cn.luo.demo.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cn.luo.demo.R;
import com.cn.luo.demo.base.BaseFragment;
import com.cn.luo.demo.base.GenericRecyclerViewBindAdapter;
import com.cn.luo.demo.model.Demo;
import com.cn.luo.demo.router.RouterService;

import java.util.ArrayList;
import java.util.List;

public class DemoListFragment extends BaseFragment {

    private GenericRecyclerViewBindAdapter adapter;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_demo_list;
    }

    @Override
    protected void initUI() {
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        List<Demo> demoList = getDemoList();

        adapter = new GenericRecyclerViewBindAdapter(this, R.layout.item_demo, demoList);
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    private List<Demo> getDemoList() {
        List<Demo> demoList = new ArrayList<Demo>();

        demoList.add(new Demo(getString(R.string.about), RouterService.ABOUT_ACTIVITY));
        demoList.add(new Demo(getString(R.string.and_fix), RouterService.AND_FIX_ACTIVITY));
        demoList.add(new Demo(getString(R.string.rx_binding), RouterService.RX_BINDING_ACTIVITY));

        return demoList;
    }

    public void onDemoItemClick(Demo item) {
        RouterService.toTargetActivity(item.getPath());
    }

    public int getSizeOfDemoList() {
        return adapter.getItemCount();
    }

    @Override
    public void onClick(View view) {

    }
}
