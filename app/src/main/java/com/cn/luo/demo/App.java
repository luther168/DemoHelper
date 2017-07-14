package com.cn.luo.demo;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.euler.andfix.patch.PatchManager;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:  2017/6/27 10:49
 * NOTE:
 */
public class App extends Application {

    public static final String APATCH_PATH = "/out.apatch";

    private static final String TAG = App.class.getSimpleName();
    private static final String VERSION_NAME = "1.0";
    private Application instance = this;
    private boolean debug = true;
    private static PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        Utils.init(this);

        if (isDebug()) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);

        // initialize
        mPatchManager = new PatchManager(this);
        mPatchManager.init("1.0");
        LogUtils.d(TAG, "inited.");

        // load patch
        mPatchManager.loadPatch();
        LogUtils.d(TAG, "apatch loaded.");
    }

    public boolean isDebug() {
        return debug;
    }

    public static PatchManager getPatchManager() {
        return mPatchManager;
    }
}
