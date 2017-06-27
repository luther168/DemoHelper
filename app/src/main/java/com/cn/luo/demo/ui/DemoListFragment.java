package com.cn.luo.demo.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cn.luo.demo.R;
import com.cn.luo.demo.base.BaseFragment;
import com.cn.luo.demo.base.GenericRecyclerViewBindAdapter;
import com.cn.luo.demo.model.Demo;
import com.cn.luo.demo.router.RouterConstant;
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
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        List<Demo> demoList = getDemoList();

        adapter = new GenericRecyclerViewBindAdapter<Demo>(context, this, demoList, R.layout.item_demo);
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    private List<Demo> getDemoList() {
        List<Demo> demoList = new ArrayList<Demo>();

        demoList.add(new Demo(getString(R.string.about), RouterConstant.ABOUT_ACTIVITY));

        return demoList;
    }

    public void onDemoItemClick(Demo item) {
        RouterService.toTargetActivity(item.getPath());
    }

    public int getSizeOfDemoList() {
        return adapter.getItemCount();
    }

}
