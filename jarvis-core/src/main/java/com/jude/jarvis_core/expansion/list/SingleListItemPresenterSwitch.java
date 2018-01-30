package com.jude.jarvis_core.expansion.list;

public class SingleListItemPresenterSwitch<M> implements ListItemPresenterSwitch<M> {
    private final Class<? extends ListItemPresenter> clazz;

    public SingleListItemPresenterSwitch(Class<? extends ListItemPresenter> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Class<? extends ListItemPresenter> getItemPresenterClass(int position, M data) {
        return clazz;
    }
}