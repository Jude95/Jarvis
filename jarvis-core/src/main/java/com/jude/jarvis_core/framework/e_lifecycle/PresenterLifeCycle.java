package com.jude.jarvis_core.framework.e_lifecycle;

import android.content.Context;
import android.os.Bundle;


/**
 * Created by zhuchenxi on 2017/1/16.
 */

public interface PresenterLifeCycle {
    void onViewAttached(Context ctx);
    void onViewDetached();
    void onSaveInstanceState(Bundle outState);
    void onRestoreInstanceState(Bundle savedInstanceState);
}
