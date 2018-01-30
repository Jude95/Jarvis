package com.jude.jarvis_core.expansion.data;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import com.jude.jarvis_core.expansion.DisposableTransformer;
import com.jude.jarvis_core.framework.z_base.JarvisPresenter;

/**
 * Created by Jude on 2017/7/25.
 */

public class DataPresenter<D extends ViewDataBinding,M> extends JarvisPresenter<D> {

    public ObservableField<M> data = new ObservableField<>();;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private Consumer<M> mDataConsumer = new Consumer<M>() {
        @Override
        public void accept(M m) throws Exception {
            publishData(m);
        }
    };

    private Consumer<Throwable> mErrorConsumer = new Consumer<Throwable>() {
        @Override
        public void accept(Throwable tr) throws Exception {
            getWindowActions().showErrorView();
        }
    };


    @Override
    public void onViewAttached(Context ctx) {
        super.onViewAttached(ctx);
        DataViewConfig config = getDataViewConfig();
        if (config.startWithProgress){
            getWindowActions().showLoadingView();
        }
    }

    @Override
    public void onViewDetached() {
        super.onViewDetached();
        mCompositeDisposable.dispose();
    }

    public void onDataChange(M m){

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


    public Consumer<M> getDataConsumer() {
        return mDataConsumer;
    }

    public Consumer<Throwable> getErrorConsumer() {
        return mErrorConsumer;
    }

    protected DataViewConfig getDataViewConfig(){
        return new DataViewConfig();
    }

    public final void publishData(M m){
        if (getWindowActions().isLoadingViewShowing()){
            getWindowActions().dismissLoadingView();
        }
        if (getWindowActions().isErrorViewShowing()){
            getWindowActions().dismissErrorView();
        }
        mCompositeDisposable.dispose();
        mCompositeDisposable = new CompositeDisposable();
        onDataChange(m);
        data.set(m);
    }

}
