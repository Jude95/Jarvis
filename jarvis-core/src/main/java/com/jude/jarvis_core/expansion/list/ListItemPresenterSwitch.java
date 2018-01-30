package com.jude.jarvis_core.expansion.list;

/**
 * Created by Jude on 2018/1/30.
 */

public interface ListItemPresenterSwitch<M> {

    Class<? extends ListItemPresenter> getItemPresenterClass(int position,M data);

}
