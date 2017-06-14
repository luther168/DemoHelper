package com.cn.luo.demo.util;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class NavigationUtil {

    public static void toActivity(Fragment fragment, Class<? extends Activity> activityClass) {
        Intent intent = new Intent();
        intent.setClass(fragment.getContext(), activityClass);
        fragment.startActivity(intent);
    }

}
