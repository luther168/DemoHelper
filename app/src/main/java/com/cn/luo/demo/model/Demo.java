package com.cn.luo.demo.model;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

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
