package com.jude.jarvis_core.expansion.list;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;

import com.jude.jarvis_core.expansion.DisposableTransformer;
import com.jude.jarvis_core.framework.z_base.JarvisPresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Jude on 2017/7/27.
 */

public abstract class ListItemPresenter<D extends ViewDataBinding,M> extends JarvisPresenter<D> {
    public ObservableField<M> data = new ObservableField<>();
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public <T> DisposableTransformer<T> getDisposableTransformer(){
        return new DisposableTransformer<>(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                mCompositeDisposable.add(disposable);
            }
        });
    }

    public void onDataChange(M m){

    }

    void publish(M data){
        mCompositeDisposable.dispose();
        mCompositeDisposable = new CompositeDisposable();
        onDataChange(data);
        this.data.set(data);
    }

    @Override
    public void onViewAttached(Context ctx) {
        super.onViewAttached(ctx);
        if (mCompositeDisposable.isDisposed()){
            this.data.set(data.get());
        }
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onViewDetached() {
        super.onViewDetached();
        mCompositeDisposable.dispose();
    }
}
