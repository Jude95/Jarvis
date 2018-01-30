package com.jude.jarvis_core.framework.e_lifecycle;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.jude.jarvis_core.framework.d_bind.BindPresenter;

/**
 * Created by zhuchenxi on 2017/1/16.
 */

public class LifeCyclePresenter<D extends ViewDataBinding> extends BindPresenter<D> implements PresenterLifeCycle{

    @Override
    public void onViewAttached(Context ctx) {
    }

    @Override
    public void onViewDetached() {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

}
