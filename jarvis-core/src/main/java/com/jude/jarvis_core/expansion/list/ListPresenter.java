package com.jude.jarvis_core.expansion.list;

import android.databinding.ViewDataBinding;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.jarvis_core.JarvisSetting;
import com.jude.jarvis_core.R;
import com.jude.jarvis_core.expansion.DisposableTransformer;
import com.jude.jarvis_core.framework.z_base.JarvisPresenter;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Jude on 2017/7/25.
 */

public abstract class ListPresenter<D extends ViewDataBinding, M> extends JarvisPresenter<D> {

    private EasyRecyclerView mRecyclerView;
    private ListViewAdapter mListViewAdapter;
    private ListViewConfig mListViewConfig;
    private int page = 0;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private Consumer<List<M>> mRefreshConsumer = new Consumer<List<M>>() {
        @Override
        public void accept(List<M> ms) throws Exception {
            getAdapter().clear();
            getAdapter().addAll(ms);
            if (getWindowActions().isLoadingViewShowing()){
                getWindowActions().dismissLoadingView();
            }
            if (getWindowActions().isErrorViewShowing()){
                getWindowActions().dismissErrorView();
            }
            page = 1;
        }
    };

    private Consumer<List<M>> mMoreConsumer = new Consumer<List<M>>() {
        @Override
        public void accept(List<M> ms) throws Exception {
            getAdapter().addAll(ms);
            if (getWindowActions().isLoadingViewShowing()){
                getWindowActions().dismissLoadingView();
            }
            if (getWindowActions().isErrorViewShowing()){
                getWindowActions().dismissErrorView();
            }
            page++;
        }
    };

    private Consumer<Throwable> mErrorConsumer = new Consumer<Throwable>() {
        @Override
        public void accept(Throwable e) throws Exception {
            stopRefresh();
            if (mListViewConfig.loadMoreAble&&mListViewConfig.showFooterErrorView){
                getAdapter().pauseMore();
            }
            if (getAdapter().getCount() == 0){
                getWindowActions().showErrorView();
            }
        }
    };

    @Override
    public void onCreated() {
        super.onCreated();
        mListViewAdapter = getListViewAdapter();
        mListViewConfig = getListConfig();
        if (mListViewConfig.startWithProgress){
            getWindowActions().showLoadingView();
        }
        initRecyclerView();
        mRecyclerView.setAdapter(new MultiPresenterRecyclerAdapter<>(getContext()));
        initAdapter();
    }

    @Override
    public void onViewBound() {
        super.onViewBound();
        if (mListViewConfig.refreshWhenAttach){
            requestRefresh();
        }
    }

    @Override
    public void onViewDetached() {
        super.onViewDetached();
        mCompositeDisposable.dispose();
    }

    private void initRecyclerView(){
        mRecyclerView = getViewDataBinding().getRoot().findViewById(R.id.recycler);
        if (mRecyclerView == null){
            throw new RuntimeException("No found recycler with id \"recycler\"");
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (mListViewConfig.refreshAble){
            mRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    ListPresenter.this.onRefresh();
                }
            });
        }
        if (mListViewConfig.showEmptyView){
            mRecyclerView.setEmptyView(mListViewAdapter.getEmptyView());
        }
    }

    private void initAdapter(){
        if (mListViewConfig.loadMoreAble){
            getAdapter().setMore(mListViewAdapter.getFooterLoadingView(), new RecyclerArrayAdapter.OnMoreListener() {
                @Override
                public void onMoreShow() {
                    onLoadMore();
                }

                @Override
                public void onMoreClick() {

                }
            });
        }
        if (mListViewConfig.showFooterErrorView){
            getAdapter().setError(mListViewAdapter.getFooterErrorView(), new RecyclerArrayAdapter.OnErrorListener() {
                @Override
                public void onErrorShow() {

                }

                @Override
                public void onErrorClick() {
                    if (mListViewConfig.errorClickToResume){
                        onLoadMore();
                    }
                }
            });
        }
        if (mListViewConfig.showFooterEndView){
            getAdapter().setNoMore(mListViewAdapter.getFooterEndView());
        }
    }

    protected ListViewAdapter getListViewAdapter(){
        return JarvisSetting.sListViewAdapterFactory.create(this);
    }

    protected ListViewConfig getListConfig(){
        return mListViewAdapter.config(new ListViewConfig());
    }


    public EasyRecyclerView getRecyclerView(){
        return mRecyclerView;
    }

    public MultiPresenterRecyclerAdapter<M> getAdapter(){
        return (MultiPresenterRecyclerAdapter<M>) mRecyclerView.getAdapter();
    }


    public int getCurPage(){
        return page;
    }

    public void setCurPage(int page){
        this.page = page;
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


    public Consumer<List<M>> getRefreshConsumer() {
        return mRefreshConsumer;
    }

    public Consumer<List<M>> getMoreConsumer() {
        return mMoreConsumer;
    }

    public Consumer<Throwable> getErrorConsumer() {
        return mErrorConsumer;
    }

    public final void requestRefresh(){
        mRecyclerView.setRefreshing(true,true);
    }

    public final void stopRefresh(){
        if(mRecyclerView!=null)
            mRecyclerView.getSwipeToRefresh().setRefreshing(false);
    }

    public void onRefresh(){}
    public void onLoadMore(){}
}
