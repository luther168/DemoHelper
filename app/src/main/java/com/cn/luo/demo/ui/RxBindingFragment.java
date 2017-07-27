package com.cn.luo.demo.ui;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;

import com.cn.luo.demo.R;
import com.cn.luo.demo.base.BaseFragment;
import com.cn.luo.demo.databinding.FragmentRxBindingBinding;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:  2017/7/27 15:49
 * NOTE:
 */
public class RxBindingFragment extends BaseFragment {

    private FragmentRxBindingBinding binding;

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
        }
    }
}
