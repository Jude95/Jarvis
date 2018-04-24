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


    public void onDataChange(M m){

    }

    void publish(M data){
        mCompositeDisposable.clear();
        onDataChange(data);
        this.data.set(data);
    }

}
