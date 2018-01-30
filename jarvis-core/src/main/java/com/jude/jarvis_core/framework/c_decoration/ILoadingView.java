package com.jude.jarvis_core.framework.c_decoration;

/**
 * Created by zhuchenxi on 2017/1/16.
 */

public interface ILoadingView {
    boolean isLoadingViewShowing();
    void showLoadingView();
    void dismissLoadingView();
}
