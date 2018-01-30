package com.jude.jarvis_core.framework.b_inject;

import android.content.Context;

/**
 * Created by Jude on 2017/7/28.
 */

public class ViewInjector {
    public static void inject(InjectPresenter presenter, Context context){
        presenter.mContext = context;
    }
}
