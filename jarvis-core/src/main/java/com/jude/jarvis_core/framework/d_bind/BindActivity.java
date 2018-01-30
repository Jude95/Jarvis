package com.jude.jarvis_core.framework.d_bind;

import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jude.jarvis_core.framework.c_decoration.DecorationActivity;
import com.jude.jarvis_core.util.ViewBindingHelper;

/**
 * Created by zhuchenxi on 2017/1/16.
 * 负责MVVM视图绑定的一层
 */

public class BindActivity<P extends BindPresenter> extends DecorationActivity<P> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
    }

    public <E extends P> E getPresenter() {
        return (E) super.getPresenter();
    }

    private void initDataBinding() {
        ViewDataBinding dataBinding = getPresenter().createDataBinding(getLayoutInflater(), getContentView(),true);
        ViewBindingHelper.bindPresenter(dataBinding, getPresenter());
        getPresenter().mViewDataBinding = dataBinding;
        getPresenter().onCreated();
        dataBinding.addOnRebindCallback(new OnRebindCallback() {
            @Override
            public void onBound(ViewDataBinding binding) {
                getPresenter().onViewBound();
            }
        });
    }

}
