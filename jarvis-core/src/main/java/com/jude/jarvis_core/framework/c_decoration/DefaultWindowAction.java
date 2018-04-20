package com.jude.jarvis_core.framework.c_decoration;

/**
 * Created by Jude on 2017/7/25.
 */

public class DefaultWindowAction extends WindowAction {

    public DefaultWindowAction(DecorationActivity activity) {
        super(activity);
    }

    @Override
    public boolean isLoadingViewShowing() {
        return false;
    }

    @Override
    public void showLoadingView() {
        throw new IllegalStateException("showLoadingView() not implement");
    }

    @Override
    public void dismissLoadingView() {
        throw new IllegalStateException("dismissLoadingView() not implement");
    }

    @Override
    public boolean isErrorViewShowing() {
        return false;
    }

    @Override
    public void showErrorView() {
        throw new IllegalStateException("showErrorView() not implement");
    }

    @Override
    public void dismissErrorView() {
        throw new IllegalStateException("dismissErrorView() not implement");
    }

    @Override
    public void setTitle(String title) {
        getDecorationActivity().setTitle(title);
    }

    @Override
    public void finish() {
        getDecorationActivity().finish();
    }

    @Override
    public boolean isProgressViewShowing() {
        return false;
    }

    @Override
    public void showProgressView() {
        throw new IllegalStateException("showProgressView() not implement");
    }

    @Override
    public void dismissProgressView() {
        throw new IllegalStateException("dismissProgressView() not implement");
    }

    public static class Factory extends WindowAction.Factory{

        @Override
        public WindowAction create(DecorationActivity activity) {
            return new DefaultWindowAction(activity);
        }
    }
}
