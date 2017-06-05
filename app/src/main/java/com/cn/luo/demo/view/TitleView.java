package com.cn.luo.demo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cn.luo.demo.R;

/**
 * @author: luther
 * @description: 自定义标题栏
 * @time: 2017/5/19 14:36
 */

public class TitleView extends RelativeLayout {

    public static final String RIGHT_SHOW_NONE = "none";
    public static final String RIGHT_SHOW_IMG = "img";
    public static final String RIGHT_SHOW_TEXT = "text";

    private final int RESOURCE_IS_NULL = 0;

    private TextView tvTitle, tvRight;
    private FrameLayout layoutLeft, layoutRight;
    private ImageView imgLeft, imgRight;
    private View backgroundView;

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        initAttributes(context, attrs);
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_title, this);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvRight = (TextView) findViewById(R.id.tvRight);
        layoutLeft = (FrameLayout) findViewById(R.id.layoutLeft);
        layoutRight = (FrameLayout) findViewById(R.id.layoutRight);
        imgLeft = (ImageView) findViewById(R.id.imgLeft);
        imgRight = (ImageView) findViewById(R.id.imgRight);
        backgroundView = findViewById(R.id.titleBackgroundView);
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitleViewStyle);
        String title = a.getString(R.styleable.TitleViewStyle_title);
        int titleBackgroundResId = a.getResourceId(R.styleable.TitleViewStyle_title_background, RESOURCE_IS_NULL);
        String rightMode = a.getString(R.styleable.TitleViewStyle_right_mode);
        int rightImgResId = a.getResourceId(R.styleable.TitleViewStyle_right_img_src, RESOURCE_IS_NULL);
        int rightTextResId = a.getResourceId(R.styleable.TitleViewStyle_right_text_src, RESOURCE_IS_NULL);
        a.recycle();

        setTitle(title);
        if (titleBackgroundResId != RESOURCE_IS_NULL) {
            setTitleBackgroundRes(titleBackgroundResId);
        }

        if (rightMode == null) {
            rightMode = RIGHT_SHOW_NONE;
        }
        if (rightMode.equals(RIGHT_SHOW_IMG)) {
            setRightImg(rightImgResId);
        } else if (rightMode.equals(RIGHT_SHOW_TEXT)) {
            setRightText(rightTextResId);
        } else {
            layoutRight.setVisibility(GONE);
        }
    }

    /**
     * 显示并设置右边的文本
     * @param resId StringRes
     */
    public void setRightText(@StringRes int resId) {
        if (resId != RESOURCE_IS_NULL) {
            imgRight.setVisibility(GONE);
            tvRight.setVisibility(VISIBLE);
            tvRight.setText(resId);
        }
        layoutRight.setVisibility(VISIBLE);
    }

    /**
     * 显示并设置右边的图片
     * @param resId DrawableRes
     */
    public void setRightImg(@DrawableRes int resId) {
        if (resId != RESOURCE_IS_NULL) {
            imgRight.setVisibility(VISIBLE);
            tvRight.setVisibility(GONE);
            imgRight.setImageResource(resId);
            layoutRight.setVisibility(VISIBLE);
        } else {
            layoutRight.setVisibility(GONE);
        }
    }

    /**
     * 设置标题栏背景色
     * @param resId DrawableRes
     */
    public void setTitleBackgroundRes(@DrawableRes int resId) {
        backgroundView.setBackgroundResource(resId);
    }

    /**
     * 设置标题的文本
     * @param title 标题
     */
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    /**
     * 设置左边的点击事件
     * @param leftOnClickListener 点击事件
     */
    public void setLeftOnClickListener(OnClickListener leftOnClickListener) {
        layoutLeft.setOnClickListener(leftOnClickListener);
    }

    /**
     * 设置右边的点击事件
     * @param rightOnClickListener 点击事件
     */
    public void setRightOnClickListener(OnClickListener rightOnClickListener) {
        layoutRight.setOnClickListener(rightOnClickListener);
    }
}
