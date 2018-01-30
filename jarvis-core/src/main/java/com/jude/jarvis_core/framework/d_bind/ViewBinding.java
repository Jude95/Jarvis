package com.jude.jarvis_core.framework.d_bind;

import android.databinding.BindingAdapter;
import android.view.ViewGroup;

/**
 * Created by Jude on 2017/7/27.
 */

public class ViewBinding {

    @BindingAdapter({"presenter"})
    public static <P extends BindPresenter> void viewAdapter(ViewGroup parent, final Class<P> presenterClass) {
        ViewBinder.createView(presenterClass,parent);
    }

}
