package com.jude.jarvis_core.framework.c_decoration;

import android.content.Context;

/**
 * Created by Jude on 2017/7/28.
 */

public class DecorationInjector {
    public static void inject(DecorationPresenter presenter, Context context){
        presenter.mWindowAction =  ((DecorationActivity)context).mWindowAction;
    }
}
