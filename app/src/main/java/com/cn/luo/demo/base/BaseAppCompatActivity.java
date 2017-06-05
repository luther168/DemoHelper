package com.cn.luo.demo.base;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.cn.luo.demo.R;

public class BaseAppCompatActivity extends AppCompatActivity {

    protected Context context;
    protected FragmentManager manager;

    private BaseFragment mBaseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base_app_compat);

        this.context = this;

        Class<?> clazz = this.getClass();

        this.manager = this.getSupportFragmentManager();
        FragmentTransaction transaction = this.manager.beginTransaction();
        transaction.addToBackStack(null);

        if(clazz.isAnnotationPresent(Content.class)){
            Content content = clazz.getAnnotation(Content.class);
            if(content != null){
                String name = content.fragment();
                String title = content.title();

                this.getIntent().putExtra("title", title);

                try{
                    Class<?> contentClazz = Class.forName(name);
                    mBaseFragment = (BaseFragment) contentClazz.newInstance();
                    mBaseFragment.setArguments(this.getIntent().getExtras());
                    transaction.replace(R.id.mLayoutFragment, mBaseFragment);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(this.mBaseFragment != null){
            this.mBaseFragment.onRestart();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.mBaseFragment != null){
            this.mBaseFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        return this.mBaseFragment == null || this.mBaseFragment.onKeyDown(keyCode, event);
    }
}
