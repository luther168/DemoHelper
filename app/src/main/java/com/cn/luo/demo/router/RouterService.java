package com.cn.luo.demo.router;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:  2017/6/27 10:26
 * NOTE:
 */
public class RouterService {

    public final static String ABOUT_ACTIVITY = "/app/activity/about";
    public static final String AND_FIX_ACTIVITY = "/app/activity/andFix";
    public static final String RX_BINDING_ACTIVITY = "/app/activity/rxBinding";

    public static void toTargetActivity(String path) {
        ARouter.getInstance()
                .build(path)
                .navigation();
    }
}
