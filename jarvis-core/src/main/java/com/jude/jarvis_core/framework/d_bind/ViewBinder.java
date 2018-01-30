package com.jude.jarvis_core.framework.d_bind;

import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jude.jarvis_core.exception.IllegalClassParameterException;
import com.jude.jarvis_core.framework.b_inject.ViewInjector;
import com.jude.jarvis_core.framework.c_decoration.DecorationInjector;
import com.jude.jarvis_core.framework.e_lifecycle.LifeCyclePresenter;
import com.jude.jarvis_core.framework.e_lifecycle.LifeCycleViewBinder;
import com.jude.jarvis_core.util.ParametrizedTypeUtil;
import com.jude.jarvis_core.util.ViewBindingHelper;

/**
 * Created by Jude on 2017/7/27.
 */

public class ViewBinder {

    public static <D extends ViewDataBinding,P extends BindPresenter<D>> P createView(Class<P> clazz, ViewGroup parent){
        return createView(clazz,parent,true);
    }

    public static <D extends ViewDataBinding,P extends BindPresenter<D>> P createView(Class<P> clazz, ViewGroup parent, boolean attach){
        D dataBinding = ViewBinder.createDataBinding(clazz,parent,attach);
        final P presenter = createPresenter(clazz);
        ViewBindingHelper.bindPresenter(dataBinding,presenter);
        presenter.mViewDataBinding = dataBinding;
        ViewInjector.inject(presenter,parent.getContext());
        DecorationInjector.inject(presenter,parent.getContext());
        LifeCycleViewBinder.bind((LifeCyclePresenter) presenter,dataBinding.getRoot());
        presenter.onCreated();
        dataBinding.addOnRebindCallback(new OnRebindCallback() {
            @Override
            public void onBound(ViewDataBinding binding) {
                presenter.onViewBound();
            }
        });
        return presenter;
    }

    private static <D extends ViewDataBinding,P extends BindPresenter> D createDataBinding(Class<P> clazz, ViewGroup parent, boolean attach){
        Class<D> viewDataBindingClass = ParametrizedTypeUtil.getTypeArgumentClass(clazz,0);
        if (viewDataBindingClass == null){
            throw new IllegalClassParameterException("ViewDataBinding not found in class "+clazz.getName());
        }
        D dataBinding =  ViewBindingHelper.createDataBinding(viewDataBindingClass, LayoutInflater.from(parent.getContext()),parent,attach);
        return dataBinding;
    }

    private static <P extends BindPresenter> P createPresenter(Class<P> clazz){
        try {
            return clazz.newInstance();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (InstantiationException e) {
            e.printStackTrace();
        }
        throw new IllegalAccessError("the presenter shouldn't change the constructor");
    }

}
