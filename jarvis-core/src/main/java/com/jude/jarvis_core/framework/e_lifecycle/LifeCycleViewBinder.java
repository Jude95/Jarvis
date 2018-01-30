package com.jude.jarvis_core.framework.e_lifecycle;

import android.view.View;

/**
 * Created by Jude on 2017/7/27.
 */

public class LifeCycleViewBinder {

    public static void bind(final LifeCyclePresenter presenter, View view){
        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                presenter.onViewAttached(v.getContext());
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                presenter.onViewDetached();
            }
        });

    }
}
