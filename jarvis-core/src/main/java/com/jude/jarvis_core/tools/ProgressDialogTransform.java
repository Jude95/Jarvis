package com.jude.jarvis_core.tools;

import com.jude.jarvis_core.framework.c_decoration.DecorationPresenter;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class ProgressDialogTransform<T> implements ObservableTransformer<T, T>, CompletableTransformer,SingleTransformer<T,T>,FlowableTransformer<T,T> {
    private DecorationPresenter presenter;

    public ProgressDialogTransform(DecorationPresenter presenter) {
        this.presenter = presenter;
    }

    public static <T> ProgressDialogTransform<T> newInstance(DecorationPresenter presenter){
        return new ProgressDialogTransform<>(presenter);
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        presenter.getWindowActions().showLoadingView();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        presenter.getWindowActions().dismissLoadingView();

                    }
                });
    }

    @Override
    public CompletableSource apply(Completable upstream) {
        return upstream
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        presenter.getWindowActions().showLoadingView();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        presenter.getWindowActions().dismissLoadingView();

                    }
                });
    }

    @Override
    public Publisher<T> apply(Flowable<T> upstream) {
        return upstream
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        presenter.getWindowActions().showLoadingView();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        presenter.getWindowActions().dismissLoadingView();

                    }
                });
    }

    @Override
    public SingleSource<T> apply(Single<T> upstream) {
        return upstream
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        presenter.getWindowActions().showLoadingView();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        presenter.getWindowActions().dismissLoadingView();

                    }
                });
    }
}