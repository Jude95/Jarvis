package com.jude.jarvis_core.framework.c_decoration;

import com.jude.jarvis_core.framework.b_inject.InjectPresenter;

/**
 * Created by zhuchenxi on 2017/1/16.
 */

public class DecorationPresenter extends InjectPresenter {
    WindowAction mWindowAction;

    public WindowAction getWindowActions() {
        return mWindowAction;
    }
}
