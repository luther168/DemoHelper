package com.cn.luo.demo.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cn.luo.demo.base.BaseWithFragmentAppCompatActivity;
import com.cn.luo.demo.base.annotation.Content;
import com.cn.luo.demo.router.RouterService;

@Content(fragment = AndFixFragment.class)
@Route(path = RouterService.AND_FIX_ACTIVITY)
public class AndFixActivity extends BaseWithFragmentAppCompatActivity {
}
