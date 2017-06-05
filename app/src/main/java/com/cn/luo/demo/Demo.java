package com.cn.luo.demo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

/**
 * @author: luther
 * @version: ${VERSION}
 * @project: DemoHelper
 * @package: com.cn.luo.demo
 * @description: ${DESP}
 * @date: 2017/6/1
 * @time: 16:16
 */
public class Demo {
    private String name;
    private Class<? extends Activity> targetActivityClass;

    public Demo(String name, Class<? extends Activity> targetActivityClass) {
        this.name = name;
        this.targetActivityClass = targetActivityClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<? extends Activity> getTargetActivityClass() {
        return targetActivityClass;
    }

    public void setTargetActivityClass(Class<? extends Activity> targetActivityClass) {
        this.targetActivityClass = targetActivityClass;
    }
}
