package com.jude.jarvis_core.expansion.list;

import android.support.annotation.LayoutRes;

/**
 * Created by Jude on 2017/7/26.
 */

public class DefaultListViewAdapter extends ListViewAdapter {


    @Override
    public ListViewConfig config(ListViewConfig listViewConfig) {
        return listViewConfig
                .setRefreshAble(false)
                .setLoadMoreAble(false)
                .setStartWithProgress(false)
                .setErrorClickToResume(false);
    }

    @Override
    public int getEmptyView() {
        return 0;
    }

    @Override
    public  @LayoutRes
    int  getFooterEndView() {
        return 0;
    }

    @Override
    public  @LayoutRes
    int  getFooterLoadingView() {
        return 0;
    }

    @Override
    public  @LayoutRes
    int  getFooterErrorView() {
        return 0;
    }

    public static class Factory extends ListViewAdapter.Factory{

        @Override
        public ListViewAdapter create(ListPresenter listPresenter) {
            return new DefaultListViewAdapter();
        }
    }
}
