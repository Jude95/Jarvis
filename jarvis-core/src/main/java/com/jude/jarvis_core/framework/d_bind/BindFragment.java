package com.jude.jarvis_core.framework.d_bind;

import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.jarvis_core.framework.c_decoration.DecorationFragment;
import com.jude.jarvis_core.util.ViewBindingHelper;

/**
 * Created by zhuchenxi on 2017/1/16.
 */

public class BindFragment<P extends BindPresenter> extends DecorationFragment<P> {
    private ViewDataBinding dataBinding;

    @Override
    public P getPresenter() {
        return (P) super.getPresenter();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDataBinding(getLayoutInflater(),getContentView());
    }

    private void initDataBinding(LayoutInflater inflater, ViewGroup container){
        dataBinding = getPresenter().createDataBinding(inflater, container,true);
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
