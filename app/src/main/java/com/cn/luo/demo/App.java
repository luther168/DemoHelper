package com.cn.luo.demo;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:  2017/6/27 10:49
 * NOTE:
 */
public class App extends Application {

    private Application instance = this;
    private boolean debug = true;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        if (isDebug()) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    public boolean isDebug() {
        return debug;
    }
}
