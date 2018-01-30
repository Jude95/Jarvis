package com.jude.jarvis_core.expansion.list;

import android.support.annotation.LayoutRes;

/**
 * Created by Jude on 2017/7/26.
 */

public abstract class ListViewAdapter {

    public abstract ListViewConfig config(ListViewConfig listViewConfig);

    public abstract @LayoutRes
    int getEmptyView();

    public abstract @LayoutRes
    int getFooterEndView();

    public abstract @LayoutRes
    int getFooterLoadingView();

    public abstract @LayoutRes
    int getFooterErrorView();

    public static abstract class Factory {
        public abstract ListViewAdapter create(ListPresenter listPresenter);
    }

}
