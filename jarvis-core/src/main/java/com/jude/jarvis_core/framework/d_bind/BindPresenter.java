package com.jude.jarvis_core.framework.d_bind;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.jarvis_core.framework.c_decoration.DecorationPresenter;
import com.jude.jarvis_core.util.ParametrizedTypeUtil;
import com.jude.jarvis_core.util.ViewBindingHelper;

/**
 * Created by zhuchenxi on 2017/1/16.
 */

public class BindPresenter<D extends ViewDataBinding> extends DecorationPresenter {
    D mViewDataBinding;

    D createDataBinding(LayoutInflater inflater, ViewGroup container,boolean attach) {
        Class<D> viewDataBindingClass = ParametrizedTypeUtil.getSuperTypeArgumentClass(getClass(), 0);
        if (viewDataBindingClass == null){
            throw new IllegalArgumentException("DataBinding class no found in "+getClass());
        }
        return ViewBindingHelper.createDataBinding(viewDataBindingClass, inflater, container, attach);
    }

    public D getViewDataBinding() {
        return mViewDataBinding;
    }

    public View getRootView() {
        return mViewDataBinding.getRoot();
    }

    public void onCreated(){

    }

    public void onViewBound() {

    }

}
