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

public class ProgressActionTransform<T> implements ObservableTransformer<T, T>, CompletableTransformer,SingleTransformer<T,T>,FlowableTransformer<T,T> {
    private DecorationPresenter presenter;

    public ProgressActionTransform(DecorationPresenter presenter) {
        this.presenter = presenter;
    }

    public static <T> ProgressActionTransform<T> newInstance(DecorationPresenter presenter){
        return new ProgressActionTransform<>(presenter);
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        presenter.getWindowActions().showProgressView();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        presenter.getWindowActions().dismissProgressView();

                    }
                });
    }

    @Override
    public CompletableSource apply(Completable upstream) {
        return upstream
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        presenter.getWindowActions().showProgressView();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        presenter.getWindowActions().dismissProgressView();

                    }
                });
    }

    @Override
    public Publisher<T> apply(Flowable<T> upstream) {
        return upstream
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        presenter.getWindowActions().showProgressView();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        presenter.getWindowActions().dismissProgressView();

                    }
                });
    }

    @Override
    public SingleSource<T> apply(Single<T> upstream) {
        return upstream
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        presenter.getWindowActions().showProgressView();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        presenter.getWindowActions().dismissProgressView();

                    }
                });
    }
}