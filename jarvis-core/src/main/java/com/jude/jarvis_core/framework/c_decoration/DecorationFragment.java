package com.jude.jarvis_core.framework.c_decoration;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jude.jarvis_core.JarvisSetting;
import com.jude.jarvis_core.framework.b_inject.InjectFragment;

/**
 * Created by zhuchenxi on 2017/1/16.
 */
public class DecorationFragment<P extends DecorationPresenter> extends InjectFragment<P> {
    FrameLayout mContentParent;
    FrameLayout mContent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContentParent = new FrameLayout(context);
        mContent = new FrameLayout(context);
        mContentParent.addView(mContent, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getPresenter().mWindowAction = JarvisSetting.sViewDecoratorFactory.create((DecorationActivity) getActivity());
        getPresenter().mWindowAction.container = mContentParent;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mContentParent;
    }


    public void setContentView(int layoutResID) {
        this.setContentView(getLayoutInflater().inflate(layoutResID, mContent, false));
    }

    public void setContentView(View view) {
        this.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mContent.addView(view, params);
    }

    public ViewGroup getContentView(){
        return mContent;
    }
}
