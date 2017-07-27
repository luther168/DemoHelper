package com.cn.luo.demo.ui;

import android.databinding.DataBindingUtil;
import android.os.Environment;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.cn.luo.demo.App;
import com.cn.luo.demo.R;
import com.cn.luo.demo.MyUtils;
import com.cn.luo.demo.base.BaseFragment;
import com.cn.luo.demo.databinding.FragmentAndFixBinding;

import java.io.IOException;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:  2017/7/14 11:25
 * NOTE:
 */
public class AndFixFragment extends BaseFragment {

    private static final String TAG = AndFixFragment.class.getSimpleName();

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_and_fix;
    }

    @Override
    protected void initUI() {
        FragmentAndFixBinding binding = DataBindingUtil.bind(rootView);
        binding.setFragment(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnCallMethod) {
            ToastUtils.showShort(MyUtils.getAndFixStatusStr());
        } else if (id == R.id.btnApplyPatch) {
            // add patch at runtime
            try {
                // .apatch file path
                String patchFileString = Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + App.APATCH_PATH;
                App.getPatchManager().addPatch(patchFileString);
                LogUtils.d(TAG, "apatch:" + patchFileString + " added.");
            } catch (IOException e) {
                LogUtils.e(TAG, "", e);
            }
        }
    }
}
