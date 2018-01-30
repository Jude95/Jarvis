package com.jude.jarvis_core.expansion.list;

public class ListViewConfig implements Cloneable{

    boolean refreshAble = false;
    boolean loadMoreAble = false;
    boolean showEmptyView = false;
    boolean showFooterEndView = false;
    boolean showFooterErrorView = false;
    boolean refreshWhenAttach = false;
    boolean startWithProgress = false;
    boolean errorClickToResume = false;

    public ListViewConfig setRefreshWhenAttach(boolean refreshWhenAttach) {
        this.refreshWhenAttach = refreshWhenAttach;
        return this;
    }

    public ListViewConfig setRefreshAble(boolean refreshAble) {
        this.refreshAble = refreshAble;
        return this;
    }

    public ListViewConfig setLoadMoreAble(boolean loadMoreAble) {
        this.loadMoreAble = loadMoreAble;
        return this;
    }

    public ListViewConfig setShowEmptyView(boolean showEmptyView) {
        this.showEmptyView = showEmptyView;
        return this;
    }

    public ListViewConfig setStartWithProgress(boolean startWithProgress) {
        this.startWithProgress = startWithProgress;
        return this;
    }

    public ListViewConfig setErrorClickToResume(boolean errorClickToResume) {
        this.errorClickToResume = errorClickToResume;
        return this;
    }

    public ListViewConfig setShowFooterEndView(boolean showFooterEndView) {
        this.showFooterEndView = showFooterEndView;
        return this;
    }

    public ListViewConfig setShowFooterErrorView(boolean showFooterErrorView) {
        this.showFooterErrorView = showFooterErrorView;
        return this;
    }

    @Override
    public ListViewConfig clone(){
        try {
            return (ListViewConfig) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return new ListViewConfig();
    }
}