package com.cn.luo.demo.ui;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.cn.luo.demo.R;
import com.cn.luo.demo.base.BaseFragment;
import com.cn.luo.demo.databinding.FragmentRxBindingBinding;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:  2017/7/27 15:49
 * NOTE:
 */
public class RxBindingFragment extends BaseFragment {

    private FragmentRxBindingBinding binding;
    private int count = 0;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_rx_binding;
    }

    @Override
    protected void initUI() {
        binding = DataBindingUtil.bind(rootView);
        binding.setListener(this);

        binding.titleView.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnEnable) {
            // Simulate sending the check number
            final long count = 3;
            Observable.interval(0, 1, TimeUnit.SECONDS)
                    .take(count + 1)
                    .map(new Function<Long, Long>() {
                        @Override
                        public Long apply(@NonNull Long aLong) throws Exception {
                            return count - aLong;
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(@NonNull Disposable disposable) throws Exception {
                            binding.btnEnable.setEnabled(false);
                            binding.btnEnable.setTextColor(Color.BLACK);
                        }
                    })
                    .subscribe(new Observer<Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Long aLong) {
                            binding.btnEnable.setText(String.format(getString(R.string.the_rest_seconds), String.valueOf(aLong)));
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {
                            binding.btnEnable.setEnabled(true);
                            binding.btnEnable.setTextColor(Color.RED);
                            binding.btnEnable.setText(getString(R.string.send_check_num));
                        }
                    });
        } else if (id == R.id.etSearch) {
            // 优化搜索请求
            RxTextView.textChanges(binding.etSearch)
                    // 当你敲完字之后停下来的半秒就会执行下面语句
                    .debounce(500, TimeUnit.MILLISECONDS)
                    // 下面这两个都是数据转换
                    // flatMap：当同时多个网络请求访问的时候，前面的网络数据会覆盖后面的网络数据
                    // switchMap：当同时多个网络请求访问的时候，会以最后一个发送请求为准，前面网路数据会被最后一个覆盖
                    .switchMap(new Function<CharSequence, ObservableSource<List<String>>>() {
                        @Override
                        public ObservableSource<List<String>> apply(@NonNull CharSequence charSequence) throws Exception {
                            //网络操作，获取我们需要的数据
                            List<String> list = new ArrayList<String>();
                            if (!TextUtils.isEmpty(charSequence)) {
                                list.add(charSequence.toString());
                                list.add("search");
                                list.add("result");
                            }
                            return Observable.just(list);
                        }
                    })
                    //网络请求是在子线程的
                    .subscribeOn(Schedulers.io())
                    //界面更新在主线程
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<String>>() {
                        @Override
                        public void accept(@NonNull List<String> strings) throws Exception {
                            //界面更新，这里用打印替代
                            if (strings.size() > 0) {
                                binding.tvSearchResult.setText(strings.toString());
                            }
                        }
                    });
        } else if (id == R.id.btnClick) {
            // 优化点击请求
            RxView.clicks(binding.btnClick).throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Object o) {
                            ToastUtils.showShort(getString(R.string.optimize_click_request) + " " + count++);
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }
}
