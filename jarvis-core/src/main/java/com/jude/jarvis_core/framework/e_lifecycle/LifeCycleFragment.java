package com.jude.jarvis_core.framework.e_lifecycle;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jude.jarvis_core.framework.d_bind.BindFragment;

/**
 * Created by zhuchenxi on 2017/1/16.
 */

public class LifeCycleFragment<P extends LifeCyclePresenter> extends BindFragment<P> {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getPresenter().onViewAttached(getActivity());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getPresenter().onViewDetached();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getPresenter().onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        getPresenter().onRestoreInstanceState(savedInstanceState);
    }
}
