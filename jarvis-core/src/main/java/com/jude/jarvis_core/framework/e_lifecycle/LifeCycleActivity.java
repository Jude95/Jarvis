package com.jude.jarvis_core.framework.e_lifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jude.jarvis_core.framework.d_bind.BindActivity;

/**
 * Created by zhuchenxi on 2017/1/16.
 * 负责Presenter 生命周期注入的一层。
 */

public class LifeCycleActivity<P extends LifeCyclePresenter> extends BindActivity<P> {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().onViewAttached(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().onViewDetached();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getPresenter().onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getPresenter().onRestoreInstanceState(savedInstanceState);
    }


}
