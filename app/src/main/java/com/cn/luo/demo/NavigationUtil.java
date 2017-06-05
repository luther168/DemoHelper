package com.cn.luo.demo;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * @author: luther
 * @version: ${VERSION}
 * @project: DemoHelper
 * @package: com.cn.luo.demo
 * @description: ${DESP}
 * @date: 2017/6/1
 * @time: 17:05
 */
public class NavigationUtil {
    public static void toActivity(Fragment fragment, Class<? extends Activity> activityClass) {
        Intent intent = new Intent();
        intent.setClass(fragment.getContext(), activityClass);
        fragment.startActivity(intent);
    }
}
