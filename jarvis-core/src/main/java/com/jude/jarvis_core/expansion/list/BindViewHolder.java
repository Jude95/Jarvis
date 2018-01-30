package com.jude.jarvis_core.expansion.list;

import android.databinding.ViewDataBinding;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Jude on 2017/7/25.
 */

public class BindViewHolder<D extends ViewDataBinding,M> extends BaseViewHolder<M> {
    final ListItemPresenter<D,M> presenter;

    public BindViewHolder(ListItemPresenter<D,M> listItemPresenter) {
        super(listItemPresenter.getViewDataBinding().getRoot());
        presenter = listItemPresenter;
    }

    @Override
    public void setData(M data) {
        presenter.publish(data);
    }
}
