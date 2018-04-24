package com.jude.jarvis_core.framework.e_lifecycle;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.jude.jarvis_core.expansion.DisposableTransformer;
import com.jude.jarvis_core.framework.d_bind.BindPresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by zhuchenxi on 2017/1/16.
 */

public class LifeCyclePresenter<D extends ViewDataBinding> extends BindPresenter<D> implements PresenterLifeCycle{
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    public void onViewAttached(Context ctx) {
    }

    @Override
    public void onViewDetached() {
        mCompositeDisposable.clear();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    public void addDisposable(Disposable disposable){
        mCompositeDisposable.add(disposable);
    }

    public <T> DisposableTransformer<T> getDisposableTransformer(){
        return new DisposableTransformer<>(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                mCompositeDisposable.add(disposable);
            }
        });
    }


}
