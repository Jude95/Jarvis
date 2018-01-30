package com.jude.jarvis_core.expansion;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Consumer;

public class DisposableTransformer<T> implements ObservableTransformer<T, T>, FlowableTransformer<T, T>, SingleTransformer<T, T>, MaybeTransformer<T, T>, CompletableTransformer {
    private final Consumer<Disposable> mSubscribeConsumer;

    public DisposableTransformer(Consumer<Disposable> mSubscribeConsumer) {
        this.mSubscribeConsumer = mSubscribeConsumer;
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.doOnSubscribe(mSubscribeConsumer);
    }

    @Override
    public CompletableSource apply(Completable upstream) {
        return upstream.doOnSubscribe(mSubscribeConsumer);
    }

    @Override
    public MaybeSource<T> apply(Maybe<T> upstream) {
        return upstream.doOnSubscribe(mSubscribeConsumer);
    }

    @Override
    public SingleSource<T> apply(Single<T> upstream) {
        return upstream.doOnSubscribe(mSubscribeConsumer);
    }

    @Override
    public Publisher<T> apply(Flowable<T> upstream) {
        return upstream.doOnSubscribe(new Consumer<Subscription>() {
            @Override
            public void accept(Subscription subscription) throws Exception {
                mSubscribeConsumer.accept(Disposables.fromSubscription(subscription));
            }
        });
    }
}