package com.jude.jarvis_core.framework.c_decoration;

import android.widget.FrameLayout;

/**
 * Created by zhuchenxi on 2017/1/16.
 */

public abstract class WindowAction implements ILoadingView,IProgressView,IErrorView,IWindow{
    DecorationActivity activity;
    FrameLayout container;

    public WindowAction(DecorationActivity activity) {
        this.activity = activity;
    }

    public final DecorationActivity getDecorationActivity(){
        return activity;
    }

    public final FrameLayout getContainer() {
        return container;
    }

    public static abstract class Factory{
        public abstract WindowAction create(DecorationActivity activity);
    }
}
